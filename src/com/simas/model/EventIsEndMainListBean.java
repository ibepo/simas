package com.simas.model;

import java.util.List;

public class EventIsEndMainListBean {
	private String page;
	private String total;
	private List<EventIsEndBean> rows;

	public EventIsEndMainListBean() {
		super();
	}

	public EventIsEndMainListBean(String page, String total, List<EventIsEndBean> rows) {
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

	public List<EventIsEndBean> getRows() {
		return rows;
	}

	public void setRows(List<EventIsEndBean> rows) {
		this.rows = rows;
	}

}
