package com.example.vtarantik.popularmovies_jkmvvm.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.vtarantik.popularmovies_jkmvvm.R;


/**
 * Created by vtarantik on 17/03/2017.
 */

public class SettingsFragment extends PreferenceFragment{

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
