package com.example.vtarantik.popularmovies_jkmvvm.rest;

import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieResponse;
import com.example.vtarantik.popularmovies_jkmvvm.entity.ReviewListResponse;
import com.example.vtarantik.popularmovies_jkmvvm.entity.TrailerListResponse;

import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
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


	@GET("3/movie/top_rated")
	Observable<MovieResponse> getTopRatedMovies(
			@Query("api_key") String apiKey,
			@Query("page") int pageIndex
	);


	@GET("3/movie/{movieId}/videos")
	Observable<TrailerListResponse> getTrailers(
			@Path("movieId") long movieId,
			@Query("api_key") String apiKey
	);


	@GET("3/movie/{movieId}/reviews")
	Observable<ReviewListResponse> getReviews(
			@Path("movieId") long movieId,
			@Query("api_key") String apiKey
	);
}
