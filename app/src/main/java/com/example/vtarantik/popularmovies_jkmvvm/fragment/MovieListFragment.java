package com.example.vtarantik.popularmovies_jkmvvm.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.FragmentMovieListBinding;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.view.IMovieListView;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.MovieListFragmentViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelFragment;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieListFragment extends ViewModelFragment<FragmentMovieListBinding,MovieListFragmentViewModel> implements IMovieListView, SharedPreferences.OnSharedPreferenceChangeListener{

	@Override
	public void onItemClick(Movie movie) {
		getViewModel().showMovieDetails(movie);
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.fragment_movie_list,MovieListFragmentViewModel.class);
		super.onCreate(savedInstanceState);

		PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);

		getPreferredMovieList(PreferenceManager.getDefaultSharedPreferences(getActivity()),getString(R.string.pref_sort_key));
	}


	@Override
	public void onDestroy() {
		super.onDestroy();

		PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
	}


	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		getPreferredMovieList(sharedPreferences, key);
	}


	private void getPreferredMovieList(SharedPreferences sharedPreferences,String key){
		if(key.equals(getString(R.string.pref_sort_key))){
			String value = sharedPreferences.getString(key,getString(R.string.pref_sort_popular));

			if(value.equals(getString(R.string.pref_sort_popular))){
				getViewModel().getPopularMoviesList();
			}else if(value.equals(getString(R.string.pref_sort_top_rated))){
				getViewModel().getTopRatedMoviesList();
			}else if(value.equals(getString(R.string.pref_sort_favourite))){
				getViewModel().getFavouriteMoviesList();
			}
		}
	}
}
