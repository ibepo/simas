package com.bepo.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.simas.R;
import com.simas.base.ApplicationController;
import com.simas.base.PathConfig;
import com.simas.model.LoginBean;
import com.simas.ui.ListActivity;
import com.simas.utils.MyTextUtils;
import com.simas.utils.ToastUtils;

public class LoginActivity extends Activity implements OnClickListener {

	// save pwd
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	private EditText edUser, etPsw;
	private TextView tvlogin;
	private TextView tvsignup;
	private TextView tvsearch;
	private CheckBox checkbox;
	private View mLoading;
	private TextView loading_txt;

	private String name;
	private String psw;
	public LoginBean mLoginBean;
	public List<LoginBean> students = new ArrayList<LoginBean>();

	private Boolean isrember = true;

	public static List<LoginBean> list = new ArrayList<LoginBean>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginmain);

		sp = getSharedPreferences("USER_INFO", MODE_PRIVATE);

		tvlogin = (TextView) findViewById(R.id.tvlogin);
		tvlogin.setOnClickListener(this);
		tvsignup = (TextView) findViewById(R.id.tvsignup);
		tvsignup.setOnClickListener(this);
		tvsearch = (TextView) findViewById(R.id.tvsearch);
		tvsearch.setOnClickListener(this);
		checkbox = (CheckBox) findViewById(R.id.checkbox);
		mLoading = findViewById(R.id.loading);

		loading_txt = (TextView) findViewById(R.id.loading_txt);
		loading_txt.setText("登录中..");

		etPsw = (EditText) this.findViewById(R.id.etPsw);
		edUser = (EditText) this.findViewById(R.id.edUser);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvlogin:

			name = edUser.getText().toString();
			psw = etPsw.getText().toString();

			name = "lingdao1";
			psw = "123456";

			if (MyTextUtils.isEmpty(name) || MyTextUtils.isEmpty(psw)) {
				ToastUtils.toast(this, "密码或账号不能为空");
				// getData();
			} else {
				mLoading.setVisibility(View.VISIBLE);
				tvlogin.setClickable(false);
				getData();
			}

			break;

		}

	}

	private void getData() {
		String url = "http://" + PathConfig.IP + "/RUsersApp.app?Method=ExcuteAppLogin&login_name=" + name
				+ "&login_pwd=" + psw;

		// String url = "http://" + PathConfig.IP +
		// "/RUsersApp.app?Method=ExcuteAppLogin&login_name=" + "lingdao1"
		// + "&login_pwd=" + "123456";

		StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {

				String jsondata = response.toString();
				if (MyTextUtils.isEmpty(jsondata)) {
					ToastUtils.toast(LoginActivity.this, "账号或密码错误!!");
					mLoading.setVisibility(View.GONE);
					tvlogin.setClickable(true);

				} else {
					list = JSON.parseArray(jsondata, LoginBean.class);
					Intent intent = new Intent(LoginActivity.this, LeaderTodo.class);
					startActivity(intent);
					LoginActivity.this.finish();
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
}
