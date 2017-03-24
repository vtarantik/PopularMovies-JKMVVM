package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Bundle;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.db.model.Category;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Review;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Trailer;
import com.example.vtarantik.popularmovies_jkmvvm.state.LCEStatefulLayout;

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

	public final ObservableList<Review> mReviews = new ObservableArrayList<>();

	public final ObservableList<Trailer> mTrailers = new ObservableArrayList<>();

	public final ItemBinding<Movie> trailerItemBinding = ItemBinding.of(BR.item, R.layout.trailer_row);

	public final ItemBinding<Movie> reviewItemBinding = ItemBinding.of(BR.item, R.layout.review_row);

	public LCEStatefulLayout.StateController stateController;





	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();

	}

}
