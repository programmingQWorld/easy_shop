package com.lcq.service.impl;

import com.lcq.dao.UserDao;
import com.lcq.domain.User;
import com.lcq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

	public User get(String s) {
		int id = Integer.valueOf(s);
		  User user = getUserDao().get(id);  // 调用dao
		return user;
	}

	public User login(String loginname, String loginpwd) {
		System.out.println("进入 com.lcq.service.impl.UserServiceImpl.login()");
		User u = new User();
		u.setLogin( loginname );
		u.setPassword( loginpwd );
		User user = getUserDao().login( u );

		System.out.println("退出 com.lcq.service.impl.UserServiceImpl.login()");
		return user;
	}
	/*@Autowired
	private UserDao userDao;

	public User selectUserById(Integer userId) {
		//return null;
		return userDao.selectUserById(userId);
	}

	public void updateUserInfo(User user) {
		userDao.updateUserInfo(user);
	}*/
}
