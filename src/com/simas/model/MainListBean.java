package com.simas.model;

import java.util.List;

public class MainListBean {

	private String page;
	private String total;
	private List<EventListBean> rows;

	public MainListBean() {
		super();
	}

	public MainListBean(String page, String total, List<EventListBean> rows) {
		super();
		this.page = page;
		this.total = total;
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<EventListBean> getRows() {
		return rows;
	}

	public void setRows(List<EventListBean> rows) {
		this.rows = rows;
	}

}
