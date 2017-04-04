package com.example.vtarantik.popularmovies_jkmvvm.view;

import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.view.base.IClickableListItemView;

import java.util.List;


/**
 * Created by vtarantik on 14/03/2017.
 */

public interface IMovieListView extends IClickableListItemView<Movie> {

	void showData(List<Movie> movies);
}
