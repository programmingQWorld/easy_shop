package com.lcq.service;

import com.lcq.domain.Category;
import com.lcq.service.impl.BaseServiceImpl;

import java.util.List;
import java.util.Map;

public interface CategoryService<Category> extends BaseService<Category> {
	/*


	public void delete(int id); //删除
	public Category get(int id); //获取一个Category
	public List<Category> query(); //获取全部Category
	*/
	// 上面可能是我的幻觉
	// 上面的基本方法都在BaseService中已经写好了。所以这里继承接口就相当于自己拥有

	public void update(Category category) throws Exception;//更新
	public void save(Category category)throws Exception; //插入
	public List<Category> queryJoinAccountForPage(String type, String targetPage, String pagesize);  // 分页查询 -- 物理分页 【使用这个方法，controller中的值交给这个方法来处理】
	public boolean deleteCategoryById(String ids);   // 根据一个id或多个id删除商品类别  // 实现单选/多选删除的方法

	public List<Category> queryJoinAccount(String type);  // 使用类别的名称查询   -- 管理员 管理 类别  // 没有实现分页
	public List<Category> queryJoinAccountForPage(Map<String, Object> map);  // 分页查询 -- 物理分页  【这个方法不会用到】
	public int getCount(String type); // 获取记录总数     “ 有时候会根据类型 ”  // 这是属于自己的特色，不需要被抽取出来
	public List<Category> getCategories();
	public List<Category> queryByHot(boolean hot);  // 根据热点来查找类别
}
