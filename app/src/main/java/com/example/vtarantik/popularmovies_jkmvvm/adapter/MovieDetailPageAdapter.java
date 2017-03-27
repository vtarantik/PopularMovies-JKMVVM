package com.example.vtarantik.popularmovies_jkmvvm.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.fragment.OverviewFragment;
import com.example.vtarantik.popularmovies_jkmvvm.fragment.ReviewListFragment;
import com.example.vtarantik.popularmovies_jkmvvm.fragment.TrailerListFragment;


/**
 * Created by vtarantik on 24/03/2017.
 */

public class MovieDetailPageAdapter extends FragmentPagerAdapter {

	private Movie mMovie;

	public MovieDetailPageAdapter(FragmentManager fragmentManager, Movie movie){
		super(fragmentManager);

		mMovie = movie;
	}

	@Override
	public Fragment getItem(int position) {
		Bundle args  = new Bundle();
		args.putParcelable(MovieDetailActivity.EXTRA_MOVIE,mMovie);

		Fragment fragment = null;

		switch(position){
			case 0:
				fragment = new OverviewFragment();
				break;
			case 1:
				fragment = new TrailerListFragment();
				break;
			case 2:
				fragment = new ReviewListFragment();
				break;

		}

		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public int getCount() {
		return 3;
	}


	@Override
	public CharSequence getPageTitle(int position) {
		switch(position){
			case 0:
				return "Overview";
			case 1:
				return "Trailers";
			case 2:
				return "Reviews";

		}
		return null;
	}
}
