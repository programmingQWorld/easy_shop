package com.lcq.dao;

import com.lcq.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
public interface ProductDao {
	public List<Product> queryProductJoinCategoryForPage (Map<String, Object> map);  // 分页查询

	public void deleteProducts(List<Integer> intIds) throws Exception;

	public void saveProduct(Product product) throws Exception;

	public void updateProduct(Product product);

	public List<Product> queryByCategoryId(long cid);  // 根据类别id 查询 前4个商品信息

	public Product get(int productID);  // 根据id查找商品信息
}
