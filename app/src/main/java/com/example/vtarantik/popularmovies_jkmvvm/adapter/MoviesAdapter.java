package com.example.vtarantik.popularmovies_jkmvvm.adapter;

/**
 * Created by strv on 06/02/2017.
 */

import com.example.vtarantik.popularmovies_jkmvvm.R;
import com.example.vtarantik.popularmovies_jkmvvm.databinding.MovieTileBinding;
import com.example.vtarantik.popularmovies_jkmvvm.view.IMovieListView;
import com.example.vtarantik.popularmovies_jkmvvm.viewmodel.MovieListFragmentViewModel;


/**
 * This is an example of a data bound adapter use case where all items have the same type.
 * <p>
 * The parent class handles the item creation and this child class only implements the
 * bindItem to set values in a type checked way.
 */
public class MoviesAdapter extends SimpleDataBoundAdapter<MovieTileBinding> {

	public MoviesAdapter(IMovieListView view, MovieListFragmentViewModel viewModel) {
		super(R.layout.movie_tile, view, viewModel.movies);
	}

}