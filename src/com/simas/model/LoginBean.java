package com.simas.model;

public class LoginBean {

	public String code;// ����
	public String role_code;// ��ɫ����
	public String grid_code;// �������
	public String name_user;// �˺�����
	public String grid_name;// ��������
	public String second_name;// ��������
	public String third_name;// �ֵ�����
	public String four_name;

	public LoginBean() {
		super();
	}

	public LoginBean(String code, String role_code, String grid_code, String name_user, String grid_name,
			String second_name, String third_name, String four_name) {
		super();
		this.code = code;
		this.role_code = role_code;
		this.grid_code = grid_code;
		this.name_user = name_user;
		this.grid_name = grid_name;
		this.second_name = second_name;
		this.third_name = third_name;
		this.four_name = four_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getGrid_code() {
		return grid_code;
	}

	public void setGrid_code(String grid_code) {
		this.grid_code = grid_code;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getGrid_name() {
		return grid_name;
	}

	public void setGrid_name(String grid_name) {
		this.grid_name = grid_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getThird_name() {
		return third_name;
	}

	public void setThird_name(String third_name) {
		this.third_name = third_name;
	}

	public String getFour_name() {
		return four_name;
	}

	public void setFour_name(String four_name) {
		this.four_name = four_name;
	}

	@Override
	public String toString() {
		return "LoginBean [code=" + code + ", role_code=" + role_code + ", grid_code=" + grid_code + ", name_user="
				+ name_user + ", grid_name=" + grid_name + ", second_name=" + second_name + ", third_name="
				+ third_name + ", four_name=" + four_name + "]";
	}
}
