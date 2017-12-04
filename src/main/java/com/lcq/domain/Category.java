package com.lcq.domain;

public class Category {
	private long id;
	private String type;
	private int hot;
	private Account account;

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", type='" + type + '\'' +
				", hot='" + hot + '\'' +
				", account=" + account +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}




	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Category() {

	}
}
