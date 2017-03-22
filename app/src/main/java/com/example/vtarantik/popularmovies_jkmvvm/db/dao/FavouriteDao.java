package com.example.vtarantik.popularmovies_jkmvvm.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.example.vtarantik.popularmovies_jkmvvm.db.model.Favourite;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieMapper;
import com.hannesdorfmann.sqlbrite.dao.Dao;

import rx.Observable;


/**
 * Created by vtarantik on 22/03/2017.
 */

public class FavouriteDao extends Dao{
	@Override
	public void createTable(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE(Favourite.TABLE_NAME,
				Movie.COL_ID + " INTEGER PRIMARY KEY",
				Favourite.COL_FAVOURITE+ " INTEGER DEFAULT 1"
		).getSql());
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


	public Observable<Void> setFavourite(int movieId, boolean isFavourite){
		if(isFavourite){
			return insert(Favourite.TABLE_NAME, MovieMapper.contentValues().id(movieId).build(),SQLiteDatabase.CONFLICT_REPLACE).map(id -> null);
		}

		return delete(Favourite.TABLE_NAME,Movie.COL_ID + " = ?",String.valueOf(movieId)).map(id -> null);
	}
}
