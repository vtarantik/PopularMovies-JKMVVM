package com.example.vtarantik.popularmovies_jkmvvm.entity;

import java.util.List;


/**
 * Created by vtarantik on 23/03/2017.
 */

public class ReviewListResponse {

	private int id;

	private int page;

	private int totalPages;

	private int totalResults;

	private List<Review> results;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public int getTotalResults() {
		return totalResults;
	}


	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}


	public List<Review> getResults() {
		return results;
	}


	public void setResults(List<Review> results) {
		this.results = results;
	}
}
