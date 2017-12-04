package com.lcq.domain;


public class User {
	private int id;  // 用户编号，自动增长
	private String login;  // 用户登录名
	private String password;  // 用户登录密码
	private String sex;  // 用户性别
	private String phone;  // 用户电话
	private String email;  // 用户邮箱


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", sex='" + sex + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {

	}
}
