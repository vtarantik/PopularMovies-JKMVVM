package com.example.vtarantik.popularmovies_jkmvvm.entity;

import java.util.List;


/**
 * Created by vtarantik on 23/03/2017.
 */

public class TrailerListResponse {

	private int id;

	private List<Trailer> results;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Trailer> getResults() {
		return results;
	}


	public void setResults(List<Trailer> results) {
		this.results = results;
	}
}
