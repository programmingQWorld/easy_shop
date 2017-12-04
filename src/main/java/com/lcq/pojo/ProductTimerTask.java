package com.lcq.pojo;

import com.lcq.domain.Category;
import com.lcq.domain.Product;
import com.lcq.service.CategoryService;
import com.lcq.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

@Component
public class ProductTimerTask extends TimerTask {  // The abstract class TimerTask has implments Runnable interface.
	@Autowired
	private ProductService productService = null;       // 注入 productService
	@Autowired
	private CategoryService categoryService = null;  // 注入categoryService

	private ServletContext servletContext = null; // 定义一个ServletContext对象（对应jsp页面中的application内置对象），因为我们更新了后台数据后（在工程运行期间，管理员向后台数据库中添加了商品信息），要存入application域里面

	// 守护线程要执行的任务
	public void run() {  // 和监听器在项目启动的时候数据初始化的逻辑一样，可参考 InitDataListener.java文件
		System.out.println("进入 -> com.lcq.pojo.ProductTimerTask.run()");
		Map<Category, List<Product>> map = new HashMap();
		// 1. 查询出热卖中的类别
		List<Category> categories = categoryService.queryByHot(true);
		if ( categories != null && categories.size() > 0 ) {
			for (Category c : categories)  {
				// 1.1.  根据 类别 id 获取推荐商品信息  1c -- np ( flter c, o)
				List<Product> list =  productService.queryByCategoryId( c.getId() );
				map.put(c, list);
			}
		}
		System.out.println(map);
		System.out.println("----++++++----");
		// 2. 把封装好的map集合交割application内置对象
		servletContext.setAttribute("shopMap", map);
		System.out.println("退出 -> com.lcq.pojo.ProductTimerTask.run()");
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;       // 通过监听器将这个applicationset进来，因为仅凭这个productTimerTask类本身是无法拿到application域对象的
	}
}
