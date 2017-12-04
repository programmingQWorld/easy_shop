package com.lcq.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Forder {
	private int id;   // 购物车（订单表）订单编号，自动增长
	private String name;  // 收件人名字
	private String phone;  // 收件人电话
	private String remark;  // 配送信息 [买家留言]
	private Date date; // 下单日期
	private double total;  // 订单总金额
	private String post;  // 邮政编码
	private String address;  // 收件人的地址信息
	private Set<Sorder> sorders;  // 一个购物车中由多个商品信息
	private int sid;
	private int uid;

	public Forder(Set<Sorder> sorders) {
		this.sorders = sorders;
	}

	public Forder() {
	}

	@Override
	public String toString() {
		return "Forder{" +
				"id=" + id +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", remark='" + remark + '\'' +
				", date=" + date +
				", total=" + total +
				", post='" + post + '\'' +
				", address='" + address + '\'' +
				", sorders=" + sorders +
				", sid=" + sid +
				", uid=" + uid +
				", status=" + status +
				", user=" + user +
				'}';
	}

	public void setSorders(HashSet<Sorder> sorders) {
		this.sorders = sorders;
	}

	private Status status;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Sorder> getSorders() {
		return sorders;
	}
}
