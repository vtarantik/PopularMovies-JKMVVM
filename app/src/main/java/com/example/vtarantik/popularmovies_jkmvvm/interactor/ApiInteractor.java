package com.example.vtarantik.popularmovies_jkmvvm.interactor;

import com.example.vtarantik.popularmovies_jkmvvm.BuildConfig;
import com.example.vtarantik.popularmovies_jkmvvm.db.model.Category;
import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieResponse;
import com.example.vtarantik.popularmovies_jkmvvm.entity.ReviewListResponse;
import com.example.vtarantik.popularmovies_jkmvvm.entity.TrailerListResponse;
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
	public Observable<MovieResponse> getMovies(Category category) {
		switch(category){
			case POPULAR:
				return this.apiDescription.getPopularMovies(BuildConfig.MOVIEDB_API_KEY,1);

			case TOP_RATED:
				return this.apiDescription.getTopRatedMovies(BuildConfig.MOVIEDB_API_KEY,1);

			default:
				throw new IllegalArgumentException("Unsupported movie category");
		}
	}


	@Override
	public Observable<TrailerListResponse> getTrailers(long movieId) {
		return this.apiDescription.getTrailers(movieId, BuildConfig.MOVIEDB_API_KEY);
	}


	@Override
	public Observable<ReviewListResponse> getReviews(long movieId) {
		return this.apiDescription.getReviews(movieId,BuildConfig.MOVIEDB_API_KEY);
	}
}
