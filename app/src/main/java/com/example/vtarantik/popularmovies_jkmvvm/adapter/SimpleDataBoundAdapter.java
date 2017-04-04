package com.example.vtarantik.popularmovies_jkmvvm.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

import com.example.vtarantik.popularmovies_jkmvvm.BR;
import com.example.vtarantik.popularmovies_jkmvvm.view.base.IClickableListItemView;

import java.util.List;



/**
 * Created by strv on 08/02/2017.
 */

abstract public class SimpleDataBoundAdapter<T extends ViewDataBinding> extends BaseDataBoundAdapter<T> {

	@LayoutRes private int mLayoutId;
	private IClickableListItemView mView;
	private ObservableArrayList<?> mItems;


	public SimpleDataBoundAdapter(@LayoutRes int layoutId, IClickableListItemView view, ObservableArrayList<?> items) {
		mLayoutId = layoutId;
		mView = view;
		mItems = items;
	}


	@Override
	protected void bindItem(DataBoundViewHolder<T> holder, int position, List<Object> payloads) {
		holder.binding.setVariable(BR.view,mView);
		holder.binding.setVariable(BR.data,mItems.get(position));
	}


	@Override
	public int getItemLayoutId(int position) {
		return mLayoutId;
	}


	@Override
	public int getItemCount() {
		return mItems.size();
	}
}
