package com.lcq.service.impl;

import com.lcq.dao.*;
import com.lcq.service.AbstractService;
import com.lcq.service.SorderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/*
* 被其他的xxxServiceImpl继承
* */
public abstract class AbstractServiceImpl implements AbstractService {
	@Autowired
	private UserDao userDao;        // 被mapper-scan扫描进来
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	protected AccountDao accountDao;
	@Autowired
	protected ProductDao productDao;
	@Autowired
	protected ForderDao forderDao;
	@Autowired
	protected SorderDao sorderDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/*
	*  将 "1,4,6,7,5,3,"按逗号分隔符分开，并将结果列表返回
	* */
	public List<Integer> splitIds (String ids) {
		String[] strIds = ids.split(",");
		List<Integer> intIds = new ArrayList<Integer>();
		for ( int i=0; i<strIds.length; i++ ) {
			intIds.add( Integer.valueOf(strIds[i]) );
		}
		return intIds;
	}
}
