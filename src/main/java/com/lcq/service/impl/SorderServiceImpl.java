package com.lcq.service.impl;

import com.lcq.domain.Forder;
import com.lcq.domain.Product;
import com.lcq.domain.Sorder;
import com.lcq.service.SorderService;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service("sorderService")
public class SorderServiceImpl extends AbstractServiceImpl implements SorderService {
	// 添加购物项目，返回新的购物车
	public Forder addSorder(Forder forder, Product product) {
		boolean isHave = false;  // 用来标记有没有重复购物项
		// 拿到当前的购物项
		Sorder sorder = productToSorder(product);
		// 判断当前购物项是否重复，如果重复，则添加数量即可
		if  (forder.getSorders() == null || forder.getSorders().size() == 0) {
			forder.setSorders( new HashSet<Sorder>() );
			forder.getSorders().add(sorder);
		}
		for ( Sorder old:  forder.getSorders() ) {  // old 是购物车内的商品物种，而sorder是最新准备添加进购物车的商品信息
			if (old.getProduct().getId() == sorder.getProduct().getId()) {
				// 购物项由重复，添加数量即可
				old.setNumber( sorder.getNumber() + old.getNumber() );
				isHave = true;
				break;  // 添加当前商品成功，于是往后的循环不需要继续执行了，自然是要退出
			}
		}
		// 当前购物项在购物车中不存在，新添加即可
		if (!isHave) {
			forder.getSorders().add(sorder);
		}
		return forder;
	}

	// 将普通商品信息转换为购物项--> 订单信息
	public Sorder productToSorder(Product product) {
		Sorder sorder = new Sorder();
		sorder.setName(product.getName());
		sorder.setNumber(1);
		sorder.setPrice(product.getPrice());
		sorder.setProduct(product);
		return sorder;
	}
}
