package com.lcq.test;

import org.junit.Test;

import java.util.Scanner;

public class PracticeTest {

	/*
	*类的成员变量
	* 猜数字
	* */
	@Test
	public void testGuessNumber () {}
	@Test
	public void testVehicle (){
		Vehicle v = new Vehicle();
		v.setSpeed(100);
		v.setSize("50/m^3");
		System.out.println("the initial speed is " + v.getSpeed() + " and my size is " + v.getSize());
		v.speedUp();
		v.speedDown();
	}

	/**
	 * 这是测试一个自定义计算时间的类
	 */
	@Test
	public void testMyDate() {
		MyTime time = new MyTime(6, 4,3);
		time.display();
		System.out.println("add hour");
		time.addHour(6);
		time.display();
	}

	/**
	 * 测试计算器
	 */
	@Test
	public void testNumber () {
		Number computer = new Number(6, 66);
		computer.division();
		computer.except();
		computer.plus();
		computer.multiplication();
		System.out.println( computer );
	}

	/*
	* 测试Person
	* */
	@Test
	public void testPerson () {
		Person1 p = new Person1("tomcat", 4);
		p.display();
	}

	/**
	 * 测试自定义异常方法
	 * @throws MyException
	 */
	@Test
	public void testMyException () throws MyException {
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("输入一个数字，但不要踏入雷区哦");
			int luckyNumber = 16;
			if (luckyNumber >10 && luckyNumber <20) {
				System.out.println("正中雷区");
				throw new MyException("很不幸，猜到地雷了", 1);
			} else {
				continue;
			}
		}
	}

}

/**
 * 自定义异常
 */
class MyException extends Exception {  // 1/ 继承java.lang.Exception
	private int id;

	/**
	 * 自定义异常类的构造方法
	 */
	public MyException(String message, int id) {
		super  (message);
		this.id = id;
	}

	/**
	 * 获取异常的代码
	 */
	public int getId() {
		return id;
	}
}

/*
* question 7
* */
class WuMingFen {
	private String theMa;
	private int quantity;
	private boolean likeSoup;



	public WuMingFen(String theMa, int quantity, boolean likeSoup) {
		this.theMa = theMa;
		this.quantity = quantity;
		this.likeSoup = likeSoup;

		if (likeSoup) {
			String b1 = "带汤的";
			System.out.println( theMa + quantity  + "两" + likeSoup);
		}
	}

	public void check() {
		if ( likeSoup == true ) {
			String token = "带汤的";
			System.out.println("面码：" + theMa + "\n分量" + quantity + "\n" + token) ;
		}
	}

	public WuMingFen() {

	}

	public String getTheMa() {

		return theMa;
	}

	public void setTheMa(String theMa) {
		this.theMa = theMa;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isLikeSoup() {
		return likeSoup;
	}

	public void setLikeSoup(boolean likeSoup) {
		this.likeSoup = likeSoup;
	}
}
/*
* question 6 get and set
* */

/*
* question 5
* */
class Person1 {
	private String name;
	private int age;

	public Person1 (String name, int age) {
		this.name = name;
		this.age = age;
	}
	public void display () {
		System.out.println("name: " + name + "\tage: " + age);
	}
}

/**
 * question 4
 */
class Number {
	private int x;
	private int y;

	public Number(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// plus except multiplication division
	public void plus () {
		System.out.println("the result is:" + (this.x + this.y));
	}
	public void except () {
		System.out.println("the result is:" + (this.x - this.y));
	}
	public void multiplication () {
		System.out.println("the result is:" + (this.x * this.y));
	}public void division () {
		System.out.println("the result is:" + (this.x / this.y));
	}

	@Override
	public String toString() {
		return "Number{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}

/**
 * question 3
 */
class MyTime {
	private int hour;
	private int minute;
	private int second;

	public MyTime( int h, int m, int s) {
		this.hour = h;
		this.minute = m;
		this.second = s;
	}
	// 输出时间信息
	public void display() {
		System.out.println("The tme is " + this.hour + ":" + this.minute + ":" + this.second);
	}

	// 添加时、分、秒
	public void addHour(int hour) {
		this.hour += hour;
	}
	public void addMinute(int minute) {
		this.second += minute;
	}
	public void addSecond(int second) {
		this.second += second;
	}

	// 减少 时、分、秒
	public void subHour(int hour) {
		this.hour -= hour;
	}
	public void subMinute(int minute) {
		this.minute -= minute;
	}
	public void subSecond(int second) {
		this.second -= second;
	}

}

/**
 * question 2
 */
class Vehicle {
	private int speed;
	private String size;

	public void move() {
		System.out.println("I'm moving.");
	}
	public void setSpeed(int speed) {
		System.out.println("now I'm running with " + speed + "per hour");
	}

	public void speedUp() {
		Vehicle v = new Vehicle();
		v.setSpeed(1000);
	}

	public void speedDown () {
		Vehicle v = new Vehicle();
		v.setSpeed(20);
	}

	public int getSpeed() {
		return speed;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}


