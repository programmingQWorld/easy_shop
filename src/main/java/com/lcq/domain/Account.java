package com.lcq.domain;

public class Account {
	private int id;
	private String login;
	private String name;
	private String pass;

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", login='" + login + '\'' +
				", name='" + name + '\'' +
				", pass='" + pass + '\'' +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Account() {

	}

}
