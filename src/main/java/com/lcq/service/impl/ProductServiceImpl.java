package com.lcq.service.impl;

import com.lcq.domain.Product;
import com.lcq.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl extends AbstractServiceImpl implements ProductService {

	public List<Product> queryJoinCategoryForPage(String name, String targetPage, String pagesize) {
		System.out.println("进入 --> com.lcq.service.impl.ProductServiceImpl.queryJoinCategoryForPage()");
		List<Product> list = null;
		Map<String, Object> map = new HashMap ();
		map.put("name", name);
		int tp = Integer.valueOf(targetPage);
		int ps = Integer.valueOf(pagesize);
		int offset = (tp-1)*ps;     // 47 10 |
		map.put("offset", offset);
		map.put("pagesize", ps);
		list = productDao.queryProductJoinCategoryForPage(map);
		System.out.println("退出 --> com.lcq.service.impl.ProductServiceImpl.queryJoinCategoryForPage()");

		return list;
	}
	/*
	根据id信息删除商品信息
	return true : 删除成功
	return fale ：删除失败
	* */
	public boolean deleteProducts(String ids) {
		System.out.println("进入-->com.lcq.service.impl.ProductServiceImpl.deleteProducts()");
		List<Integer> intIds = splitIds(ids);

		try {
			productDao.deleteProducts(intIds);
			System.out.println("退出-->com.lcq.service.impl.ProductServiceImpl.deleteProducts()");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("退出-->com.lcq.service.impl.ProductServiceImpl.deleteProducts()");
			return false;
		}
	}

	public void saveProduct(Product product) throws Exception {
		System.out.println("进入 -> com.lcq.service.impl.ProductServiceImpl.saveProduct()");
		productDao.saveProduct(product);
		System.out.println("退出 -> com.lcq.service.impl.ProductServiceImpl.saveProduct()");
	}

	public void updateProduct(Product product) {
		System.out.println("进入 -> com.lcq.service.impl.ProductServiceImpl.updateProduct() ");
		productDao.updateProduct(product);
		System.out.println("退出 -> com.lcq.service.impl.ProductServiceImpl.updateProduct() ");
	}
	/*
	* 根据热点类别查询推荐商品（仅仅查询前4个） 关键字： 热点 ，推荐 hot commend
	* */
	public List<Product> queryByCategoryId(long cid) {
		System.out.println("进入 -> com.lcq.service.impl.ProductServiceImpl.queryByCategoryId()");
		List<Product> list = productDao.queryByCategoryId(cid);
		System.out.println("退出 -> com.lcq.service.impl.ProductServiceImpl.queryByCategoryId()");
		return list;
	}

	public Product get(String productID) {
		int pid = Integer.valueOf(productID);
		Product product = productDao.get(pid);
		return product;
	}
}
