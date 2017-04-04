package com.example.vtarantik.popularmovies_jkmvvm.db.contract;

import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by vtarantik on 28/03/2017.
 */

public class FavouriteMoviesContract {
	public static final String AUTHORITY = "com.example.vtarantik.popularmovies_jkmvvm";
	public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	public static final String PATH_FAVOURITE_MOVIES = "favourite";

	public static final class FavouriteMoviesEntry implements BaseColumns{
		public static final Uri FAVOURITE = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITE_MOVIES).build();

		public static final String TABLE_NAME = "favourite_movies";

		public static final String COL_FAVOURITE = "favourite";
	}


}
