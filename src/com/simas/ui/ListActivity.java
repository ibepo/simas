package com.simas.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.simas.R;
import com.simas.adapter.ListAdapter;
import com.simas.base.ApplicationController;
import com.simas.base.BaseAct;
import com.simas.model.EventListBean;
import com.simas.model.MainListBean;

public class ListActivity extends BaseAct implements OnClickListener {

	private PopupWindow popupWindow;
	TextView tvCancel, tvShengpi, tvYanshi;
	Button bt01;

	private ListView mListView, lvYanshi;

	public ArrayList<EventListBean> list;
	private ListAdapter<EventListBean> adapter;

	public ArrayList<EventListBean> YanshiList;
	private ListAdapter<EventListBean> YanshiAdapter;

	private int requestCode;
	private int index;

	public ArrayList<EventListBean> data = new ArrayList<EventListBean>();
	private ListAdapter<EventListBean> mListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		initData();
		initView();
		initTab();
	}

	private void initTab() {
		tvShengpi.setTextColor(this.getResources().getColor(R.color.tab_white));
		tvShengpi.setBackgroundResource(R.drawable.icon_disk_titlebar_tab_left_checked);
		tvYanshi.setTextColor(this.getResources().getColor(R.color.tab_gary));
		tvYanshi.setBackgroundResource(R.drawable.icon_disk_titlebar_tab_right);
		mListView.setVisibility(View.VISIBLE);
		lvYanshi.setVisibility(View.GONE);

	}

	private void switchTab() {
		tvShengpi.setTextColor(this.getResources().getColor(R.color.tab_gary));
		tvShengpi.setBackgroundResource(R.drawable.icon_disk_titlebar_tab_left);
		tvYanshi.setTextColor(this.getResources().getColor(R.color.tab_white));
		tvYanshi.setBackgroundResource(R.drawable.icon_disk_titlebar_tab_right_checked);
		mListView.setVisibility(View.GONE);
		lvYanshi.setVisibility(View.VISIBLE);
	}

	private void initView() {
		tvShengpi = (TextView) this.findViewById(R.id.tvShengpi);
		tvShengpi.setOnClickListener(this);
		tvYanshi = (TextView) this.findViewById(R.id.tvLeft);
		tvYanshi.setOnClickListener(this);

		mListView = (ListView) this.findViewById(R.id.listView);
		adapter = new ListAdapter<EventListBean>(list, mListView, this);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(mOnItemClickListener);

		lvYanshi = (ListView) this.findViewById(R.id.leaderListView);
		YanshiAdapter = new ListAdapter<EventListBean>(YanshiList, lvYanshi, this);
		lvYanshi.setAdapter(YanshiAdapter);
		lvYanshi.setOnItemClickListener(mOnItemClickListener);

	}

	OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// getPopupWindow(arg2);
			// popupWindow.showAtLocation(mListView, Gravity.BOTTOM
			// | Gravity.CENTER, 0, 0);
			Intent intent = new Intent(ListActivity.this, QuestionCommentAct.class);
			intent.putExtra("index", arg2 + "");
			requestCode = 0;
			startActivityForResult(intent, requestCode);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (data != null && data.getStringExtra("index") != null) {
			index = Integer.valueOf(data.getStringExtra("index"));
		}

		switch (resultCode) {
		case 0:
			list.remove(index);
			adapter.notifyDataSetChanged();
			// adapter.setData(list);
			break;
		case 100:

			break;
		}
	}

	// ------------------------------------------popupwindow相关------------------------------------------------
	private void getPopupWindow(int i) {
		if (null != popupWindow) {
			popupWindow.dismiss();
			return;
		} else {
			initPopupWindow(i);
		}
	}

	protected void initPopupWindow(final int i) {

		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = getLayoutInflater().inflate(R.layout.comment_items, null, false);

		// popwindow中的控件

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		// popupWindow = new PopupWindow(popupWindow_view, 650, 715, true);
		popupWindow = new PopupWindow(popupWindow_view, 500, 300, true);
		popupWindow.setWidth(LayoutParams.FILL_PARENT);
		popupWindow.setHeight(LayoutParams.WRAP_CONTENT);

		tvCancel = (TextView) popupWindow_view.findViewById(R.id.tvCancel);
		tvCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
			}
		});

		bt01 = (Button) popupWindow_view.findViewById(R.id.bt01);
		bt01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				initData01(i);
				adapter.notifyDataSetChanged();
				// adapter.setData(list);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
			}
		});

		// 设置动画效果
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 点击其他地方消失
		popupWindow_view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
				return false;
			}

		});

	}

	private void initData() {

		String url = "http://192.168.2.77:8601/EEventApp.app?Method=ExecuteQueryEvents&grid_code=01&sortname=code&rp=50&page="
				+ 20 + "&sortorder=desc";

		StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				String jsondata = response.toString();
				MainListBean temp = (MainListBean) JSON.parseObject(jsondata, MainListBean.class);

				data = (ArrayList<EventListBean>) temp.getRows();
				// mListAdapter.notifyDataSetChanged();
				// mPullListView.onPullDownRefreshComplete();

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);
			}
		});
		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}

	private void initData01(int i) {
		list.remove(i);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvShengpi:
			initTab();

			break;
		case R.id.tvLeft:
			switchTab();
			break;
		default:
			break;
		}

	}

}
