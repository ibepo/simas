package com.bepo.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pullrefresh.PullToRefreshListView;
import com.simas.R;
import com.simas.adapter.ListAdapter;
import com.simas.model.MainListBean;

public class EventIsEnd extends Fragment {

	private ListView mListView;
	private PullToRefreshListView mPullListView;

	public ArrayList<MainListBean> data = new ArrayList<MainListBean>();
	private ListAdapter<MainListBean> mListAdapter;

	public EventIsEnd() {
		super();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		System.out.println("onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.event_is_end, container, true);
		
		return view;
	}

}
