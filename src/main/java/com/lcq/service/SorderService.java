package com.lcq.service;

import com.lcq.domain.Forder;
import com.lcq.domain.Product;
import com.lcq.domain.Sorder;

public interface SorderService {
	// 添加购物项目，返回新的购物车
	public Forder addSorder( Forder forder, Product produdct);
	// 把商品数据转化为购物项目
	public Sorder productToSorder(Product product);
}
