package com.simas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.bepo.ui.LoginActivity;
import com.simas.R;
import com.simas.base.BaseAct;

/**
 * 
 * @author simas
 * @描述 启动欢迎页 用来呈现启动页面,预加载相关缓存数据,判断版本信息等
 * @tip handler的延时启动
 * 
 */
public class WelcomeAct extends BaseAct {

	private static final int delay_length = 2000;// 延时启动下一个activity的时间长短

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);

		new Handler().postDelayed(new Runnable() {
			public void run() {
//				 Intent mainIntent = new Intent(WelcomeAct.this, Main.class);
//				 WelcomeAct.this.startActivity(mainIntent);
//				 WelcomeAct.this.finish();

				Intent mainIntent = new Intent(WelcomeAct.this,
						LoginActivity.class);
				WelcomeAct.this.startActivity(mainIntent);
				WelcomeAct.this.finish();

			}

		}, delay_length);

	}

	@Override
	public void finish() {
		super.finish();
	}
}
