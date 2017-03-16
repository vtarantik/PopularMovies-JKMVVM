package com.example.vtarantik.popularmovies_jkmvvm.interactor;

import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieResponse;

import rx.Observable;


/**
 * Created by vtarantik on 14/03/2017.
 */

public interface IApiInteractor {
	public Observable<MovieResponse> getPopularMovies();
}
