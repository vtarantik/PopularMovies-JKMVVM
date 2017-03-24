package com.example.vtarantik.popularmovies_jkmvvm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.FragmentMovieOverviewBinding;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.OverviewFragmentViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelFragment;


/**
 * Created by vtarantik on 24/03/2017.
 */

public class OverviewFragment extends ViewModelFragment<FragmentMovieOverviewBinding, OverviewFragmentViewModel> {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.fragment_movie_overview, OverviewFragmentViewModel.class);
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onViewModelInitialized(OverviewFragmentViewModel viewModel) {
		super.onViewModelInitialized(viewModel);

		getViewModel().movie.set(getArguments().getParcelable(MovieDetailActivity.EXTRA_MOVIE));
	}
}
