package com.simas.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.simas.R;
import com.simas.base.BaseAct;

public class Main extends BaseAct implements OnClickListener {

	private Button btnWeather;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {

		btnWeather = (Button) this.findViewById(R.id.weather);
		btnWeather.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.weather:

			// Intent mainIntent = new Intent(Main.this, WeatherAct.class);
			// this.startActivity(mainIntent);
			// this.finish();

			break;

		default:
			break;
		}

	}
}
