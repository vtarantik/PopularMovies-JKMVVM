package com.example.vtarantik.popularmovies_jkmvvm.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class MovieResponse {

	private int page;

	@SerializedName("results")
	private List<Movie> movies;

	@SerializedName("total_results")
	private int totalResults;

	@SerializedName("total_pages")
	private int total_pages;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
}
