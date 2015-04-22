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
 * @���� ������ӭҳ ������������ҳ��,Ԥ������ػ�������,�жϰ汾��Ϣ��
 * @tip handler����ʱ����
 * 
 */
public class WelcomeAct extends BaseAct {

	private static final int delay_length = 2000;// ��ʱ������һ��activity��ʱ�䳤��

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
