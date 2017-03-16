package com.example.vtarantik.popularmovies_jkmvvm.interactor;

import com.example.vtarantik.popularmovies_jkmvvm.BuildConfig;
import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieResponse;
import com.example.vtarantik.popularmovies_jkmvvm.rest.ApiDescription;

import rx.Observable;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class ApiInteractor implements IApiInteractor{
	public static final String TAG = ApiInteractor.class.getName();
	private final ApiDescription apiDescription;

	public ApiInteractor(ApiDescription apiDescription) {
		this.apiDescription = apiDescription;
	}

	@Override
	public Observable<MovieResponse> getPopularMovies() {
		return this.apiDescription.getPopularMovies(BuildConfig.MOVIEDB_API_KEY,1);
	}
}
