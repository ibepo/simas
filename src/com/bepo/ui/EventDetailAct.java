package com.bepo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.simas.R;
import com.simas.base.ApplicationController;
import com.simas.base.BaseAct;
import com.simas.base.PathConfig;
import com.simas.model.EventDetailBean;
import com.simas.utils.ToastUtils;

public class EventDetailAct extends BaseAct implements OnClickListener {

	private TextView tvBack, tvShengpi, tvEventType, tvEventName, tvDengjiren, tvEventAddress, tvEventTime, tvWangge,
			tvDetail, loading_txt;

	private ImageView imageview;
	private View mLoading;
	PopupWindow popupWindow;
	Button bt01, bt02, bt03;
	EditText etYijian;
	private RelativeLayout rlMain;
	private EventDetailBean data = new EventDetailBean();
	private String flag = "";
	private String code = "";
	private String yiJian = "";
	private String wfID = "";
	private String stepID = "";
	// 待办所用字段
	private String actionID = "";
	private String rolecode = LoginActivity.list.get(0).getRole_code();
	private String gridcode = LoginActivity.list.get(0).getGrid_code();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);
		initView();
		initData();
	}

	private void initData() {

		Intent intent = this.getIntent();
		if (intent != null && intent.getStringExtra("flag") != null) {
			flag = intent.getStringExtra("flag");

			if (flag.equals("0")) {
				tvShengpi.setVisibility(View.GONE);
			} else if (flag.equals("1")) {
				tvShengpi.setText(" 批示  ");
			} else if (flag.equals("2")) {
				tvShengpi.setText(" 审批  ");
			}
		}

		if (intent != null && intent.getStringExtra("code") != null) {
			code = intent.getStringExtra("code");

		}
		if (intent != null && intent.getStringExtra("wfID") != null) {
			wfID = intent.getStringExtra("wfID");

		}
		if (intent != null && intent.getStringExtra("stepID") != null) {
			stepID = intent.getStringExtra("stepID");

		}

		String url = "http://" + PathConfig.IP + "/EEventApp.app?Method=ExecuteQuerySeeApp&code=" + code;

		// 1String url = "http://" + PathConfig.IP +
		// "/EEventApp.app?Method=ExecuteQuerySeeApp&code=" + "00008801";

		StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				String jsondata = response.toString();

				if (jsondata.equals("")) {
					ToastUtils.toast(EventDetailAct.this, "没有具体数据");
					finish();
				} else {
					data = JSON.parseArray(jsondata, EventDetailBean.class).get(0);
					setView();
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}

		});

		ApplicationController.getInstance().addToRequestQueue(stringRequest);
	}

	private void setView() {

		if (!data.getAppeal_img().equals("")) {
			String imageUrl = "http://" + PathConfig.IP + "/uploads/picture/" + data.getAppeal_img();
			// String imageUrl =
			// "http://a.hiphotos.baidu.com/image/pic/item/8cb1cb13495409236d9ba7359258d109b2de498f.jpg";
			// String imageUrl =
			// "http://t12.baidu.com/it/u=4095575894,102452705&fm=32&s=A98AA55F526172A6F6A058E50300A060&w=623&h=799&img.JPEG";
			// String imageUrl =
			// "http://c.hiphotos.baidu.com/image/pic/item/0823dd54564e9258e306c3e09e82d158ccbf4e89.jpg";

			ImageLoader.getInstance().displayImage(imageUrl, imageview);
		}

		tvEventType.setText(data.getName_event_type());
		tvEventName.setText(data.getName_appeal());
		tvWangge.setText(data.getThird_name() + " " + data.getFour_name() + " " + data.getGrid_name());
		tvEventName.setText(data.getEvent_address());
		tvDengjiren.setText(data.getCreated_name());
		tvEventTime.setText(data.getCreated_telephone());
		tvDetail.setText(data.getAppeal_content());
		tvEventAddress.setText(data.getEvent_address());
		mLoading.setVisibility(View.GONE);
	}

	private void initView() {
		rlMain = (RelativeLayout) this.findViewById(R.id.rlMain);
		tvBack = (TextView) this.findViewById(R.id.tvBack);
		tvBack.setOnClickListener(this);

		tvShengpi = (TextView) this.findViewById(R.id.tvShengpi);
		tvShengpi.setOnClickListener(this);

		imageview = (ImageView) this.findViewById(R.id.imagview);

		tvEventType = (TextView) this.findViewById(R.id.tvEventType);
		tvEventName = (TextView) this.findViewById(R.id.tvEventName);
		tvEventAddress = (TextView) this.findViewById(R.id.tvEventAddress);
		tvDengjiren = (TextView) this.findViewById(R.id.tvDengjiren);
		tvEventType = (TextView) this.findViewById(R.id.tvEventType);
		tvWangge = (TextView) this.findViewById(R.id.tvWangge);
		tvEventTime = (TextView) this.findViewById(R.id.tvEventTime);
		tvDetail = (TextView) this.findViewById(R.id.tvDetail);

		mLoading = (View) this.findViewById(R.id.loading);
		loading_txt = (TextView) this.findViewById(R.id.loading_txt);
		loading_txt.setText("正在加载...");

	}

	private void getPopupWindow() {
		if (null != popupWindow) {
			popupWindow.dismiss();
			return;
		} else {
			initPopupWindow();
		}
	}

	protected void initPopupWindow() {

		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = getLayoutInflater().inflate(R.layout.comment_items, null, false);

		// popwindow中的控件

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		// popupWindow = new PopupWindow(popupWindow_view, 650, 715, true);
		popupWindow = new PopupWindow(popupWindow_view, 500, 300, true);
		popupWindow.setWidth(LayoutParams.FILL_PARENT);
		popupWindow.setHeight(LayoutParams.WRAP_CONTENT);

		bt01 = (Button) popupWindow_view.findViewById(R.id.bt01);
		bt02 = (Button) popupWindow_view.findViewById(R.id.bt02);
		bt03 = (Button) popupWindow_view.findViewById(R.id.bt03);
		etYijian = (EditText) popupWindow_view.findViewById(R.id.etYijian);

		bt03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}

			}
		});

		if (flag.equals("1")) {
			bt01.setText("提交");
			bt02.setVisibility(View.GONE);
			bt01.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					yiJian = etYijian.getText().toString().trim();
					String url = "http://" + PathConfig.IP + "/EEventLeaderOpinionApp.app?Method=ExecuteSave&code="
							+ code + "&leader_opinion=" + yiJian + "&wfID=" + wfID + "&stepID=" + stepID;
					submitData(url);

				}

			});

		} else if (flag.equals("2")) {

			bt01.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					yiJian = etYijian.getText().toString().trim();
					String url = "http://" + PathConfig.IP
							+ "/EEventApp.app?Method=ExecuteSaveApproval&workFlowName=EEvent&wfID=" + wfID
							+ "&stepID=15&actionID=710&opinion=" + yiJian + "&rolecode=" + rolecode + "&gridcode="
							+ gridcode + "&code=" + LoginActivity.list.get(0).getCode();
					submitData(url.trim());

				}

			});
			bt02.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					yiJian = etYijian.getText().toString().trim();
					String url = "http://" + PathConfig.IP
							+ "/EEventApp.app?Method=ExecuteSaveApproval&workFlowName=EEvent&wfID=" + wfID
							+ "&stepID=15&actionID=720&opinion=" + yiJian + "&rolecode=" + rolecode + "&gridcode="
							+ gridcode + "&code=" + LoginActivity.list.get(0).getCode();
					submitData(url.trim());
				}
			});

		}

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

	private void submitData(String url) {

		String submitUrl = url;

		StringRequest stringRequest = new StringRequest(submitUrl, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				String jsondata = response.toString();
				ToastUtils.toast(EventDetailAct.this, jsondata);
				finish();
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				ToastUtils.toast(EventDetailAct.this, "网络错误，请稍后重试！！ ");
			}

		});

		ApplicationController.getInstance().addToRequestQueue(stringRequest);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvBack:
			this.finish();
			break;
		case R.id.tvShengpi:
			getPopupWindow();
			popupWindow.showAtLocation(rlMain, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
			break;

		default:
			break;
		}

	}

}
