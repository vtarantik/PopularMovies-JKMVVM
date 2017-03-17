package com.example.vtarantik.popularmovies_jkmvvm.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.ActivityMovieDetailBinding;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.MovieDetailActivityViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieDetailActivity extends ViewModelActivity<ActivityMovieDetailBinding,MovieDetailActivityViewModel>{

	public static final String EXTRA_MOVIE = "movie";

	public static Intent newIntent(Context context, Movie movie){
		Intent intent = new Intent(context,MovieDetailActivity.class);
		intent.putExtra(EXTRA_MOVIE,movie);

		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setupViewModel(R.layout.activity_movie_detail,MovieDetailActivityViewModel.class);
		super.onCreate(savedInstanceState);

		setSupportActionBar(getBinding().toolbar);

		replaceFragment(com.example.vtarantik.popularmovies_jkmvvm.fragment.MovieDetailFragment.class.getName());
	}


	@Override
	public void onViewModelInitialized(MovieDetailActivityViewModel viewModel) {
		super.onViewModelInitialized(viewModel);
		getViewModel().movie.set(getIntent().getParcelableExtra(EXTRA_MOVIE));
	}


	private void replaceFragment(String fragmentName) {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentByTag(fragmentName);
		if (fragment == null) {
			fragment = Fragment.instantiate(this, fragmentName,getBundle());
		}
		fm.beginTransaction().replace(R.id.activity_movie_detail_fragment_container, fragment, fragmentName).commit();
	}

}
