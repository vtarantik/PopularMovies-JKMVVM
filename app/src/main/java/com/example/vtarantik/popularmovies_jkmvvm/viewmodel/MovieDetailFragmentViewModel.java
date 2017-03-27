package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.util.Log;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;
import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.db.dao.FavouriteDao;
import com.example.vtarantik.popularmovies_jkmvvm.db.dao.MovieDao;
import com.example.vtarantik.popularmovies_jkmvvm.db.model.Category;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Review;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Trailer;
import com.example.vtarantik.popularmovies_jkmvvm.state.LCEStatefulLayout;

import javax.inject.Inject;

import cz.kinst.jakub.view.SimpleStatefulLayout;
import cz.kinst.jakub.viewmodelbinding.ViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieDetailFragmentViewModel extends ViewModel {

	public final ObservableField<Movie> movie = new ObservableField<>();

	public final ObservableField<Boolean> favourite = new ObservableField<>();

	@Inject
	FavouriteDao mFavouriteDao;


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();

		PopularMoviesApp.getAppComponent().inject(this);

		favourite.set(movie.get().isFavourite());

		favourite.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
			@Override
			public void onPropertyChanged(Observable observable, int i) {
				boolean checked = (boolean) ((ObservableField) observable).get();
				mFavouriteDao.favourite(movie.get().getId(), checked)
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(v -> {
							boolean isFavourite = !movie.get().isFavourite();
							movie.get().setFavourite(isFavourite);
							favourite.set(isFavourite);
						});
			}
		});
	}


}
