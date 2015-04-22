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
import com.simas.model.EventTodoBean;

@SuppressWarnings("rawtypes")
public class TodoListAdapter<T> extends CustomAdapter {
	private ListView listView;
	private Context context;
	private List<EventTodoBean> data; // Target Data
	private LayoutInflater inflater;

	@SuppressWarnings("unchecked")
	public TodoListAdapter(ArrayList<T> data, Context context) {
		super(data, context);
	}

	@SuppressWarnings("unchecked")
	public TodoListAdapter(ArrayList<T> data, ListView listView, Context context) {
		super(data, context);

		this.listView = listView;
		// asyncImageLoader = new AsyncImageLoader();
		this.context = context;
		this.data = (List<EventTodoBean>) data;
		inflater = ((Activity) context).getLayoutInflater();
	}

	@SuppressWarnings("unchecked")
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) context;

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
		EventTodoBean megInfo = (EventTodoBean) getItem(position);
		viewCache.tvTitle.setText(megInfo.getName_event_type());
		viewCache.tvContent.setText(megInfo.getName_appeal());
		viewCache.tvTime.setText(megInfo.getStart_date());
		return rowView;
	}

	private class ViewHolder {

		public TextView tvTitle;
		public TextView tvContent;
		public TextView tvTime;

		public TextView tvacceptCode;
		public TextView tvacceptState;
		public TextView tveventName;
		public TextView tveventType;
		public TextView tvtransferUnit;
		public TextView tvreportTime;
		public TextView tvreportPersion;
		public TextView tvappealPersion;
	}

	public void setData(List<EventTodoBean> resultList) {
		this.data = resultList;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}