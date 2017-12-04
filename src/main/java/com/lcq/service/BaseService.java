package com.lcq.service;

import java.util.List;

public interface BaseService<T> {
	public void save(T t) throws Exception;      // 调用dao来保存一个实体
	public void update(T t) throws Exception;    // 调用dao来修改一个实体
	public void delete(String id);  // 调用dao来删除一个实体。调用之前需要将String类型转换为 int 类型
	public T get(String id);    // 调用dao来获取一个实体。调用之前需要将String类型转换为 int 类型
	public List<T> query();     // 调用dao来获得所有的实体。
}
