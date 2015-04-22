package com.bepo.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

import com.simas.R;
import com.widget.customviewpager.TabSwipPager;

public class MainActivity extends FragmentActivity {

	private LinearLayout llTabSwipPager;
	private TabSwipPager tabSwipPager;

	private ArrayList<Fragment> fragmentsList;
	private String[] tags;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		initData();

		llTabSwipPager = (LinearLayout) findViewById(R.id.llTabSwipPager);

		tabSwipPager = new TabSwipPager(getApplicationContext(), getSupportFragmentManager(), llTabSwipPager);
		if (!tabSwipPager.setFragmentList(fragmentsList, tags)) {
			System.out.println("初始化失败");
			finish();
		}
	}

	private void initData() {
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(new AFragment());
		fragmentsList.add(new AFragment());
		fragmentsList.add(new AFragment());

		tags = new String[] { "战机A", "战机B", "战机C" };

	}
}
