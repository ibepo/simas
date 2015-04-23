package com.simas.ui;

import android.os.Bundle;

import com.finddreams.graygridView.MyGridView;
import com.simas.R;
import com.simas.adapter.MyGridAdapter;
import com.simas.base.BaseAct;

public class MainAct extends BaseAct {
	private MyGridView gridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_jiugongge);
		initView();
	}

	private void initView() {
		gridview = (MyGridView) findViewById(R.id.gridview);
		gridview.setAdapter(new MyGridAdapter(this));

	}

}
