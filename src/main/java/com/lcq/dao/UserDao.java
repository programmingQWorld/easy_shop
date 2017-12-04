package com.lcq.dao;

import com.lcq.domain.User;


public interface UserDao  {
	// 用户用户的id来查找用户信息
	public User selectUserById(Integer id);
	// 修改用户的信息
	public void updateUserInfo (User user);
	public User get(int id);
	public User login(User u);  // 用户登录
}
