package com.lcq.listener;

import com.lcq.domain.Category;
import com.lcq.domain.Product;
import com.lcq.pojo.ProductTimerTask;
import com.lcq.service.CategoryService;
import com.lcq.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

// @Component  // 监听器时web层的组件，他是tomcat实例化的，不是Spring实例化的。不能放在spring中
public class InitDataListener implements ServletContextListener {

	private ProductService productService = null;  // productService中定义了跟商品的相关的业务逻辑
	private CategoryService categoryService = null;
	/*  初始化容器时调用的方法*/
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// 获取业务逻辑类 productService---->查询商品信息""
		System.out.println("进入InitDataListener");
		/*
			不知为什么，在这种方式之下，不但没有拿给productService  Bean ，就连最下边的控制台输出也没有起作用了
			而且在调用spring自己的监听器的时候就已经有过一次配置文件的加载过程了
			而在这里 又 new ClassPathXmlApplicationContext("applicationContext.xml");
			就又多加载了一次配置文件。。相当于同样的bean我们实例化了2次
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		productService = (ProductService)context.getBean("productService");
		System.out.println("context__>"+ context);*/
		/*ApplicationContext context = (ApplicationContext)servletContextEvent.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);*/
		// 上面这一句确实能够获取到context容器 但是写起来太长了
		// 使用下面这一句也可以
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
		productService = (ProductService) context.getBean("productService");
		categoryService = (CategoryService)context.getBean("categoryService");
		System.out.println("在监听器中获取productService" + productService);
		System.out.println("在监听器中获取categorService" + productService);
		System.out.println(context.getAutowireCapableBeanFactory());
		// 从配置文件中获取ProductTimer对象
		ProductTimerTask productTimerTask  = (ProductTimerTask)context.getBean("productTimerTask");  //  productTimerTask
		System.out.println(productTimerTask);
		System.out.println(" __ ++ ");
		// 把内置对象交给productTimerTask, 因为productTimerTask里面是拿不到application的，智能通过监听器set给它
		productTimerTask.setServletContext( servletContextEvent.getServletContext() );
		// 通过设置定时器，让首页的数据每个小时同步一次（配置为守护线程）
		new Timer(true).schedule( productTimerTask, 0, 1000*60*60);
	}
	/*  销毁容器时调用的方法 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {}

}
