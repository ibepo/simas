 package com.simas.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.simas.R;
import com.simas.model.MainListBean;

@SuppressWarnings("rawtypes")
public class CommentAdapter<T> extends CustomAdapter implements Filterable {
	private ListView listView;
	// private AsyncImageLoader asyncImageLoader;
	private Context context;
	private List<MainListBean> data; // Target Data
	private String flag;// flag4constraint
	private List<MainListBean> mOriginalValues; // Original Values
	private LayoutInflater inflater;

	@SuppressWarnings("unchecked")
	public CommentAdapter(ArrayList<T> data, Context context) {
		super(data, context);
	}

	@SuppressWarnings("unchecked")
	public CommentAdapter(ArrayList<T> data, ListView listView, Context context) {
		super(data, context);

		this.listView = listView;
		// asyncImageLoader = new AsyncImageLoader();
		this.context = context;
		this.data = (List<MainListBean>) data;
		inflater = ((Activity) context).getLayoutInflater();
	}

	@SuppressWarnings("unchecked")
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) context;

		View rowView = convertView;
		ViewHolder viewCache;
		if (rowView == null) {
			rowView = inflater.inflate(R.layout.list_question, null);
			viewCache = new ViewHolder();
			viewCache.tvBuzhou = (TextView) rowView.findViewById(R.id.tvBuzhou);
			viewCache.tvContent = (TextView) rowView
					.findViewById(R.id.tvContent);

			rowView.setTag(viewCache);
		} else {
			viewCache = (ViewHolder) rowView.getTag();
		}
		MainListBean megInfo = (MainListBean) getItem(position);

//		viewCache.tvBuzhou.setText("步骤： "+megInfo.getTitle());
//		viewCache.tvContent.setText("办理情况及结果："+megInfo.getContent());
		return rowView;
	}

	private class ViewHolder {

		public TextView tvBuzhou;
		public TextView tvContent;
	}

	public void setData(List<MainListBean> resultList) {
		this.data = resultList;
	}

	public void injectFlag(String flag) {
		this.flag = flag;
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

	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				data = (List<MainListBean>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				List<MainListBean> FilteredArrList = new ArrayList<MainListBean>();
				if (mOriginalValues == null) {
					mOriginalValues = new ArrayList<MainListBean>(data);
				}

				/********
				 * 
				 * If constraint(CharSequence that is received) is null returns
				 * the mOriginalValues(Original) values else does the Filtering
				 * and returns FilteredArrList(Filtered)
				 * 
				 * 
				 * StringBuilder sb = new StringBuilder();
				 * sb.append("a").append("b").append("c"); sb.toString();
				 * 
				 * String str = "a"+"b"+"c"+1+false;
				 * 
				 * StringBuffer sbbuffer = new StringBuffer(); for(int i =
				 * 0;i<1000;i++){ str = str+"1"; }
				 * 
				 * String a = "a"; String b = "b"; String a1 = "1"; String
				 * afalse = "false"; String strResult = a+b+a1+afalse;
				 ********/
				if (constraint == null || constraint.length() == 0) {

					// set the Original result to return
					results.count = mOriginalValues.size();
					results.values = mOriginalValues;
				} else {
					constraint = constraint.toString();

					// for (int i = 0; i < mOriginalValues.size(); i++) {
					// listBean data = mOriginalValues.get(i);
					// // String name = data.getName();
					// // String number = data.getSapid();
					//
					// if (flag.equals("name")) {
					//
					// if (name.indexOf(constraint.toString()) != -1) {
					// FilteredArrList.add(data);
					// }
					// } else if (flag.equals("number")) {
					// // if (number.indexOf(constraint.toString()) != -1)
					// // {
					// if (number.startsWith(constraint.toString())) {
					// FilteredArrList.add(data);
					// }
					// }
					//
					// }
					// set the Filtered result to return
					results.count = FilteredArrList.size();
					results.values = FilteredArrList;
				}
				return results;
			}
		};
		return filter;
	}
}