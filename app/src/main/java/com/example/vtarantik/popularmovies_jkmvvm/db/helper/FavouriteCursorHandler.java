package com.example.vtarantik.popularmovies_jkmvvm.db.helper;

import android.database.Cursor;
import android.database.SQLException;

import com.example.vtarantik.popularmovies_jkmvvm.db.model.Favourite;


/**
 * Created by vtarantik on 03/04/2017.
 */

public class FavouriteCursorHandler implements CursorHandler<Boolean> {
	@Override
	public Boolean handle(Cursor cursor, MovieCursorHandler.EmptyCursorCallback callback) throws SQLException {
		if(cursor == null||cursor.getCount()==0) {
			return false;
		}else{
			return true;
		}
	}
}
