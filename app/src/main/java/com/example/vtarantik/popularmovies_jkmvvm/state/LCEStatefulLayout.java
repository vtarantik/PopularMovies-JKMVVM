package com.example.vtarantik.popularmovies_jkmvvm.state;

import android.content.Context;
import android.util.AttributeSet;

import cz.kinst.jakub.view.SimpleStatefulLayout;


/**
 * Created by vtarantik on 21/03/2017.
 */

public class LCEStatefulLayout extends SimpleStatefulLayout {
	public LCEStatefulLayout(Context context) {
		super(context);
	}


	public LCEStatefulLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	public LCEStatefulLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	public void clearStates() {
		//nothing here
	}
}
