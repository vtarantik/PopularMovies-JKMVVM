package com.example.vtarantik.popularmovies_jkmvvm.view.base;

import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;


/**
 * Created by vtarantik on 23/03/2017.
 */

public interface IClickableListItemView<T> {
	void onItemClick(T entity);
}
