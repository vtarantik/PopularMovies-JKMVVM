package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.databinding.ObservableField;

import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;

import cz.kinst.jakub.viewmodelbinding.ViewModel;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieDetailActivityViewModel extends ViewModel {

	public final ObservableField<Movie> movie = new ObservableField<>();

}
