package com.lcq.service;

import com.lcq.domain.Forder;

public interface ForderService {
	// 计算购物总价格
	public double cluTotal (Forder forder);

	public void saveOrUpdate(Forder baseForder);
}