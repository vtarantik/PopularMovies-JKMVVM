package com.example.vtarantik.popularmovies_jkmvvm.dagger;

import android.app.Application;
import android.view.LayoutInflater;

import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by vtarantik on 14/03/2017.
 */

@Module(
		includes = {
				InteractorsModule.class
		}
)
public class AppModule {
	public static final String TAG = AppModule.class.getName();

	private final PopularMoviesApp app;

	public AppModule(PopularMoviesApp app) {
		this.app = app;
	}

	@Provides
	@Singleton
	public Application provideApplication() {
		return app;
	}

	@Provides
	@Singleton
	LayoutInflater provideLayoutInflater() {
		return LayoutInflater.from(app);
	}
}
