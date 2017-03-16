package com.example.vtarantik.popularmovies_jkmvvm.interactor;

import com.example.vtarantik.popularmovies_jkmvvm.rest.ApiDescription;


/**
 * Created by vtarantik on 14/03/2017.
 */

public class ApiInteractorFactory {
	public static final String TAG = ApiInteractorFactory.class.getName();

	private ApiInteractorFactory() {
	}

	public static IApiInteractor provideApiInteractor(ApiDescription apiDescription) {
		return new ApiInteractor(apiDescription);
	}
}
