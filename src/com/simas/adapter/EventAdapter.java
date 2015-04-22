package com.simas.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.simas.R;
import com.simas.model.EventListBean;

public class EventAdapter<T> extends CustomAdapter<T> {
	private List<EventListBean> data;
	public Context context;
	public LayoutInflater inflater;
	private ListView listView;

	public EventAdapter(ArrayList<T> data, Context context) {
		super(data, context);
	}

	@SuppressWarnings("unchecked")
	public EventAdapter(ArrayList<T> data, ListView listView, Context context) {
		super(data, context);
		this.listView = listView;
		this.context = context;
		this.data = (List<EventListBean>) data;
		inflater = ((Activity) context).getLayoutInflater();
	}

	@SuppressWarnings("unchecked")
	public View getView(int position, View convertView, ViewGroup parent) {
		// Activity activity = (Activity) context;

		View rowView = convertView;
		ViewHolder viewCache;
		if (rowView == null) {
			rowView = inflater.inflate(R.layout.list_items, null);
			viewCache = new ViewHolder();
			viewCache.tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
			viewCache.tvContent = (TextView) rowView.findViewById(R.id.tvContent);
			viewCache.tvTime = (TextView) rowView.findViewById(R.id.tvTime);
			rowView.setTag(viewCache);

		} else {
			viewCache = (ViewHolder) rowView.getTag();
		}

		EventListBean megInfo = (EventListBean) getItem(position);
		viewCache.tvTitle.setText(megInfo.getName_event_type());
		viewCache.tvContent.setText(megInfo.getName_appeal());
		viewCache.tvTime.setText(megInfo.getStart_date());
		return rowView;
	}

	private class ViewHolder {

		public TextView tvTitle;
		public TextView tvContent;
		public TextView tvTime;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@SuppressWarnings("unchecked")
	public void setData(ArrayList<T> data) {
		this.data = (List<EventListBean>) data;

	}
}
