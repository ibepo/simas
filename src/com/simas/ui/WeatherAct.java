package com.simas.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.simas.R;
import com.simas.base.BaseAct;
import com.simas.model.WeatherInfo;

public class WeatherAct extends BaseAct implements OnClickListener {

	private WeatherInfo weatherInfo;
	private TextView description;
	private TextView temperature;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);
		initView();
	}

	private void initView() {
		description = (TextView) this.findViewById(R.id.description);
		temperature = (TextView) this.findViewById(R.id.temperature);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.description:

			break;

		default:
			break;
		}

	}
}
