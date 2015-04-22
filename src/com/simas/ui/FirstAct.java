package com.simas.ui;

import android.os.Bundle;

import com.simas.R;
import com.simas.base.BaseAct;

public class FirstAct extends BaseAct {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.weather);
		overridePendingTransition(R.anim.slide_bottom_in, R.anim.slide_top_out);

	}

}
