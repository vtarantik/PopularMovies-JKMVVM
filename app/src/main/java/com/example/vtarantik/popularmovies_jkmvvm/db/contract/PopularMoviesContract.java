package com.example.vtarantik.popularmovies_jkmvvm.db.contract;

import android.provider.BaseColumns;


/**
 * Created by vtarantik on 28/03/2017.
 */

public class PopularMoviesContract {

	public static final class PopularMoviesEntry implements BaseColumns{
		public static final String TABLE_NAME = "popular_movies";

		public static final String COL_POPULAR = "popular";
	}
}
