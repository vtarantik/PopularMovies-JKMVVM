package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.content.Intent;
import android.databinding.ObservableArrayList;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;
import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.db.dao.MovieDao;
import com.example.vtarantik.popularmovies_jkmvvm.db.model.Category;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.interactor.IApiInteractor;
import com.example.vtarantik.popularmovies_jkmvvm.state.LCEStatefulLayout;

import java.util.List;

import javax.inject.Inject;

import cz.kinst.jakub.view.SimpleStatefulLayout;
import cz.kinst.jakub.viewmodelbinding.ViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieListFragmentViewModel extends ViewModel {

	public final ObservableArrayList<Movie> movies = new ObservableArrayList<>();

	public final ItemBinding<Movie> itemBinding = ItemBinding.of(BR.item, R.layout.movie_tile);

	public LCEStatefulLayout.StateController stateController;

	private Category selectedCategory;

	@Inject
	IApiInteractor mIApiInteractor;


	@Inject
	MovieDao mMovieDao;


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();

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
		getMoviesForCategoryFromDb(Category.FAVOURITE);
	}


	public void showMovieDetails(Movie movie){
		Intent intent = MovieDetailActivity.newIntent(getActivity(),movie);
		getActivity().startActivity(intent);
	}


	private void getMoviesForCategory(Category category) {
		if(selectedCategory != category) {
			selectedCategory = category;

			mIApiInteractor.getMovies(category)
					.subscribeOn(Schedulers.newThread())
					.flatMap(movieResponse -> mMovieDao.insertInBatch(movieResponse,category)
							.map(bool -> movieResponse))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(movieResponse -> {
						if(movieResponse.getMovies() != null && !movieResponse.getMovies().isEmpty()) {
							stateController.setState(LCEStatefulLayout.State.CONTENT);
							updateData(movieResponse.getMovies());
						} else {
							onApiError(category);
						}

					}, throwable -> onApiError(category));
		}
	}


	private void getMoviesForCategoryFromDb(Category category) {
		if(selectedCategory != category) {
			selectedCategory = category;

			stateController.setState(SimpleStatefulLayout.State.PROGRESS);

			mMovieDao.getMovies(category)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(movies1 -> {
						if(movies1 != null && !movies1.isEmpty()) {
							stateController.setState(LCEStatefulLayout.State.CONTENT);
							updateData(movies1);
						} else {
//							stateController.setState(LCEStatefulLayout.State.EMPTY);
							onError(category);

						}
					}, throwable -> onError(category));
		}
	}


	private void onError(Category category) {
		selectedCategory = null;
		getMoviesForCategory(category);
//		stateController.setState(LCEStatefulLayout.State.EMPTY);
	}


	private void onApiError(Category category){
//		selectedCategory = null;
//		getMoviesForCategoryFromDb(category);
		stateController.setState(LCEStatefulLayout.State.EMPTY);
	}


	private void updateData(List<Movie> newMovies) {
		movies.clear();
		movies.addAll(newMovies);
	}
}
