package com.lcq.test;

import com.alibaba.fastjson.JSONObject;
import com.lcq.dao.ProductDao;
import com.lcq.domain.Category;
import com.lcq.domain.Product;
import com.lcq.pojo.ProductTimerTask;
import com.lcq.service.CategoryService;
import com.lcq.service.ProductService;
import javafx.application.Application;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ProductServiceTest extends TestCase {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDao productDao;

	@Test
	public void test() {
		List<Product> list = productService.queryJoinCategoryForPage("", "1", "2");
		System.out.println(list);
	}

	/* 删除数据byid */
	@Test
	public void test1() {
		boolean result = productService.deleteProducts("11,12");
		System.out.println("是否删除成功" + result);
	}

	/*
	* 获取所有类别的信息
	* */
	@Autowired
	private CategoryService categoryService;

	@Test
	public void test2() {
		Map<String, Object> map = new HashMap();
		map.put("type", "");
		List<Category> list = categoryService.getCategories();
		System.out.println("result:" + list);
	}

	/*
	* 保存商品的信息
	* */
	@Test
	public void test3() throws Exception {
		Product product = new Product();
		product.setPic("WEB-INF/upload");
		Category c = new Category();
		c.setId(28);
		product.setCategory(c);
		product.setDate(new Date());
		product.setCommend(true);
		product.setOpen(false);
		product.setName("大海报");
		product.setPrice(16.0);
		product.setRemark("简述");
		product.setXremark("详述");
		productService.saveProduct(product);
	}


	@Test
	public void test4() {
		String s = "aaa-bbb-ccc";
		String[] ss = getArray(s, "-");
		for (String ele : ss) {
			System.out.println("-->" + ele);
		}
	}

	public static String[] getArray(String s, String symbol) {
		StringTokenizer st = new StringTokenizer(s, symbol);
		int count = st.countTokens();
		String[] strs = new String[count];

		int i = 0;
		while (st.hasMoreElements()) {
			strs[i] = (String) st.nextElement();
			i++;
		}
		return strs;


	}
	@Test
	public void testGetProductByCategoryid() {
		//List<Product> list = productService.queryByCategoryId(1);
		List<Product> list1 = productDao.queryByCategoryId(27);
		System.out.println(list1);
	}


	/*
	* Child继承Person类，
	* 实现了Child的一个实例，
	* 向上转型为Person类的引用
	* * */
	@Test
	public void test5() {
		Person p = new Child();
		p.eat();
	}

	/*
	* 匿名内部类用于继承
	* */
	@Test
	public void test6() {
		Person p = new Person() {
			// 此处方法重载 说明是实现父类 即内部类可以继承其他类， 而且是必须
			public void eat() {
				System.out.println("eat something");
			}
		};

		p.eat();
	}

	/*
	* 匿名内部类用于接口
	* */
	@Test
	public void test7() {
		IPerson p = new IPerson() {
			public void eat() {
				System.out.println("eat something in interface");
			}
		};
		p.eat();
	}

	/*
	*测试定时器
	* */
	@Autowired
	private ProductTimerTask productTimerTask = null;  // 定义一个ProductTimerTask对象
	@Test
	public void test8 () {
		new Timer(false).schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("读取数据库数据");
			}
		}, 0, 1000);

		for (int i=0; i<5; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void test9 () {
		Map<String, Object> params = new HashMap<String, Object>();
		Date first = new Date();
		categoryService.queryByHot(true);
		System.out.println(" 第一次查询： " + (new Date().getTime() - first.getTime()) + "ms");

		Date second = new Date();
		categoryService.queryByHot(true);
		System.out.println("第二次查询： " + (new Date().getTime() - second.getTime()) + "ms");

		Date third = new Date();
		categoryService.queryByHot(true);
		System.out.println("第三次次查询： " + (new Date().getTime() - third.getTime()) + "ms");

		// 不需要担心缓存中的数据与数据库中的数据不同步的问题，因为只要sqlSession进行了修改(update, insert, delete) 操作，就会清空掉缓存数据



	}
	@Test
	public void test10 () {
		Product product = productService.get("16");
		System.out.println(product);
	}

	/*
	* 使用ali的json库来将map转换为json字符串
	* */
	@Test
	public void test11 () {
		Map<String, String> map = new HashMap();
		map.put("name", "lin-cheng-qi");
		map.put("yu-name", "lin-cq");
		map.put("hobby", "program");
		map.put("hobby", "enjoying art");

		String json = JSONObject.toJSONString(map);
		System.out.println(json);
	}
	/* 测试模拟可用的json数据，模拟出来可以返回给页面 */
	@Test
	public void test13() {
		List<Map> list = new ArrayList();
		String[] sarr = new String[] {"huli", "jade", "dfadfe"};
		int[] intvalue = new int[] {123, 4353, 322321};
		for ( int i=0; i<3; i++) {
			Map map = new HashMap();
			map.put("name", sarr[i]);
			map.put("id", intvalue[i]);

			list.add(map);
		}
		String json = JSONObject.toJSONString(list);
		System.out.println(json);

		// result:   [{"id":123,"name":"huli"},{"id":4353,"name":"jade"},{"id":322321,"name":"dfadfe"}]
	}
}

abstract class Person {
	public abstract void eat();
}

interface  IPerson {
	public void eat();
}

class Child extends Person {
	public void eat() {
		System.out.println("eat something");
	}
}