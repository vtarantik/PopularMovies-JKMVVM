package com.example.vtarantik.popularmovies_jkmvvm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.FragmentMovieTrailerBinding;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Trailer;
import com.example.vtarantik.popularmovies_jkmvvm.view.ITrailerListView;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.TrailerFragmentViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelFragment;


/**
 * Created by vtarantik on 24/03/2017.
 */

public class TrailerListFragment extends ViewModelFragment<FragmentMovieTrailerBinding, TrailerFragmentViewModel> implements ITrailerListView {
	private static final String TAG = TrailerListFragment.class.getName();


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.fragment_movie_trailer,TrailerFragmentViewModel.class);
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onViewModelInitialized(TrailerFragmentViewModel viewModel) {
		super.onViewModelInitialized(viewModel);

		getViewModel().movie.set(getArguments().getParcelable(MovieDetailActivity.EXTRA_MOVIE));
	}


	@Override
	public void onItemClick(Trailer entity) {
		Log.d(TAG,"item clicked");
	}
}
