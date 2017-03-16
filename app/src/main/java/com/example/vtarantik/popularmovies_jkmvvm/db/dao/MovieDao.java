package com.example.vtarantik.popularmovies_jkmvvm.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;
import com.example.vtarantik.popularmovies_jkmvvm.entity.MovieMapper;
import com.hannesdorfmann.sqlbrite.dao.Dao;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import rx.Observable;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieDao extends Dao {
	private static final String TAG = MovieDao.class.getSimpleName();

	public Observable<Boolean> insertInBatch(List<Movie> data) {
		return Observable.create(sub -> {
			BriteDatabase.Transaction t = newTransaction();
			try {
				for(Movie d: data) {
					insertItem(d);
				}
				t.markSuccessful();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				t.end();
			}
			sub.onNext(true);
			sub.onCompleted();
		});
	}

	@Override
	public void createTable(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE(Movie.TABLE_NAME,
				Movie.COL_ID + " INTEGER PRIMARY KEY",
				Movie.COL_TITLE + " TEXT",
				Movie.COL_OVERVIEW + " TEXT",
				Movie.COL_RELEASE_DATE + " TEXT",
				Movie.COL_VOTE_COUNT + " INTEGER",
				Movie.COL_RATING + " REAL",
				Movie.COL_POPULARITY + " REAL",
				Movie.COL_BACKDROP + " TEXT",
				Movie.COL_POSTER + " TEXT")
				.getSql());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public long insertItem(Movie movie) {
		return db.insert(Movie.TABLE_NAME,
				MovieMapper.contentValues()
						.id(movie.getId())
						.title(movie.getTitle())
						.overview(movie.getOverview())
						.releaseDate(movie.getReleaseDate())
						.voteCount(movie.getVoteCount())
						.rating(movie.getRating())
						.popularity(movie.getPopularity())
						.backdrop(movie.getBackdrop())
						.poster(movie.getPoster())
						.build(), SQLiteDatabase.CONFLICT_REPLACE);
	}

	public Observable<List<Movie>> getPopularMovies() {
		return query(SELECT("*").FROM(Movie.TABLE_NAME).ORDER_BY(Movie.COL_POPULARITY+" DESC"))
				.run()
				.mapToList(MovieMapper.MAPPER);
	}

	public Observable<List<Movie>> getTopRatedMovies() {
		return query(SELECT("*").FROM(Movie.TABLE_NAME).ORDER_BY(Movie.COL_POPULARITY+" DESC"))
				.run()
				.mapToList(MovieMapper.MAPPER);
	}

	public Observable<List<Movie>> getFavouriteMovies() {
		return query(SELECT("*").FROM(Movie.TABLE_NAME).ORDER_BY(Movie.COL_POPULARITY+" DESC"))
				.run()
				.mapToList(MovieMapper.MAPPER);
	}

	public long clearTable() {
		return db.delete(Movie.TABLE_NAME, null);
	}
}
