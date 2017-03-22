package com.example.vtarantik.popularmovies_jkmvvm.db.model;

/**
 * Created by vtarantik on 22/03/2017.
 */

public enum Category {
	POPULAR(Popular.TABLE_NAME),
	TOP_RATED(TopRated.TABLE_NAME),
	FAVOURITE(Favourite.TABLE_NAME);

	private String tableName;

	Category(String tableName){
		this.tableName = tableName;
	}


	public String getTableName() {
		return tableName;
	}
}
