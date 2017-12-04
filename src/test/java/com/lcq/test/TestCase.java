package com.lcq.test;

import com.lcq.domain.User;
import com.lcq.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static java.lang.Thread.sleep;
// 指定bean注入的配置文件
// 使用标准的额JUnit @RunWith注释来告诉JUnit使用spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class TestCase extends AbstractJUnit4SpringContextTests {
	/*private static SqlSessionFactory sessionFactory;
	static {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		sessionFactory = (SqlSessionFactory)applicationContext.getBean("sqlSessionFactory");
	}*/
	@Resource
	private UserService userService;
	/*
	@Test
	public void test1() {
		/*System.out.println(sessionFactory);
		SqlSession session = sessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User user = userMapper.getUserInfoById(1);
		System.out.println(user);
		User user = userService.getUserById(1);
		System.out.println(user);
	}
	*/

	public void test (Integer a) {
		System.out.println(a);
	}
	@Test
	public void testStatic () throws InterruptedException {
		System.out.println("程序正在执行");
		//sleep(1000);  使用wait()会报错
		//notify();
		test(12);

		String name = "lcq";
		System.out.println(name.length());

		String username = null;
		System.out.println("lcq".equals(username));  // 这样写不会爆出空指针异常
		try {
			System.out.println(username.equals("lcq"));
		} catch (NullPointerException ne) {
			System.out.println("请检查username是否错误");
			System.out.println("本次登录失败");


			System.exit(0); // 终止当前运行的java虚拟机。 后面的代码完全不会被执行
			System.out.println("二次登录失败");
		} finally {
			System.out.println("用户打算再次发送请求");
		}
	}

}
