package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;
import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.db.dao.MovieDao;
import com.example.vtarantik.popularmovies_jkmvvm.db.model.Category;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Review;
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
 * Created by vtarantik on 24/03/2017.
 */

public class ReviewFragmentViewModel extends ViewModel{

	public final ObservableField<Movie> movie = new ObservableField<>();

	public final ObservableList<Review> reviews = new ObservableArrayList<>();

	public final ItemBinding<Review> itemBinding = ItemBinding.of(BR.item, R.layout.review_row);

	public LCEStatefulLayout.StateController stateController;

	@Inject
	IApiInteractor mIApiInteractor;


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();

		PopularMoviesApp.getAppComponent().inject(this);

		itemBinding.bindExtra(BR.view, getView());

		stateController = LCEStatefulLayout.StateController.create().build();

		getReviewsForMovie(movie.get());
	}


	private void getReviewsForMovie(Movie movie) {
			stateController.setState(SimpleStatefulLayout.State.PROGRESS);

			mIApiInteractor.getReviews(movie.getId())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(reviewListResponse -> {

						if(reviewListResponse.getResults() != null && !reviewListResponse.getResults().isEmpty()) {
							stateController.setState(LCEStatefulLayout.State.CONTENT);
							updateData(reviewListResponse.getResults());
						} else {
							stateController.setState(SimpleStatefulLayout.State.EMPTY);
						}

					}, throwable -> onError());
	}


	private void onError() {
		stateController.setState(SimpleStatefulLayout.State.EMPTY);
	}


	private void updateData(List<Review> newReviews) {
		reviews.clear();
		reviews.addAll(newReviews);
	}
}
