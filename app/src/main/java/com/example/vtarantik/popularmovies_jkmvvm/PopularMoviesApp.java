package com.example.vtarantik.popularmovies_jkmvvm;

import android.app.Application;

import com.example.vtarantik.popularmovies_jkmvvm.dagger.AppComponent;
import com.example.vtarantik.popularmovies_jkmvvm.dagger.AppModule;
import com.example.vtarantik.popularmovies_jkmvvm.dagger.DaggerAppComponent;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class PopularMoviesApp  extends Application {

	public static final String TAG = PopularMoviesApp.class.getName();

	private static AppComponent appComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		// Initialize Dagger
		appComponent = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();
	}

	public static AppComponent getAppComponent() {
		return appComponent;
	}

}
