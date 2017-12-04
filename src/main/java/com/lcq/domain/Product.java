package com.lcq.domain;

import java.util.Date;

public class Product {
	private int id;  // 商品编号，自动增长
	private String name;  // 商品名称
	private double price;  // 商品价格
	private String pic;  // 商品图片
	private String remark;  // 商品简单介绍
	private String xremark;  // 商品详细介绍
	private Date date;  // 商品生产日期
	private boolean commend;  // 是否为推荐商品，推荐商品才有可能显示在商城首页
	private boolean open;  // 是否为有效商品，有小商品才有可能显示在商城首页

	private Category category;          // 商品所在的类别  对应 cid

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", pic='" + pic + '\'' +
				", remark='" + remark + '\'' +
				", xremark='" + xremark + '\'' +
				", date=" + date +
				", commend=" + commend +
				", open=" + open +
				", category=" + category +
				'}';
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getXremark() {
		return xremark;
	}

	public void setXremark(String xremark) {
		this.xremark = xremark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isCommend() {
		return commend;
	}

	public void setCommend(boolean commend) {
		this.commend = commend;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
