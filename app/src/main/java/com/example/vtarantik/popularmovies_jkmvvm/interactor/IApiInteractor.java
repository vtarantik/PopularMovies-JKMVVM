package com.example.vtarantik.popularmovies_jkmvvm.interactor;

import com.example.vtarantik.popularmovies_jkmvvm.db.model.Category;
import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieResponse;

import rx.Observable;


/**
 * Created by vtarantik on 14/03/2017.
 */

public interface IApiInteractor {
	Observable<MovieResponse> getMovies(Category category);
}
