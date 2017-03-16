package com.example.vtarantik.popularmovies_jkmvvm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.FragmentMovieListBinding;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.view.IMovieListView;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.MovieListFragmentViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelFragment;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieListFragment extends ViewModelFragment<FragmentMovieListBinding,MovieListFragmentViewModel> implements IMovieListView{

	@Override
	public void onItemClick(Movie movie) {
		Intent intent = MovieDetailActivity.newIntent(getActivity(),movie);
		startActivity(intent);
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.fragment_movie_list,MovieListFragmentViewModel.class);
		super.onCreate(savedInstanceState);
	}
}
