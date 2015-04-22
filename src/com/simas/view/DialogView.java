package com.simas.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.simas.R;

public class DialogView extends Dialog implements OnClickListener {
	
	
	

	@Override
	public void addContentView(View view, LayoutParams params) {
		super.addContentView(view, params);
	}

	@Override
	public void cancel() {
		super.cancel();
	}

	@Override
	public View findViewById(int id) {
		return super.findViewById(id);
	}

	protected DialogView(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public TextView tvTitle;
	public TextView tvContent;
	public TextView tvCancle;
	public TextView tvOK;

	private void initView() {
		tvTitle = (TextView) this.findViewById(R.id.tvTitle);
		tvContent = (TextView) this.findViewById(R.id.tvContent);
		tvCancle = (TextView) this.findViewById(R.id.tvCancle);
		tvCancle.setOnClickListener(this);
		tvOK = (TextView) this.findViewById(R.id.tvOk);
		tvOK.setOnClickListener(this);

	}

	public void noTitleDialog(String arg) {
		tvTitle.setVisibility(View.GONE);
		tvContent.setText(arg);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.tvOk:

			break;

		case R.id.tvCancle:
			break;

		default:
			break;
		}

	}

}
