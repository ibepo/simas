package com.simas.model;

import java.util.List;

public class EventTodoMainListBean {

	private String page;
	private String total;
	private List<EventTodoBean> rows;

	public EventTodoMainListBean() {
		super();
	}

	public EventTodoMainListBean(String page, String total, List<EventTodoBean> rows) {
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

	public List<EventTodoBean> getRows() {
		return rows;
	}

	public void setRows(List<EventTodoBean> rows) {
		this.rows = rows;
	}

}
