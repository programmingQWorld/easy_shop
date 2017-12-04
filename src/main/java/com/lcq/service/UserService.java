package com.lcq.service;

import com.lcq.domain.User;

public interface UserService {
	public User get(String s);

	public User login(String loginname, String loginpwd);  // 用户登录
	// public User selectUserById(Integer userId);  // 根据用户id查询用户信息
	// public void updateUserInfo(User user); // 修改用户的信息


	// 继承接口可以使用封装过的共性方法
	// 使用泛型可以灵活地避免类型地问题

	//  然而抽象了这么多，并没有跑起来

}
