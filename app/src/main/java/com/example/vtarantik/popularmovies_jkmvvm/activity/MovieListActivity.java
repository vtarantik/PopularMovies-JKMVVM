package com.example.vtarantik.popularmovies_jkmvvm.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.ActivityMovieListBinding;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.MovieListActivityViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;


public class MovieListActivity extends ViewModelActivity<ActivityMovieListBinding,MovieListActivityViewModel>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setupViewModel(R.layout.activity_movie_list,MovieListActivityViewModel.class);
		super.onCreate(savedInstanceState);

		replaceFragment(com.example.vtarantik.popularmovies_jkmvvm.fragment.MovieListFragment.class.getName());
	}


	private void replaceFragment(String fragmentName) {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentByTag(fragmentName);
		if (fragment == null) {
			fragment = Fragment.instantiate(this, fragmentName);
		}
		fm.beginTransaction().replace(R.id.activity_movie_list_fragment_container, fragment, fragmentName).commit();
	}
}
