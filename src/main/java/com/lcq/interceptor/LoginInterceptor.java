package com.lcq.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {

		System.out.println(" 进入 登录验证拦截器 ");
		// 判断当前session是否有用户信息
		if ( req.getSession().getAttribute("userOnline") == null ) {
			// 保存当前客户正在访问的url地址
			String goURL = req.getContextPath();  // output -eg :
			String param = req.getQueryString();  // 获得地址中携带的参数， queryString 域中存放的是get请求的参数

			if (param != null ) {
				goURL += "?" + param;  // 重新拼接好请求地址 + 参数
			}

			// 把当前客户想要访问的地址，存储到session中
			req.getSession().setAttribute("goURL", goURL);
			// 非法请求，跳转到登录页面
			res.sendRedirect( req.getContextPath() + "/ulogin");
		} else { return true; }

		System.out.println(" 退出 登录验证拦截器 ");

		return false;
	}

	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}
}
