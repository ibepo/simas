package com.simas.ui;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.github.johnpersano.supertoasts.SuperToast;
import com.pullrefresh.PullToRefreshListView;
import com.simas.R;
import com.simas.adapter.EventAdapter;
import com.simas.base.ApplicationController;
import com.simas.base.BaseAct;
import com.simas.base.PathConfig;
import com.simas.model.EventListBean;
import com.simas.model.MainListBean;
import com.simas.utils.ToastUtils;

public class testsuperlistview extends BaseAct {
	Context context;

	ListView listView;
	PullToRefreshListView mPullToRefreshListView;

	public ArrayList<EventListBean> data = new ArrayList<EventListBean>();// 区领导
	private EventAdapter<EventListBean> mListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.testpulltodata);
		initView();
		getData();

	}

	private void initView() {
		mPullToRefreshListView = (PullToRefreshListView) this.findViewById(R.id.listView);
		listView = mPullToRefreshListView.getRefreshableView();
		mListAdapter = new EventAdapter<EventListBean>(data, listView, testsuperlistview.this);
		listView.setAdapter(mListAdapter);
	}

	private void getData() {
		String leaderUrl = "http://" + PathConfig.IP
				+ "/EEventApp.app?Method=ExecuteQueryEvents&grid_code=01&sortname=code&rp=10&page=1&sortorder=desc";
		StringRequest stringRequest = new StringRequest(leaderUrl, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				String jsondata = response.toString();
				MainListBean temp = (MainListBean) JSON.parseObject(jsondata, MainListBean.class);
				data = (ArrayList<EventListBean>) temp.getRows();
				mListAdapter.addData(data);
				ToastUtils.showSuperToastComment(context, "加载完成");
				ToastUtils.showSuperToastAlert(context, "O(∩_∩)O~警告警告，陆地进攻，陆地进攻！！！O(∩_∩)O~");
			}
		}, null);
		ApplicationController.getInstance().addToRequestQueue(stringRequest);

	}

}
