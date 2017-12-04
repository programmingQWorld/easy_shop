package com.lcq.service.impl;

import com.lcq.domain.Category;
import com.lcq.service.AbstractService;
import com.lcq.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("categoryService")
public class CategoryServiceImpl extends AbstractServiceImpl implements CategoryService {
		public void save(Object o) throws Exception {
		System.out.println("进入 --> com.lcq.service.impl.CategoryServiceImpl.save()");
		getCategoryDao().save((Category) o);
		System.out.println("退出 --> com.lcq.service.impl.CategoryServiceImpl.save()");
	}

	public void update(Object o) throws Exception {
		System.out.println("进入 --> com.lcq.service.impl.CategoryServiceImpl.update()");
		getCategoryDao().update((Category) o);
		System.out.println("退出 --> com.lcq.service.impl.CategoryServiceImpl.update()");
	}

	public void delete(String id) {

	}


	public Object get(String id) {
		return null;
	}

	public List query() {
		return null;
	}

	public List queryJoinAccount(String type) {
		return getCategoryDao().queryJoinAccount(type);
	}

	public List queryJoinAccountForPage(String type, String targetPage, String pagesize) {
		System.out.println("进入 -> com.lcq.service.impl.CategoryServiceImpl.queryJoinAccountForPage(java.lang.String, java.lang.String, java.lang.String)");
		int o = Integer.valueOf(targetPage);
		int s = Integer.valueOf(pagesize);
		int offset = (o-1) * s;         //   page 1: use sql : limit (0, pagesize)  -------  page 2: ues sql: limit ((2-1)*pagesize, pagesize )
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("type", type);
		map.put("offset", offset);
		map.put("pagesize", s);
		List<Category>categories = queryJoinAccountForPage(map);
		System.out.println("退出 -> com.lcq.service.impl.CategoryServiceImpl.queryJoinAccountForPage(java.lang.String, java.lang.String, java.lang.String)");
		return categories;
	}

	/*
	*根据一个或多个id删除商品类别
	* */
	public boolean deleteCategoryById (String categoryIdList) {
		System.out.println("进入 --> com.lcq.service.impl.CategoryServiceImpl.deleteCategoryById()");
		// 将 参数 ids 解析为数组
		/*String[] strIds = categoryIdList.split(",");
		List<Integer> intIds = new ArrayList<Integer>();
		for ( int i=0; i<strIds.length; i++ ) {
			intIds.add( Integer.valueOf(strIds[i]) );
		}*/
		List<Integer> intIds = splitIds(categoryIdList);
		try {
			getCategoryDao().deleteCategoryByIds(intIds);
			System.out.println("退出 --> com.lcq.service.impl.CategoryServiceImpl.deleteCategoryById()");
			return true;        // 删除成功
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("退出 --> com.lcq.service.impl.CategoryServiceImpl.deleteCategoryById()");
			return false;   // 删除失败
		}
	}

	/*
	* 根据类别名称获得商品的数量
	* */
	public int getCount(String type) {
		int count = getCategoryDao().getCount(type);
		return count;
	}

	public List getCategories() {

		List<Category> list = getCategoryDao().getCategories();
		return list;
	}

	public List queryByHot(boolean hot) {
		System.out.println("进入 -> com.lcq.service.impl.CategoryServiceImpl.queryByHot()");
		List<Category> list =  getCategoryDao().queryByHot(hot);
		System.out.println("退出 -> com.lcq.service.impl.CategoryServiceImpl.queryByHot()");
		return list;
	}

	/*
	* 使用map对象，mybatis 进行分页查询 -- 物理查询
	* */
	public List queryJoinAccountForPage(Map map) {
		List<Category> list = getCategoryDao().queryJoinAccountForPage(map);
		return list;
	}
	/*
	* 1. 仍然时什么方法都没有写，但是在调用的时候可以使用到 baseServiceImpl中的方法。
	* 2. 继承是为了能够使用那些被包装过的共性方法
	* 3. 而实现，这个目的是当service中有其他的特殊方法之时，仍然需要来实现
	* */
}
