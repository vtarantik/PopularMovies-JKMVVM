package com.example.vtarantik.popularmovies_jkmvvm.db.helper;

import android.database.Cursor;
import android.database.SQLException;


/**
 * Created by vtarantik on 30/03/2017.
 */

public interface CursorHandler<T> {
	T handle(Cursor cursor, MovieCursorHandler.EmptyCursorCallback callback) throws SQLException;
}
