package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.databinding.ObservableField;
import android.os.Bundle;

import com.example.vtarantik.popularmovies_jkmvvm.activity.MovieDetailActivity;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;

import cz.kinst.jakub.viewmodelbinding.ViewModel;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieDetailFragmentViewModel extends ViewModel {

	public final ObservableField<Movie> movie = new ObservableField<>();

}
