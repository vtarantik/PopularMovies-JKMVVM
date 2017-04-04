package com.example.vtarantik.popularmovies_jkmvvm.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.vtarantik.popularmovies_jkmvvm.db.model.Favourite;
import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.Column;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.ObjectMappable;


/**
 * Created by vtarantik on 14/03/2017.
 */

@ObjectMappable
public class Movie implements Parcelable {
	private static final String TAG = Movie.class.getSimpleName();

	public static final String TABLE_NAME = "movies";
	public static final String COL_ID = "_id";
	public static final String COL_TITLE= "title";
	public static final String COL_OVERVIEW = "overview";
	public static final String COL_RELEASE_DATE = "release_date";
	public static final String COL_VOTE_COUNT = "vote_count";
	public static final String COL_RATING= "rating";
	public static final String COL_POPULARITY= "popularity";
	public static final String COL_BACKDROP= "backdrop";
	public static final String COL_POSTER= "poster";

	@Column(COL_ID)
	private long id;

	@Column(COL_TITLE)
	private String title;

	@Column(COL_OVERVIEW)
	private String overview;

	@Column(COL_RELEASE_DATE)
	private String releaseDate;

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	@Column(COL_VOTE_COUNT)
	@SerializedName("vote_count")
	private int voteCount;

	@Column(COL_RATING)
	@SerializedName("vote_average")
	private double rating;

	@Column(COL_POPULARITY)
	private double popularity;

	@Column(COL_BACKDROP)
	@SerializedName("backdrop_path")
	private String backdrop;

	@Column(COL_POSTER)
	@SerializedName("poster_path")
	private String poster;

	@Column(value = Favourite.COL_FAVOURITE, throwOnColumnIndexNotFound = false)
	int favourite;


	public Movie() {
	}

	public Movie(long id, String title, String overview, String releaseDate, double rating,double popularity, String poster) {
		this.id = id;
		this.title = title;
		this.overview = overview;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.popularity = popularity;
		this.poster = poster;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public String getBackdrop() {
		return backdrop;
	}

	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public boolean isFavourite() {
		return favourite > 0;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite ? 1 : 0;
	}


	@Override
	public int describeContents() { return 0; }


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.id);
		dest.writeString(this.title);
		dest.writeString(this.overview);
		dest.writeString(this.releaseDate);
		dest.writeInt(this.voteCount);
		dest.writeDouble(this.rating);
		dest.writeDouble(this.popularity);
		dest.writeString(this.backdrop);
		dest.writeString(this.poster);
		dest.writeInt(this.favourite);
	}


	protected Movie(Parcel in) {
		this.id = in.readLong();
		this.title = in.readString();
		this.overview = in.readString();
		this.releaseDate = in.readString();
		this.voteCount = in.readInt();
		this.rating = in.readDouble();
		this.popularity = in.readDouble();
		this.backdrop = in.readString();
		this.poster = in.readString();
		this.favourite = in.readInt();
	}


	public static final Creator<Movie> CREATOR = new Creator<Movie>() {
		@Override
		public Movie createFromParcel(Parcel source) {return new Movie(source);}


		@Override
		public Movie[] newArray(int size) {return new Movie[size];}
	};
}
