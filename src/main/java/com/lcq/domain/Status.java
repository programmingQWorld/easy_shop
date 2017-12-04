package com.lcq.domain;

public class Status {
	private int id;                 // 状态编号，自动增长
	private String status;    // 订单状态

	public Status() {
	}

	@Override
	public String toString() {
		return "Status{" +
				"id=" + id +
				", status='" + status + '\'' +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}