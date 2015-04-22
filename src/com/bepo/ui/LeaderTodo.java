package com.bepo.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.simas.R;
import com.simas.adapter.IsEndListAdapter;
import com.simas.adapter.ListAdapter;
import com.simas.adapter.TodoListAdapter;
import com.simas.base.ApplicationController;
import com.simas.base.BaseAct;
import com.simas.base.PathConfig;
import com.simas.model.EventIsEndBean;
import com.simas.model.EventIsEndMainListBean;
import com.simas.model.EventListBean;
import com.simas.model.EventTodoBean;
import com.simas.model.EventTodoMainListBean;
import com.simas.model.MainListBean;

public class LeaderTodo extends BaseAct implements OnClickListener {

	private ListView leaderListView;
	private ListView todoListView;
	private ListView isEndListView;
	private TextView tvLeft, tvMid, tvRight;

	public ArrayList<EventListBean> data = new ArrayList<EventListBean>();// 区领导
	public ArrayList<EventTodoBean> todoData = new ArrayList<EventTodoBean>();// 待办数据
	public ArrayList<EventIsEndBean> isEndData = new ArrayList<EventIsEndBean>();// 已办数据

	private ListAdapter<EventListBean> mListAdapter;
	private TodoListAdapter<EventTodoBean> todoListAdapter;
	private IsEndListAdapter<EventIsEndBean> isEndListAdapter;

	String leaderUrl = "http://" + PathConfig.IP
			+ "/EEventApp.app?Method=ExecuteQueryEvents&grid_code=01&sortname=code&rp=1000&page=1&sortorder=desc";

	String todoUrl = "http://" + PathConfig.IP
			+ "/EEventApp.app?Method=ExecuteQueryCurrentstep&sortname=code&rp=1000&page=1&sortorder=desc&rolecode="
			+ LoginActivity.list.get(0).getRole_code() + "&gridcode=" + LoginActivity.list.get(0).getGrid_code()
			+ "&code=" + LoginActivity.list.get(0).getCode();

	String isEndUrl = "http://" + PathConfig.IP
			+ "/EEventApp.app?Method=ExecuteQueryHistorystep&sortname=code&rp=1000&page=1&sortorder=desc&gridcode="
			+ LoginActivity.list.get(0).getGrid_code() + "&code=" + LoginActivity.list.get(0).getCode();

	@Override
	protected void onResume() {
		super.onResume();
//		initTab();
		initData(2);
		// tvRight.setTextColor(this.getResources().getColor(R.color.tab_white));
		// tvRight.setBackgroundResource(R.drawable.btn_toggle_filter_right_s);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		tvLeft = (TextView) this.findViewById(R.id.tvLeft);
		tvMid = (TextView) this.findViewById(R.id.tvMid);
		tvRight = (TextView) this.findViewById(R.id.tvRight);
		tvLeft.setOnClickListener(this);
		tvMid.setOnClickListener(this);
		tvRight.setOnClickListener(this);

		leaderListView = (ListView) this.findViewById(R.id.leaderListView);
		leaderListView.setOnItemClickListener(mOnItemClickListenerLeader);

		todoListView = (ListView) this.findViewById(R.id.todoListView);
		todoListView.setOnItemClickListener(mOnItemClickListenerEventTodo);

		isEndListView = (ListView) this.findViewById(R.id.isEndListView);
		isEndListView.setOnItemClickListener(mOnItemClickListenerEventIsEnd);
		initData(1);

	}

	private void initTab() {
		tvLeft.setTextColor(this.getResources().getColor(R.color.tab_gary));
		tvLeft.setBackgroundResource(R.drawable.btn_toggle_filter_left_n);
		isEndListView.setVisibility(View.GONE);

		tvMid.setTextColor(this.getResources().getColor(R.color.tab_gary));
		tvMid.setBackgroundResource(R.drawable.btn_toggle_filter_middle_n);
		leaderListView.setVisibility(View.GONE);

		tvRight.setTextColor(this.getResources().getColor(R.color.tab_gary));
		tvRight.setBackgroundResource(R.drawable.btn_toggle_filter_right_n);
		todoListView.setVisibility(View.GONE);
	}

	public void Getview() {

	};

	OnItemClickListener mOnItemClickListenerEventIsEnd = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Intent intent = new Intent(LeaderTodo.this, EventDetailAct.class);
			intent.putExtra("flag", "0");
			intent.putExtra("code", isEndData.get(arg2).getCode());
			startActivity(intent);
		}
	};
	OnItemClickListener mOnItemClickListenerLeader = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			Intent intent1 = new Intent(LeaderTodo.this, EventDetailAct.class);
			intent1.putExtra("flag", "1");
			intent1.putExtra("code", data.get(arg2).getCode());
			intent1.putExtra("wfID", data.get(arg2).getFlag_wfid());
			intent1.putExtra("stepID", data.get(arg2).getStep_id());
			startActivity(intent1);
		}
	};
	OnItemClickListener mOnItemClickListenerEventTodo = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			Intent intent2 = new Intent(LeaderTodo.this, EventDetailAct.class);
			intent2.putExtra("flag", "2");
			intent2.putExtra("code", todoData.get(arg2).getCode());
			intent2.putExtra("wfID", todoData.get(arg2).getFlag_wfid());
			intent2.putExtra("stepID", todoData.get(arg2).getStep_id());
			startActivity(intent2);
		}
	};

	private void initData(final int arg0) {
		String url = "";
		switch (arg0) {
		case 0:
			isEndListView.setVisibility(View.VISIBLE);
			url = isEndUrl;
			break;
		case 1:
			leaderListView.setVisibility(View.VISIBLE);
			url = leaderUrl;
			break;
		case 2:
			url = todoUrl;
			break;

		default:
			break;
		}

		StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				String jsondata = response.toString();
				switch (arg0) {
				case 0:

					EventIsEndMainListBean temp0 = (EventIsEndMainListBean) JSON.parseObject(jsondata,
							EventIsEndMainListBean.class);
					isEndData = (ArrayList<EventIsEndBean>) temp0.getRows();
					isEndListAdapter = new IsEndListAdapter<EventIsEndBean>(isEndData, isEndListView, LeaderTodo.this);
					isEndListView.setAdapter(isEndListAdapter);
					break;
				case 1:
					MainListBean temp = (MainListBean) JSON.parseObject(jsondata, MainListBean.class);
					data = (ArrayList<EventListBean>) temp.getRows();
					mListAdapter = new ListAdapter<EventListBean>(data, leaderListView, LeaderTodo.this);
					leaderListView.setAdapter(mListAdapter);
					break;
				case 2:
					EventTodoMainListBean temp1 = (EventTodoMainListBean) JSON.parseObject(jsondata,
							EventTodoMainListBean.class);
					todoData = (ArrayList<EventTodoBean>) temp1.getRows();
					todoListAdapter = new TodoListAdapter<EventTodoBean>(todoData, todoListView, LeaderTodo.this);
					todoListView.setAdapter(todoListAdapter);
					break;
				default:
					break;
				}

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);
			}
		});
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.tvLeft:
			initTab();
			tvLeft.setTextColor(this.getResources().getColor(R.color.tab_white));
			tvLeft.setBackgroundResource(R.drawable.btn_toggle_filter_left_s);
			initData(0);

			break;
		case R.id.tvMid:
			initTab();
			tvMid.setTextColor(this.getResources().getColor(R.color.tab_white));
			tvMid.setBackgroundResource(R.drawable.btn_toggle_filter_middle_s);
			initData(1);

			break;
		case R.id.tvRight:
			initTab();
			tvRight.setTextColor(this.getResources().getColor(R.color.tab_white));
			tvRight.setBackgroundResource(R.drawable.btn_toggle_filter_right_s);
			todoListView.setVisibility(View.VISIBLE);
			initData(2);

			break;

		default:
			break;
		}

	}

}
