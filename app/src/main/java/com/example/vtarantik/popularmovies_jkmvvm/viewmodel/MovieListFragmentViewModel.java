package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.databinding.ObservableArrayList;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;
import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.interactor.IApiInteractor;

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

	public final ItemBinding<Movie> itemBinding = ItemBinding.of(BR.item, R.layout.movie_row);

	public SimpleStatefulLayout.StateController stateController;

	@Inject
	IApiInteractor mIApiInteractor;


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();

		PopularMoviesApp.getAppComponent().inject(this);

		itemBinding.bindExtra(BR.view,getView());

		stateController = SimpleStatefulLayout.StateController.create().build();

		getPopularMoviesList();
	}


	public void getPopularMoviesList() {

		stateController.setState(SimpleStatefulLayout.State.PROGRESS);

		mIApiInteractor.getPopularMovies()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(movieResponse -> {

					if(movieResponse.getMovies() != null && !movieResponse.getMovies().isEmpty()) {
						stateController.setState(SimpleStatefulLayout.State.CONTENT);
						updateData(movieResponse.getMovies());
					} else {
						stateController.setState(SimpleStatefulLayout.State.EMPTY);

					}

				}, throwable -> {
					onError();
				});
	}


	private void onError() {
		stateController.setState(SimpleStatefulLayout.State.EMPTY);
	}


	private void updateData(List<Movie> newMovies) {
		movies.clear();
		movies.addAll(newMovies);
		stateController.setState(SimpleStatefulLayout.State.CONTENT);
	}
}
