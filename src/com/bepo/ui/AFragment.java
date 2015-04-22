package com.bepo.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pullrefresh.PullToRefreshListView;
import com.simas.R;
import com.simas.adapter.EventAdapter;
import com.simas.base.ApplicationController;
import com.simas.model.EventListBean;
import com.simas.model.MainListBean;

public class AFragment extends Fragment {

	PullToRefreshListView mPullToRefreshListView;
	ListView mListView;

	public ArrayList<EventListBean> data = new ArrayList<EventListBean>();
	private EventAdapter<EventListBean> mListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getData();
	}

	private ArrayList<EventListBean> getData() {
		String url = "http://192.168.2.77:8601/EEventApp.app?Method=ExecuteQueryEvents&grid_code=01&sortname=code&rp=10&page=1&sortorder=desc  ";
		StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				String jsondata = response.toString().trim();
				MainListBean temp = (MainListBean) JSON.parseObject(jsondata, MainListBean.class);
				String sss = temp.getPage();
				data = (ArrayList<EventListBean>) temp.getRows();
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);
			}
		});
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
		return data;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.event_is_end, container, false);
		mListView = (ListView) view.findViewById(R.id.mlistView);
		mListAdapter = new EventAdapter(data, getActivity());
		mListView.setAdapter(mListAdapter);
		return view;
	}
}