package com.lcq.domain;

public class Sorder {
	private int id;  // 购物项编号，自动增长
	private String name;  // 被购买商品的名称
	private double price;  // 购买时商品的价格
	private int number;  // 购买的数量

	private int pid;
	private int fid;

	private Product product;
	private Forder forder;


	@Override
	public String toString() {
		return "sorder{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", number=" + number +
				", pid=" + pid +
				", fid=" + fid +
				", product=" + product +
				", forder=" + forder +
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Forder getForder() {
		return forder;
	}

	public void setForder(Forder forder) {
		this.forder = forder;
	}
}
