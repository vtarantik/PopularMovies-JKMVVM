package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;
import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.MoviesContract.MoviesEntry;
import com.example.vtarantik.popularmovies_jkmvvm.db.helper.CursorHandler;
import com.example.vtarantik.popularmovies_jkmvvm.db.helper.MovieCursorHandler;
import com.example.vtarantik.popularmovies_jkmvvm.db.helper.MovieDBHelper;
import com.example.vtarantik.popularmovies_jkmvvm.db.model.Category;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.interactor.IApiInteractor;
import com.example.vtarantik.popularmovies_jkmvvm.state.LCEStatefulLayout;
import com.example.vtarantik.popularmovies_jkmvvm.view.IMovieListView;

import java.util.List;

import javax.inject.Inject;

import cz.kinst.jakub.viewmodelbinding.ViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieListFragmentViewModel extends ViewModel implements LoaderManager.LoaderCallbacks<Cursor> {
	public static final int MOVIES_LOADER = 1;
	private static final String TAG = MovieListFragmentViewModel.class.getName();


	public final ObservableArrayList<Movie> movies = new ObservableArrayList<>();

	public final ItemBinding<Movie> itemBinding = ItemBinding.of(BR.item, R.layout.movie_tile);

	public LCEStatefulLayout.StateController stateController;

	private Category selectedCategory;


	@Inject
	IApiInteractor mIApiInteractor;


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();
		movies.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Movie>>() {
			@Override
			public void onChanged(ObservableList<Movie> movies) {
				Log.d(TAG,"list changed");
			}


			@Override
			public void onItemRangeChanged(ObservableList<Movie> movies, int i, int i1) {
				Log.d(TAG,"item range changed");
			}


			@Override
			public void onItemRangeInserted(ObservableList<Movie> movies, int i, int i1) {
				Log.d(TAG,"item range inserted");
			}


			@Override
			public void onItemRangeMoved(ObservableList<Movie> movies, int i, int i1, int i2) {
				Log.d(TAG,"item range moved");
			}


			@Override
			public void onItemRangeRemoved(ObservableList<Movie> movies, int i, int i1) {
				Log.d(TAG,"item range removed");
			}
		});

		PopularMoviesApp.getAppComponent().inject(this);

		itemBinding.bindExtra(BR.view, getView());

		stateController = LCEStatefulLayout.StateController.create().build();

	}


	public void getPopularMoviesList() {

		getMoviesForCategoryFromDb(Category.POPULAR);
	}


	public void getTopRatedMoviesList() {

		getMoviesForCategoryFromDb(Category.TOP_RATED);
	}


	public void getFavouriteMoviesList() {
		selectedCategory = null;
		getMoviesForCategoryFromDb(Category.FAVOURITE);
	}


	public void showMovieDetails(Movie movie) {
		Intent intent = MovieDetailActivity.newIntent(getActivity(), movie);
		getActivity().startActivity(intent);
	}


	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		movies.clear();

		stateController.setState(LCEStatefulLayout.State.PROGRESS);

		switch(id) {
			case MOVIES_LOADER:
				Uri movieUri = selectedCategory == Category.POPULAR ? MoviesEntry.POPULAR_MOVIES_URI : selectedCategory == Category.FAVOURITE ? MoviesEntry.FAVOURITE_MOVIES_URI : selectedCategory == Category.TOP_RATED ? MoviesEntry.TOP_RATED_MOVIES_URI : null;

				String sortOrder = selectedCategory == Category.POPULAR ? MoviesEntry.COL_POPULARITY : MoviesEntry.COL_RATING;
				sortOrder += " DESC";

				return new CursorLoader(getContext(),
						movieUri,
						null,
						null,
						null,
						sortOrder
				);
		}
		return null;
	}


	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		CursorHandler<List<Movie>> handler = new MovieCursorHandler();
		List<Movie> mvs = handler.handle(data, () -> onError(selectedCategory));

		if(mvs != null && !mvs.isEmpty()) {
			updateData(mvs);
		}

	}


	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		movies.clear();
	}


	private void getMoviesForCategory(Category category) {
		if(selectedCategory != category) {
			selectedCategory = category;

			mIApiInteractor.getMovies(category)
					.subscribeOn(Schedulers.newThread())
					.flatMap(movieResponse -> MovieDBHelper.insertInBackground(getActivity().getContentResolver(), getUriForCategory(selectedCategory), movieResponse.getMovies())
							.map(bool -> movieResponse))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(movieResponse -> {
						if(movieResponse.getMovies() != null && !movieResponse.getMovies().isEmpty()) {
							updateData(movieResponse.getMovies());
						} else {
							onApiError(category, new Throwable("API ERROR"));
						}

					}, throwable -> onApiError(category, throwable));
		}
	}


	private void getMoviesForCategoryFromDb(Category category) {
		if(selectedCategory != category) {
			movies.clear();
			selectedCategory = category;

			if(getActivity().getLoaderManager().getLoader(MOVIES_LOADER) == null) {
				getActivity().getLoaderManager().initLoader(MOVIES_LOADER, null, this);
			} else {
				getActivity().getLoaderManager().restartLoader(MOVIES_LOADER, null, this);
			}

		}
	}


	private void onError(Category category) {
		selectedCategory = null;
		if(category != Category.FAVOURITE) {
			getMoviesForCategory(category);
		} else {
			selectedCategory = category;
			stateController.setState(LCEStatefulLayout.State.EMPTY);
		}
	}


	private void onApiError(Category category, Throwable throwable) {
		stateController.setState(LCEStatefulLayout.State.EMPTY);
	}


	private void updateData(List<Movie> newMovies) {
		stateController.setState(LCEStatefulLayout.State.CONTENT);
		movies.clear();
		movies.addAll(newMovies);
		((IMovieListView)getView()).showData(newMovies);
	}


	private Uri getUriForCategory(Category category) {
		switch(category) {
			case FAVOURITE:
				return MoviesEntry.FAVOURITE_MOVIES_URI;

			case TOP_RATED:
				return MoviesEntry.TOP_RATED_MOVIES_URI;

			case POPULAR:
				return MoviesEntry.POPULAR_MOVIES_URI;

			default:
				throw new UnsupportedOperationException("No such category");

		}
	}
}
