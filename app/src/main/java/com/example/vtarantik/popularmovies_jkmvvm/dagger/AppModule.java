package com.example.vtarantik.popularmovies_jkmvvm.dagger;

import android.app.Application;
import android.view.LayoutInflater;

import com.example.vtarantik.popularmovies_jkmvvm.PopularMoviesApp;
import com.example.vtarantik.popularmovies_jkmvvm.db.dao.FavouriteDao;
import com.example.vtarantik.popularmovies_jkmvvm.db.dao.MovieDao;
import com.example.vtarantik.popularmovies_jkmvvm.db.dao.PopularDao;
import com.example.vtarantik.popularmovies_jkmvvm.db.dao.TopRatedDao;
import com.example.vtarantik.popularmovies_jkmvvm.utility.Constants;
import com.hannesdorfmann.sqlbrite.dao.DaoManager;

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
	private final MovieDao movieDao;
	private final PopularDao popularDao;
	private final TopRatedDao topRatedDao;
	private final FavouriteDao favouriteDao;

	public AppModule(PopularMoviesApp app) {
		this.app = app;
		movieDao = new MovieDao();
		popularDao = new PopularDao();
		topRatedDao = new TopRatedDao();
		favouriteDao = new FavouriteDao();

		DaoManager.with(app)
				.version(Constants.DB_VERSION)
				.databaseName(Constants.DB_NAME)
				.logging(false)
				.add(movieDao)
				.add(popularDao)
				.add(topRatedDao)
				.add(favouriteDao)
				.build();
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

	@Provides
	@Singleton
	public MovieDao provideMovieDao(){
		return movieDao;
	}

	@Provides
	@Singleton
	public FavouriteDao provideFavouriteDao(){
		return favouriteDao;
	}

}
