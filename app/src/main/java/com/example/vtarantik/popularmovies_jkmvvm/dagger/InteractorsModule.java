package com.example.vtarantik.popularmovies_jkmvvm.dagger;

import com.example.vtarantik.popularmovies_jkmvvm.interactor.ApiInteractorFactory;
import com.example.vtarantik.popularmovies_jkmvvm.interactor.IApiInteractor;
import com.example.vtarantik.popularmovies_jkmvvm.rest.ApiDescription;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by vtarantik on 14/03/2017.
 */

@Module(
		includes = {ServiceModule.class}
)
public class InteractorsModule {
	public static final String TAG = InteractorsModule.class.getName();

	@Provides
	@Singleton
	public IApiInteractor provideApiInteractor(ApiDescription apiDescription) {
		return ApiInteractorFactory.provideApiInteractor(apiDescription);
	}
}
