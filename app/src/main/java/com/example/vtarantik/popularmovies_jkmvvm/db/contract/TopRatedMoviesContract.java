package com.example.vtarantik.popularmovies_jkmvvm.db.contract;

import android.provider.BaseColumns;


/**
 * Created by vtarantik on 28/03/2017.
 */

public class TopRatedMoviesContract {

	public static final class TopRatedMoviesEntry implements BaseColumns{
		public static final String TABLE_NAME = "top_rated_books";

		public static final String COL_TOP_RATED = "top_rated";
	}
}
