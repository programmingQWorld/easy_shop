package com.lcq.service;

import com.lcq.domain.Product;

import java.util.List;

public interface ProductService extends AbstractService {
	public List<Product> queryJoinCategoryForPage(String name, String targetPage, String pagesize);  // 分页查询 -- 物理分页 【使用这个方法，controller中的值交给这个方法来处理】
	public boolean deleteProducts (String ids);
	public void saveProduct(Product product) throws Exception;

	public void updateProduct(Product product);
	public List<Product> queryByCategoryId(long cid);  // 根据热点类别查询推荐商品(仅查询出前4个)

	Product get(String productID);  // 根据id查询数据库中的商品数据
}
