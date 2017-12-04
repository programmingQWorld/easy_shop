package com.lcq.dao;

import java.util.List;

public interface BaseDao<T> {
	public void save(T t);      // 保存1条记录
	public void update(T t);    // 修改1条记录
	public void delete(int id); // 根据id删除记录
	public T get(int id);       // 根据id获得记录
	public List<T> query();     // 查询表中的所有记录
}
