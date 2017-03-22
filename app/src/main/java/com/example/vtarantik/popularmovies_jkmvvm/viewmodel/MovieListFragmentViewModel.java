package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.databinding.ObservableArrayList;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;
import com.example.vtarantik.popularmovies_jkmvvm.R;
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

		getPopularMoviesList();
	}


	public void getPopularMoviesList() {

		getMoviesForCategory(Category.POPULAR);
	}


	public void getTopRatedMoviesList() {

		getMoviesForCategory(Category.TOP_RATED);
	}


	public void getFavouriteMoviesList() {
		getMoviesForCategoryFromDb(Category.FAVOURITE);
	}


	private void getMoviesForCategory(Category category) {
		if(selectedCategory != category) {
			selectedCategory = category;
			stateController.setState(SimpleStatefulLayout.State.PROGRESS);

			mIApiInteractor.getMovies(category)
					.subscribeOn(Schedulers.newThread())
//					.flatMap(mR -> mMovieDao.insertInBatch(mR, category))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(movieResponse -> {

						if(movieResponse.getMovies() != null && !movieResponse.getMovies().isEmpty()) {
							stateController.setState(LCEStatefulLayout.State.CONTENT);
							updateData(movieResponse.getMovies());
						} else {
							getMoviesForCategoryFromDb(category);

						}

					}, throwable -> onError());
		}
	}


	private void getMoviesForCategoryFromDb(Category category) {
		if(selectedCategory != category) {
			selectedCategory = category;
			mMovieDao.getMovies(category)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(movies1 -> {
						if(movies1 != null && !movies1.isEmpty()) {
							stateController.setState(LCEStatefulLayout.State.CONTENT);
							updateData(movies1);
						} else {
							stateController.setState(LCEStatefulLayout.State.EMPTY);

						}
					}, throwable -> onError());
		}
	}


	private void onError() {
		stateController.setState(SimpleStatefulLayout.State.EMPTY);
	}


	private void updateData(List<Movie> newMovies) {
		movies.clear();
		movies.addAll(newMovies);
	}
}
