package com.simas.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.simas.R;
import com.simas.adapter.CommentAdapter;
import com.simas.base.BaseAct;
import com.simas.model.MainListBean;

public class QuestionCommentAct extends BaseAct implements OnClickListener {
	private int resultCode = 0;
	PopupWindow popupWindow;
	TextView tvCancel;
	Button bt01, bt02, bt03;

	ImageView iv;
	ListView listView;
	private CommentAdapter<MainListBean> adapter;
	public ArrayList<MainListBean> list;
	public String index;

	TextView tvShengpi, tvBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_comment);
		initData();
		initView();

	}

	private void initView() {
		iv = (ImageView) this.findViewById(R.id.iv);
		tvShengpi = (TextView) this.findViewById(R.id.tvShengpi);
		tvBack = (TextView) this.findViewById(R.id.tvBack);
		tvShengpi.setOnClickListener(this);
		tvBack.setOnClickListener(this);

		listView = (ListView) this.findViewById(R.id.listView);
		listView.addHeaderView(LayoutInflater.from(this).inflate(
				R.layout.listview_head, null));
		adapter = new CommentAdapter<MainListBean>(list, listView, this);
		listView.setAdapter(adapter);

	}

	private void initData() {

		Intent intent = this.getIntent();
		if (intent != null && intent.getStringExtra("index") != null) {
			index = intent.getStringExtra("index");
		}

		list = new ArrayList<MainListBean>();
		MainListBean tempBean = new MainListBean();
//		tempBean.setTitle("��ʼ");
//		tempBean.setContent("����");
//		tempBean.setCoordinate("118.2057681458,39.6311881908");
//		tempBean.setPersion("22");
//		tempBean.setHouse("56");
//		tempBean.setProject("49");
//		tempBean.setUnit("43");
//		tempBean.setQuestion("90");
//		tempBean.setEvent("45");
//		list.add(tempBean);
//
//		tempBean = new MainListBean();
//		tempBean.setTitle("�ϱ��ֵ�");
//		tempBean.setContent("��ֵ��������");
//		tempBean.setCoordinate("118.177934,39.69532");
//		tempBean.setPersion("34");
//		tempBean.setHouse("54");
//		tempBean.setProject("75");
//		tempBean.setUnit("65");
//		tempBean.setQuestion("90");
//		tempBean.setEvent("34");
//		list.add(tempBean);
//
//		tempBean = new MainListBean();
//		tempBean.setTitle("�ϱ�ָ������");
//		tempBean.setContent("��������С���ⶫ�����Ҷ���ɳ�����̻�������Ӱ����л������а�ȫ������");
//		tempBean.setCoordinate("118.2047301458,39.6311151908");
//		tempBean.setPersion("76");
//		tempBean.setHouse("56");
//		tempBean.setProject("49");
//		tempBean.setUnit("43");
//		tempBean.setQuestion("80");
//		tempBean.setEvent("95");
//		list.add(tempBean);
//
//		tempBean = new MainListBean();
//		tempBean.setTitle("������ǲ");
//		tempBean.setContent("����ʱ�ޣ�5�죬�����������軷�����Ž��д���");
//		tempBean.setCoordinate("118.186997,39.624414");
//		tempBean.setPersion("44");
//		tempBean.setHouse("26");
//		tempBean.setProject("79");
//		tempBean.setUnit("93");
//		tempBean.setQuestion("20");
//		tempBean.setEvent("85");
//		list.add(tempBean);

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

		// ��ȡ�Զ��岼���ļ�activity_popupwindow_left.xml����ͼ
		View popupWindow_view = getLayoutInflater().inflate(
				R.layout.comment_items, null, false);

		// popwindow�еĿؼ�

		// ����PopupWindowʵ��,200,LayoutParams.MATCH_PARENT�ֱ��ǿ�Ⱥ͸߶�
		// popupWindow = new PopupWindow(popupWindow_view, 650, 715, true);
		popupWindow = new PopupWindow(popupWindow_view, 500, 300, true);
		popupWindow.setWidth(LayoutParams.FILL_PARENT);
		popupWindow.setHeight(LayoutParams.WRAP_CONTENT);

		bt01 = (Button) popupWindow_view.findViewById(R.id.bt01);
		bt01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}

				Intent mIntent = new Intent();
				mIntent.putExtra("index", index);
				QuestionCommentAct.this.setResult(resultCode, mIntent);
				finish();

			}
		});

		bt02 = (Button) popupWindow_view.findViewById(R.id.bt02);
		bt02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}

				Intent mIntent = new Intent();
				mIntent.putExtra("index", index);
				QuestionCommentAct.this.setResult(100, mIntent);
				finish();

			}
		});
		bt03 = (Button) popupWindow_view.findViewById(R.id.bt03);
		bt03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}

			}
		});

		// ���ö���Ч��
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		// ��������ط���ʧ
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

	@Override
	public void onBackPressed() {
		Intent mIntent = new Intent();
		mIntent.putExtra("index", index);
		QuestionCommentAct.this.setResult(100, mIntent);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvBack:
			finish();
			break;
		case R.id.tvShengpi:
			getPopupWindow();
			popupWindow.showAtLocation(listView, Gravity.BOTTOM
					| Gravity.CENTER, 0, 0);
			break;
		default:
			break;
		}

	}

}
