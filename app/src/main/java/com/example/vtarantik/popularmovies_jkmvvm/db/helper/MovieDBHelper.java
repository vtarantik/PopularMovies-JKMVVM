package com.example.vtarantik.popularmovies_jkmvvm.db.helper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.vtarantik.popularmovies_jkmvvm.db.contract.FavouriteMoviesContract.FavouriteMoviesEntry;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.MoviesContract.MoviesEntry;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.PopularMoviesContract.PopularMoviesEntry;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.TopRatedMoviesContract.TopRatedMoviesEntry;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieMapper;
import com.example.vtarantik.popularmovies_jkmvvm.utility.Constants;

import java.util.List;

import rx.Observable;


/**
 * Created by vtarantik on 28/03/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

	public MovieDBHelper(Context context) {
		super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create tasks table (careful to follow SQL formatting rules)
		final String CREATE_TABLE_MOVIES = "CREATE TABLE " + MoviesEntry.TABLE_NAME + " (" +
				MoviesEntry._ID + " INTEGER PRIMARY KEY, " +
				MoviesEntry.COL_TITLE + " TEXT NOT NULL, " +
				MoviesEntry.COL_OVERVIEW + " TEXT NOT NULL, " +
				MoviesEntry.COL_RELEASE_DATE + " TEXT NOT NULL, " +
				MoviesEntry.COL_VOTE_COUNT + " INTEGER NOT NULL, " +
				MoviesEntry.COL_RATING + " REAL NOT NULL, " +
				MoviesEntry.COL_POPULARITY + " REAL NOT NULL, " +
				MoviesEntry.COL_BACKDROP + " TEXT NOT NULL, " +
				MoviesEntry.COL_POSTER + " TEXT NOT NULL);";

		db.execSQL(CREATE_TABLE_MOVIES);


		final String CREATE_TABLE_POPULAR_MOVIES = "CREATE TABLE " + PopularMoviesEntry.TABLE_NAME + " (" +
				PopularMoviesEntry._ID + " INTEGER PRIMARY KEY, " +
				PopularMoviesEntry.COL_POPULAR + " INTEGER DEFAULT 1);";

		db.execSQL(CREATE_TABLE_POPULAR_MOVIES);

		final String CREATE_TABLE_TOP_RATED_MOVIES = "CREATE TABLE " + TopRatedMoviesEntry.TABLE_NAME + " (" +
				TopRatedMoviesEntry._ID + " INTEGER PRIMARY KEY, " +
				TopRatedMoviesEntry.COL_TOP_RATED + " INTEGER DEFAULT 1);";

		db.execSQL(CREATE_TABLE_TOP_RATED_MOVIES);

		final String CREATE_TABLE_FAVOURITE_MOVIES = "CREATE TABLE " + FavouriteMoviesEntry.TABLE_NAME + " (" +
				FavouriteMoviesEntry._ID + " INTEGER PRIMARY KEY, " +
				FavouriteMoviesEntry.COL_FAVOURITE + " INTEGER DEFAULT 1);";

		db.execSQL(CREATE_TABLE_FAVOURITE_MOVIES);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + MoviesEntry.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + PopularMoviesEntry.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + FavouriteMoviesEntry.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TopRatedMoviesEntry.TABLE_NAME);
		onCreate(db);
	}


	public static Observable<List<Movie>> queryInBackground(
			final ContentResolver contentResolver,
			final Uri uri,
			final String[] projection,
			final String selection,
			final String[] selectionArgs,
			final String sortOrder,
			final CursorHandler<List<Movie>> cursorHandler,
			final MovieCursorHandler.EmptyCursorCallback callback
	) {
		return Observable.create(subscriber -> {
			if(!subscriber.isUnsubscribed()) {
				Cursor cursor = null;
				try {
					cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
					if(cursor != null && cursor.getCount() > 0) {
						subscriber.onNext(cursorHandler.handle(cursor, callback));
					}
					subscriber.onCompleted();
				} catch(Exception err) {
					subscriber.onError(err);

				} finally {
					if(cursor != null) cursor.close();
				}
			}
		});
	}


	public static Observable<List<Movie>> insertInBackground(
			final ContentResolver contentResolver,
			final Uri uri,
			final List<Movie> movies
	) {
		return Observable.create(subscriber -> {
			if(!subscriber.isUnsubscribed()) {
				Cursor cursor = null;
				try {
					for(Movie movie: movies){
						contentResolver.insert(uri,MovieMapper.contentValues()
								.id(movie.getId())
								.title(movie.getTitle())
								.overview(movie.getOverview())
								.releaseDate(movie.getReleaseDate())
								.voteCount(movie.getVoteCount())
								.rating(movie.getRating())
								.popularity(movie.getPopularity())
								.backdrop(movie.getBackdrop())
								.poster(movie.getPoster())
								.build());
					}
					subscriber.onNext(movies);
					subscriber.onCompleted();
				} catch(Exception err) {
					subscriber.onError(err);

				} finally {
					if(cursor != null) cursor.close();
				}
			}
		});
	}
}
