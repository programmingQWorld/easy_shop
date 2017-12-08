package com.lcq.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest {  // 异常的简单测试

	/*
	* 任何方法往外抛能处理的异常的时候都有一种简单的写法： throws exception
	* 因为exception类是所有能处理的异常类的根基类，因此抛出exception类就会抛出所有能够被处理异常类里了
	* 使用 throws exception 抛出所有能被处理的异常之后，这些被跑出来的异常就是交割java运行时系统处理了
	* 而处理的方法是吧这些异常的相关信息错误信息全部打印出来
	* @throw
	* */
	void fn () throws Exception {}

	/**
	 * 再知道异常的类型以后，方法声明时使用throws把异常往外抛
	 * @param i
	 * @throws ArithmeticException
	 */
	void  m1 (int i) throws ArithmeticException {}


	void m2 (int i) {
		if ( i==0 ) {
			// 这种做法就是手动抛出异常，使用throw + new 出来的异常对象 就可以把这个异常对象抛出
			// 这里是new了一个异常对象，再构建这个对象的时候还可以指定他相关的信息，如这里志明了异常信息 i 不能等于 0
			// 这个对象跑出去的时候使用getMessage() 方法拿到的就是 i 不能等于0 这种信息
			throw new ArithmeticException(" i不能等于0" );
		}

	}

	/**
	 * 正常情况下如果这里不写try……catch语句那么程序编译时一定会爆粗哦
	 * 因为这里有可能回产生两个必须要处理的异常：FileNotFuoundException和IOException.
	 * 但由于再生命方法f()时已经使用throws把可能产生的这两个异常抛出了，
	 * 所以这里可以不谢try……catch语句去处理可能回产生的异常。
	 * f()方法把抛出的异常交给下一个要调用它的方法去粗粝
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void f() throws FileNotFoundException, IOException {
		// 这里有可能回产生FileNotFoundException异常
		FileInputStream fis = new FileInputStream("MyFile.txt");
		// 这里有可能回产生IOException异常
		int b = fis.read();
		while ( b!= -1 ) {
			System.out.println( (char)b );
			b = fis.read();
		}
	}
}
