package com.simas.model;

public class EventListBean {

	private String income_id;// 受理编号
	private String name_appeal;// 事件名称
	private String name_event_type;// 事件类型
	private String name_department;// 流转单位
	private String start_date;// 上报时间
	private String name_user;// 上报人
	private String step_name;// 工作流状态

	private String code_event_type;
	private String code;
	private String grid_code;
	private String code_event_from;
	private String flag_wfid;
	private String guid;
	private String event_count;
	private String wfname;
	private String step_id;
	private String state;
	private String due_date;
	private String finish_date;
	private String grid_name;
	private String four_name;
	private String third_name;
	private String second_name;
	private String name_event_from;
	private String name_cn;

	public EventListBean() {
		super();
	}

	public EventListBean(String income_id, String name_appeal, String name_event_type, String name_department,
			String start_date, String name_user, String step_name, String code_event_type, String code,
			String grid_code, String code_event_from, String flag_wfid, String guid, String event_count, String wfname,
			String step_id, String state, String due_date, String finish_date, String grid_name, String four_name,
			String third_name, String second_name, String name_event_from, String name_cn) {
		super();
		this.income_id = income_id;
		this.name_appeal = name_appeal;
		this.name_event_type = name_event_type;
		this.name_department = name_department;
		this.start_date = start_date;
		this.name_user = name_user;
		this.step_name = step_name;
		this.code_event_type = code_event_type;
		this.code = code;
		this.grid_code = grid_code;
		this.code_event_from = code_event_from;
		this.flag_wfid = flag_wfid;
		this.guid = guid;
		this.event_count = event_count;
		this.wfname = wfname;
		this.step_id = step_id;
		this.state = state;
		this.due_date = due_date;
		this.finish_date = finish_date;
		this.grid_name = grid_name;
		this.four_name = four_name;
		this.third_name = third_name;
		this.second_name = second_name;
		this.name_event_from = name_event_from;
		this.name_cn = name_cn;
	}

	public String getIncome_id() {
		return income_id;
	}

	public void setIncome_id(String income_id) {
		this.income_id = income_id;
	}

	public String getName_appeal() {
		return name_appeal;
	}

	public void setName_appeal(String name_appeal) {
		this.name_appeal = name_appeal;
	}

	public String getName_event_type() {
		return name_event_type;
	}

	public void setName_event_type(String name_event_type) {
		this.name_event_type = name_event_type;
	}

	public String getName_department() {
		return name_department;
	}

	public void setName_department(String name_department) {
		this.name_department = name_department;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getStep_name() {
		return step_name;
	}

	public void setStep_name(String step_name) {
		this.step_name = step_name;
	}

	public String getCode_event_type() {
		return code_event_type;
	}

	public void setCode_event_type(String code_event_type) {
		this.code_event_type = code_event_type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrid_code() {
		return grid_code;
	}

	public void setGrid_code(String grid_code) {
		this.grid_code = grid_code;
	}

	public String getCode_event_from() {
		return code_event_from;
	}

	public void setCode_event_from(String code_event_from) {
		this.code_event_from = code_event_from;
	}

	public String getFlag_wfid() {
		return flag_wfid;
	}

	public void setFlag_wfid(String flag_wfid) {
		this.flag_wfid = flag_wfid;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getEvent_count() {
		return event_count;
	}

	public void setEvent_count(String event_count) {
		this.event_count = event_count;
	}

	public String getWfname() {
		return wfname;
	}

	public void setWfname(String wfname) {
		this.wfname = wfname;
	}

	public String getStep_id() {
		return step_id;
	}

	public void setStep_id(String step_id) {
		this.step_id = step_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getFinish_date() {
		return finish_date;
	}

	public void setFinish_date(String finish_date) {
		this.finish_date = finish_date;
	}

	public String getGrid_name() {
		return grid_name;
	}

	public void setGrid_name(String grid_name) {
		this.grid_name = grid_name;
	}

	public String getFour_name() {
		return four_name;
	}

	public void setFour_name(String four_name) {
		this.four_name = four_name;
	}

	public String getThird_name() {
		return third_name;
	}

	public void setThird_name(String third_name) {
		this.third_name = third_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getName_event_from() {
		return name_event_from;
	}

	public void setName_event_from(String name_event_from) {
		this.name_event_from = name_event_from;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

}
