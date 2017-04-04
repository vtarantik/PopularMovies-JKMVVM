package com.example.vtarantik.popularmovies_jkmvvm.db.contract;

import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by vtarantik on 28/03/2017.
 */

public class MoviesContract {
	public static final String AUTHORITY = "com.example.vtarantik.popularmovies_jkmvvm";
	public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	public static final String PATH_FAVOURITE_MOVIES = "favourite_movies";
	public static final String PATH_POPULAR_MOVIES = "popular_movies";
	public static final String PATH_TOP_RATED_MOVIES = "top_rated_movies";


	public static final class MoviesEntry implements BaseColumns{

		public static final Uri POPULAR_MOVIES_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POPULAR_MOVIES).build();
		public static final Uri FAVOURITE_MOVIES_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITE_MOVIES).build();
		public static final Uri TOP_RATED_MOVIES_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TOP_RATED_MOVIES).build();


		public static final String TABLE_NAME = "movies";
		public static final String COL_TITLE= "title";
		public static final String COL_OVERVIEW = "overview";
		public static final String COL_RELEASE_DATE = "release_date";
		public static final String COL_VOTE_COUNT = "vote_count";
		public static final String COL_RATING= "rating";
		public static final String COL_POPULARITY= "popularity";
		public static final String COL_BACKDROP= "backdrop";
		public static final String COL_POSTER= "poster";
	}
}
