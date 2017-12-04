package com.lcq.controller;

import com.lcq.domain.Forder;
import com.lcq.domain.Sorder;
import com.lcq.domain.User;
import com.lcq.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashSet;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@RequestMapping("showcart")
	public String showCart () {
		System.out.println("进入->com.lcq.controller.UserController.showCart()");
		System.out.println("退出->com.lcq.controller.UserController.showCart()");
		return "user/shopcart";
	}
	// 前往购物车页面
	@RequestMapping("/cartPage")
	public String arriiveCartPage () {
		return "user/shopcart";
	}
	@RequestMapping("login")
	public String login (String loginname, String loginpwd, HttpSession session) {
		System.out.println("进入 -> com.lcq.controller.UserController.login()");
		String result = "";
		// 进入登入判断
		User user = userService.login(loginname, loginpwd);
		if ( user == null ) {
			session.setAttribute("error", "登录失败");
			return "ulogin";
		} else {
			// 登录成功，先将用户存储到session中
			session.setAttribute("userOnline", user);
			// 根据session中goURL是否有值而决定页面的跳转
			if ( session.getAttribute("goURL") == null ) {
				result =  "index";  // 调到首页
			} else {
				result = "redirect:/" + (String)session.getAttribute("goURL");
			}
		}
		System.out.println("退出 -> com.lcq.controller.UserController.login()");
		return result;
	}

	// 购物车数据入库
	@RequestMapping("forder-save")
	public String saveForder (Forder baseForder, HttpServletRequest request) {
		System.out.println("进入->com.lcq.controller.UserController.saveForder()");
		baseForder.setDate( new Date() );// 时间
		Forder forder = (Forder)request.getSession().getAttribute("forder");
		baseForder.setSorders( (HashSet<Sorder>)forder.getSorders() );
		System.out.println( baseForder.getSorders() );
		baseForder.setTotal( baseForder.getSorders().size() );// 总量
		baseForder.setStatus( forder.getStatus());  // 状态
		// 所属用户
		baseForder.setUser( forder.getUser() );
		forderService.saveOrUpdate( baseForder );

		request.getSession().setAttribute("oldForder", baseForder);
		request.getSession().setAttribute("forder", new Forder() );


		System.out.println("退出->com.lcq.controller.UserController.saveForder()");
		return "baidu.com";
	}

}
