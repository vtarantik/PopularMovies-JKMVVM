package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.net.Uri;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;
import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Trailer;
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

public class TrailerFragmentViewModel extends ViewModel {

	public final ObservableField<Movie> movie = new ObservableField<>();

	public final ObservableList<Trailer> trailers = new ObservableArrayList<>();

	public final ItemBinding<Trailer> itemBinding = ItemBinding.of(BR.item, R.layout.trailer_row);

	public LCEStatefulLayout.StateController stateController;

	@Inject
	IApiInteractor mIApiInteractor;


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();

		PopularMoviesApp.getAppComponent().inject(this);

		itemBinding.bindExtra(BR.view, getView());

		stateController = LCEStatefulLayout.StateController.create().build();

		getTrailersForMovie(movie.get());
	}


	public void playTrailer(String key) {
		watchYoutubeVideo(key);
	}


	private void getTrailersForMovie(Movie movie) {
		stateController.setState(SimpleStatefulLayout.State.PROGRESS);

		mIApiInteractor.getTrailers(movie.getId())
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(trailerListResponse -> {

					if(trailerListResponse.getResults() != null && !trailerListResponse.getResults().isEmpty()) {
						stateController.setState(LCEStatefulLayout.State.CONTENT);
						updateData(trailerListResponse.getResults());
					} else {
						stateController.setState(SimpleStatefulLayout.State.EMPTY);
					}

				}, throwable -> onError(throwable));
	}


	private void onError(Throwable throwable) {
		stateController.setState(SimpleStatefulLayout.State.EMPTY);
	}


	private void updateData(List<Trailer> newTrailers) {
		trailers.clear();
		trailers.addAll(newTrailers);
	}


	private void watchYoutubeVideo(String id) {
		Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
		Intent webIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://www.youtube.com/watch?v=" + id));
		try {
			getActivity().startActivity(appIntent);
		} catch(ActivityNotFoundException ex) {
			getActivity().startActivity(webIntent);
		}
	}
}
