package com.example.vtarantik.popularmovies_jkmvvm.viewmodel;

import android.content.ContentValues;
import android.databinding.Observable;
import android.databinding.ObservableField;

import com.example.vtarantik.popularmovies_jkmvvm.db.contract.FavouriteMoviesContract;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.PopularMoviesContract;
import com.example.vtarantik.popularmovies_jkmvvm.db.helper.CursorHandler;
import com.example.vtarantik.popularmovies_jkmvvm.db.helper.FavouriteCursorHandler;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;

import cz.kinst.jakub.viewmodelbinding.ViewModel;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieDetailFragmentViewModel extends ViewModel {

	public final ObservableField<Movie> movie = new ObservableField<>();

	public final ObservableField<Boolean> favourite = new ObservableField<>();


	@Override
	public void onViewModelCreated() {
		super.onViewModelCreated();

		CursorHandler<Boolean> handler = new FavouriteCursorHandler();

		boolean isFav = handler.handle(getActivity().getContentResolver().query(FavouriteMoviesContract.FavouriteMoviesEntry.FAVOURITE,null,FavouriteMoviesContract.FavouriteMoviesEntry._ID + "=" + movie.get().getId(),null,null),null);

		favourite.set(isFav);

		favourite.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
			@Override
			public void onPropertyChanged(Observable observable, int i) {
				boolean checked = (boolean) ((ObservableField) observable).get();

				if(checked) {
					ContentValues contentValues = new ContentValues();

					contentValues.put(FavouriteMoviesContract.FavouriteMoviesEntry._ID, movie.get().getId());
					contentValues.put(FavouriteMoviesContract.FavouriteMoviesEntry.COL_FAVOURITE, checked ? 1 : 0);

					getActivity().getContentResolver().insert(FavouriteMoviesContract.FavouriteMoviesEntry.FAVOURITE, contentValues);
				} else {
					getActivity().getContentResolver().delete(FavouriteMoviesContract.FavouriteMoviesEntry.FAVOURITE, FavouriteMoviesContract.FavouriteMoviesEntry._ID + "=" + movie.get().getId(), null);
				}
			}
		});
	}

}
