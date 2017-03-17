package com.example.vtarantik.popularmovies_jkmvvm.utility;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Created by vtarantik on 15/03/2017.
 */


public final class BindingUtility {

	private static final String POSTER_PATH = "http://image.tmdb.org/t/p/w185";

	@BindingAdapter({"bind:imageUrl"})
	public static void loadImage(ImageView view, String url) {
		Picasso.with(view.getContext())
				.load(POSTER_PATH + url)
//				.transform(new CircleTransform())
				.into(view);
	}


	@BindingAdapter({"bind:posterUrl"})
	public static void loadPoster(ImageView view, String url) {
		Picasso.with(view.getContext())
				.load(POSTER_PATH + url)
				.fit().centerCrop()
				.into(view);

	}
}
