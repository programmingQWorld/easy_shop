package com.lcq.controller;

import com.lcq.domain.Forder;
import com.lcq.domain.Product;
import com.lcq.domain.Sorder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class SorderController  extends BaseController {
	/*
	* 添加商品到购物车
	* */
	@RequestMapping("sorder-add/{pid}")
	@ResponseBody
	public String addSorder (@PathVariable("pid") String productID, HttpSession session) {
		// 1. 根据product.id获取相应的商品数据

		Product product = productService.get(productID);
		//System.out.println(product);
		// 2. 判断当前session是否有购物车，如果没有则创建
		if ( session.getAttribute("forder") == null ) {
			session.setAttribute("forder", new Forder(new HashSet<Sorder>()));
		}

		// 3. 把商品信息转化为sorder，并且添加到购物车中（判断购物项是否重复。
		Forder forder = (Forder) session.getAttribute("forder");


		Forder updateForder = sorderService.addSorder( forder, product);

		// 4. 计算购物的总价格
		updateForder.setTotal(forderService.cluTotal(forder));
		// 5. 把新的购物车存储到session中
		session.setAttribute("forder", updateForder);
		return "" +updateForder.getSorders().size() + "";
	}



	// 响应当前购物车信息
	@RequestMapping("shopcartinfo")
	@ResponseBody
	public List<Map> responseCartInfo (HttpSession session) {
		System.out.println("进入 -> com.lcq.controller.SorderController.responseCartInfo()");

		Forder forder = (Forder)session.getAttribute("forder");
		List<Map> list = new ArrayList();
		Set<Sorder> sorders = forder.getSorders();
		int i = 1;
		for (Sorder sorder : sorders) {  // id pic name number price (number*price)
			Map map = new HashMap();
			map.put( "id", i++ );
			map.put("name", sorder.getName() );
			map.put("quantity", sorder.getNumber() );
			map.put("price", sorder.getPrice() );
			list.add( map );
		}
		System.out.println("退出 -> com.lcq.controller.SorderController.responseCartInfo()");
		return list;
	}
}
