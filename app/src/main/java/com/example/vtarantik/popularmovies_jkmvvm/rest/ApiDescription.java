package com.example.vtarantik.popularmovies_jkmvvm.rest;

import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by vtarantik on 14/03/2017.
 */

public interface ApiDescription {

	String ENDPOINT_URL = "https://api.themoviedb.org/";

	@GET("3/movie/popular")
	Observable<MovieResponse> getPopularMovies(
			@Query("api_key") String apiKey,
			@Query("page") int pageIndex
	);

	@GET("3/search/top_rated")
	Observable<MovieResponse> getTopRatedMovies(
			@Query("api_key") String apiKey,
			@Query("page") int pageIndex
	);
}
