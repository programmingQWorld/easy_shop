package com.lcq.service.impl;

import com.lcq.dao.BaseDao;
import com.lcq.service.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.ext.ParamConverter;
import java.util.List;
/*
* 1. 要注意T泛型的写法
* 2. 这是baseservice接口的实现类，在这里面实现对一些共性的方法
* */
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
	@Resource  /* 在这里不需要再到 dao接口中声明bean的注解了。在spring-mybatis中有mapperscan的配置，在初始化容器的时候会帮我们生成实例 */
	private BaseDao<T> baseDao;
	public void save(T t) {
		baseDao.save(t);
	}

	public void update(T t) {
		baseDao.update(t);
	}

	public void delete(String id) {
		int nid = Integer.valueOf(id);
		baseDao.delete(nid);
	}

	public T get(String id) {
		int nid = Integer.valueOf(id);
		T t = baseDao.get(nid);
		return t;
	}

	public List<T> query() {
		List<T> list = baseDao.query();
		return list;
	}
}
