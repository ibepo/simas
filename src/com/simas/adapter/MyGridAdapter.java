package com.simas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finddreams.graygridView.BaseViewHolder;
import com.simas.R;

public class MyGridAdapter extends BaseAdapter {
	private Context mContext;

	public String[] img_text = { "时间管理", "基础信息", "信息共享", "巡查轨迹", "统计分析", "民情日志", "居家养老" };
	public int[] imgs = { R.drawable.icon_dark_save, R.drawable.icon_dark_save, R.drawable.icon_dark_save,
			R.drawable.icon_dark_save, R.drawable.icon_dark_save, R.drawable.icon_dark_save, R.drawable.icon_dark_save,
			R.drawable.icon_dark_save, R.drawable.icon_dark_save };

	public MyGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return img_text.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
		}
		TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);

		tv.setText(img_text[position]);
		return convertView;
	}

}
