package com.example.vtarantik.popularmovies_jkmvvm.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.ActivitySettingsBinding;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.SettingsActivityViewModel;

import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;


/**
 * Created by vtarantik on 17/03/2017.
 */

public class SettingsActivity extends ViewModelActivity<ActivitySettingsBinding,SettingsActivityViewModel> {

	public static Intent newIntent(Context context) {
		return new Intent(context,SettingsActivity.class);
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.activity_settings,SettingsActivityViewModel.class);
		super.onCreate(savedInstanceState);

		replaceFragment(com.example.vtarantik.popularmovies_jkmvvm.fragment.SettingsFragment.class.getName());
	}


	private void replaceFragment(String fragmentName) {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentByTag(fragmentName);
		if (fragment == null) {
			fragment = Fragment.instantiate(this, fragmentName);
		}
		fm.beginTransaction().replace(R.id.activity_settings_fragment_container, fragment, fragmentName).commit();
	}
}
