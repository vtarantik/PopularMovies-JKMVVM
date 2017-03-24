package com.example.vtarantik.popularmovies_jkmvvm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.FragmentMovieReviewBinding;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Review;
import com.example.vtarantik.popularmovies_jkmvvm.view.IReviewListView;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.ReviewFragmentViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelFragment;


/**
 * Created by vtarantik on 24/03/2017.
 */

public class ReviewListFragment extends ViewModelFragment<FragmentMovieReviewBinding,ReviewFragmentViewModel> implements IReviewListView{

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.fragment_movie_review,ReviewFragmentViewModel.class);
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onViewModelInitialized(ReviewFragmentViewModel viewModel) {
		super.onViewModelInitialized(viewModel);

		getViewModel().movie.set(getArguments().getParcelable(MovieDetailActivity.EXTRA_MOVIE));
	}


	@Override
	public void onItemClick(Review entity) {
		//TODO expand and animate?
	}
}
