package com.bepo.ui;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pullrefresh.PullToRefreshListView;
import com.simas.R;
import com.simas.adapter.ListAdapter;
import com.simas.base.ApplicationController;
import com.simas.model.MainListBean;

public class EventTodo extends Fragment {
	TextView tvCancel, tvShengpi, tvYanshi;
	private ListView mListView;
	private PullToRefreshListView mPullListView;

	public ArrayList<MainListBean> data;
	private ListAdapter<MainListBean> mListAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.event_todo, container, true);
		getData();
		mPullListView = (PullToRefreshListView) view.findViewById(R.id.listView);
		mListView = mPullListView.getRefreshableView();
		mListView.setCacheColorHint(Color.TRANSPARENT);

		mListAdapter = new ListAdapter<MainListBean>(data, mListView, getActivity());
		mListView.setAdapter(mListAdapter);
		return view;
	}

	private void getData() {
		data = new ArrayList<MainListBean>();
		String url = "http://192.168.2.77:8601/EEventApp.app?Method=ExecuteQueryEvents&grid_code=01&sortname=code&rp=10&page=1&sortorder=desc  ";

		StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				String jsondata = response.toString();
				jsondata.trim();

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);
			}
		});
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}
}
