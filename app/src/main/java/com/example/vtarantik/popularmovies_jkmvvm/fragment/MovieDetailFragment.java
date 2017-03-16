package com.example.vtarantik.popularmovies_jkmvvm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieListActivity;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.FragmentMovieDetailBinding;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.MovieDetailFragmentViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelFragment;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieDetailFragment extends ViewModelFragment<FragmentMovieDetailBinding,MovieDetailFragmentViewModel> {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.fragment_movie_detail, MovieDetailFragmentViewModel.class);
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onViewModelInitialized(MovieDetailFragmentViewModel viewModel) {
		super.onViewModelInitialized(viewModel);
		viewModel.movie.set(getArguments().getParcelable(MovieDetailActivity.EXTRA_MOVIE));
	}
}
