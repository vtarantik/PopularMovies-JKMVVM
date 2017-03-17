package com.example.vtarantik.popularmovies_jkmvvm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.ActivityMovieListBinding;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.MovieListActivityViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;


public class MovieListActivity extends ViewModelActivity<ActivityMovieListBinding,MovieListActivityViewModel>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setupViewModel(R.layout.activity_movie_list,MovieListActivityViewModel.class);
		super.onCreate(savedInstanceState);

		setSupportActionBar(getBinding().toolbar);

		replaceFragment(com.example.vtarantik.popularmovies_jkmvvm.fragment.MovieListFragment.class.getName());
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main,menu);

		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_settings:
				startSettingsActivity();
		}

		return super.onOptionsItemSelected(item);
	}


	private void startSettingsActivity() {
		Intent intent = SettingsActivity.newIntent(this);
		startActivity(intent);
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
