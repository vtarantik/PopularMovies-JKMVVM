package com.example.vtarantik.popularmovies_jkmvvm.db.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.vtarantik.popularmovies_jkmvvm.db.contract.FavouriteMoviesContract;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.FavouriteMoviesContract.FavouriteMoviesEntry;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.MoviesContract;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.MoviesContract.MoviesEntry;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.PopularMoviesContract;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.TopRatedMoviesContract;
import com.example.vtarantik.popularmovies_jkmvvm.db.helper.MovieDBHelper;
import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieMapper;


/**
 * Created by vtarantik on 28/03/2017.
 */

public class MoviesProvider extends ContentProvider {
	public static final int FAVOURITE_MOVIES = 100;

	public static final int POPULAR_MOVIES = 200;

	public static final int TOP_RATED_MOVIES = 300;

	public static final int FAVOURITE = 400;
	private static final String TAG = MoviesProvider.class.getName();


	public static UriMatcher buildUriMatcher() {
		UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_FAVOURITE_MOVIES, FAVOURITE_MOVIES);
		uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_POPULAR_MOVIES, POPULAR_MOVIES);
		uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_TOP_RATED_MOVIES, TOP_RATED_MOVIES);
		uriMatcher.addURI(FavouriteMoviesContract.AUTHORITY, FavouriteMoviesContract.PATH_FAVOURITE_MOVIES, FAVOURITE);


		return uriMatcher;
	}


	private MovieDBHelper movieDBHelper;


	@Override
	public boolean onCreate() {
		movieDBHelper = new MovieDBHelper(getContext());

		return true;

	}


	@Nullable
	@Override
	public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
		final SQLiteDatabase db = movieDBHelper.getReadableDatabase();

		int match = buildUriMatcher().match(uri);

		Cursor retCursor;

		String tableName;
		String orderBy;

		switch(match) {
			case FAVOURITE_MOVIES:
				tableName = FavouriteMoviesEntry.TABLE_NAME;
				orderBy = MoviesEntry.COL_RATING;
				break;

			case POPULAR_MOVIES:
				tableName = PopularMoviesContract.PopularMoviesEntry.TABLE_NAME;
				orderBy = MoviesEntry.COL_POPULARITY;
				break;

			case TOP_RATED_MOVIES:
				tableName = TopRatedMoviesContract.TopRatedMoviesEntry.TABLE_NAME;
				orderBy = MoviesEntry.COL_RATING;
				break;

			case FAVOURITE:
				tableName = FavouriteMoviesEntry.TABLE_NAME;
				orderBy = "";
				break;

			default:
				throw new UnsupportedOperationException("Unknown uri");
		}

		if(match != FAVOURITE) {
			String sql = "SELECT * FROM " + MoviesEntry.TABLE_NAME + " NATURAL INNER JOIN " + tableName + " NATURAL LEFT OUTER JOIN " + FavouriteMoviesContract.FavouriteMoviesEntry.TABLE_NAME + " ORDER BY " + orderBy + " DESC";

			retCursor = db.rawQuery(sql, null);

		}else{
			retCursor = db.query(FavouriteMoviesEntry.TABLE_NAME,null,selection,null,null,null,null);
		}


		return retCursor;
	}


	@Nullable
	@Override
	public String getType(@NonNull Uri uri) {
		return null;
	}


	@Nullable
	@Override
	public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

		final SQLiteDatabase db = movieDBHelper.getWritableDatabase();

		int match = buildUriMatcher().match(uri);

		Uri returnUri;

		String categoryTableName;

		switch(match) {
			case FAVOURITE_MOVIES:
				categoryTableName = FavouriteMoviesEntry.TABLE_NAME;
				returnUri = MoviesEntry.FAVOURITE_MOVIES_URI;
				break;

			case TOP_RATED_MOVIES:
				categoryTableName = TopRatedMoviesContract.TopRatedMoviesEntry.TABLE_NAME;
				returnUri = MoviesEntry.TOP_RATED_MOVIES_URI;
				break;

			case POPULAR_MOVIES:
				categoryTableName = PopularMoviesContract.PopularMoviesEntry.TABLE_NAME;
				returnUri = MoviesEntry.POPULAR_MOVIES_URI;
				break;

			case FAVOURITE:
				categoryTableName = FavouriteMoviesEntry.TABLE_NAME;
				returnUri = MoviesEntry.FAVOURITE_MOVIES_URI;
				break;

			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}


		if(match != FAVOURITE) {
			long id = db.insertWithOnConflict(MoviesEntry.TABLE_NAME, null, values,SQLiteDatabase.CONFLICT_REPLACE);

			if(id > 0) {
				db.insertWithOnConflict(categoryTableName, null, MovieMapper.contentValues().id(id).build(),SQLiteDatabase.CONFLICT_REPLACE);

				returnUri = ContentUris.withAppendedId(returnUri, id);
			} else {
				throw new SQLException("Failed to insert row");
			}
		} else {
			long id = db.insertWithOnConflict(categoryTableName, null, values, SQLiteDatabase.CONFLICT_REPLACE);
			Log.d(TAG, "Inserted id: " + id);
		}


//		getContext().getContentResolver().notifyChange(uri, null);

		return returnUri;

	}


	@Override
	public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
		final SQLiteDatabase db = movieDBHelper.getWritableDatabase();

		int match = buildUriMatcher().match(uri);

		switch(match) {
			case FAVOURITE:
				int numRows = db.delete(FavouriteMoviesEntry.TABLE_NAME, selection, selectionArgs);
				return numRows;
		}

		return 0;

	}


	@Override
	public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
		return 0;
	}
}
