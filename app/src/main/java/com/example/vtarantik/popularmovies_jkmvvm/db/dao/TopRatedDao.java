package com.example.vtarantik.popularmovies_jkmvvm.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.example.vtarantik.popularmovies_jkmvvm.db.model.TopRated;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.hannesdorfmann.sqlbrite.dao.Dao;


/**
 * Created by vtarantik on 22/03/2017.
 */

public class TopRatedDao extends Dao{
	@Override
	public void createTable(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE(TopRated.TABLE_NAME,
				Movie.COL_ID + " INTEGER PRIMARY KEY",
				TopRated.COL_FAVOURITE+ " INTEGER DEFAULT 1"
		).getSql());
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
