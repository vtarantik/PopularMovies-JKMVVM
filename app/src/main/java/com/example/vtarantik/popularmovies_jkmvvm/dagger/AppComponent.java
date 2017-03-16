package com.example.vtarantik.popularmovies_jkmvvm.dagger;

import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.MovieListFragmentViewModel;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by vtarantik on 14/03/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

	void inject(MovieListFragmentViewModel moviesViewModel);
}