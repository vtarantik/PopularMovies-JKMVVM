package com.example.vtarantik.popularmovies_jkmvvm.db.helper;

import android.database.Cursor;
import android.database.SQLException;

import com.example.vtarantik.popularmovies_jkmvvm.db.contract.FavouriteMoviesContract;
import com.example.vtarantik.popularmovies_jkmvvm.db.contract.MoviesContract;
import com.example.vtarantik.popularmovies_jkmvvm.entity.Movie;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vtarantik on 30/03/2017.
 */

public class MovieCursorHandler implements CursorHandler<List<Movie>> {

	@Override
	public List<Movie> handle(Cursor cursor,EmptyCursorCallback emptyCursorCallback) throws SQLException {
		List<Movie> result = new ArrayList<>();
		if(cursor == null) {
			emptyCursorCallback.onEmptyCursor();
		} else {
			while(cursor.moveToNext()) {
				int id = cursor.getInt(cursor.getColumnIndex(MoviesContract.MoviesEntry._ID));
				String title = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_TITLE));
				String description = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_OVERVIEW));
				String poster = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_POSTER));
				double popularity = cursor.getDouble(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_POPULARITY));
				double rating = cursor.getDouble(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_RATING));
				String releaseDate = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_RELEASE_DATE));
				int voteCount = cursor.getInt(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_VOTE_COUNT));
				String backDrop = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_BACKDROP));
				boolean favourite = cursor.getInt(cursor.getColumnIndex(FavouriteMoviesContract.FavouriteMoviesEntry.COL_FAVOURITE)) == 0 ? false : true;


				Movie movie = new Movie();
				movie.setId(id);
				movie.setTitle(title);
				movie.setOverview(description);
				movie.setPoster(poster);
				movie.setPopularity(popularity);
				movie.setRating(rating);
				movie.setReleaseDate(releaseDate);
				movie.setVoteCount(voteCount);
				movie.setBackdrop(backDrop);
				movie.setFavourite(favourite);


				result.add(movie);
			}
		}
		if(result.isEmpty()){
			emptyCursorCallback.onEmptyCursor();
		}

		return result;
	}

	public interface EmptyCursorCallback{
		void onEmptyCursor();
	}
}
