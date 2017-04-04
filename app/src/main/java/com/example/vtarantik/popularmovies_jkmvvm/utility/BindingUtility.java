package com.example.vtarantik.popularmovies_jkmvvm.utility;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Created by vtarantik on 15/03/2017.
 */


public final class BindingUtility {

	private static final String POSTER_PATH = "http://image.tmdb.org/t/p/w185";

	public enum RecyclerDecoration
	{
		LINEAR_DIVIDER
	}

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

	@BindingAdapter({"recyclerDecoration"})
	public static void setRecyclerDecoration(RecyclerView recyclerView, RecyclerDecoration recyclerDecoration)
	{
		RecyclerView.ItemDecoration itemDecoration;

		if(recyclerDecoration == RecyclerDecoration.LINEAR_DIVIDER)
		{
			itemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
		}
		else
		{
			throw new IllegalArgumentException();
		}

		recyclerView.addItemDecoration(itemDecoration);
	}
}
