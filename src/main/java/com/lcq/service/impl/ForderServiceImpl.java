package com.lcq.service.impl;

import com.lcq.domain.Forder;
import com.lcq.domain.Sorder;
import com.lcq.service.ForderService;
import com.lcq.service.SorderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("forderService")
public class ForderServiceImpl extends AbstractServiceImpl implements ForderService {
	// 计算购物车的总价
	public double cluTotal (Forder forder) {
		double total = 0.0;
		for (Sorder sorder : forder.getSorders()) {
			total += sorder.getNumber() * sorder.getPrice();
		}
		return total;
	}

	public void saveOrUpdate(Forder baseForder) {
		if ( baseForder.getId() != 0 ) {  // 更新

		}	else {
			// save
			int forderID = forderDao.save( baseForder );
			System.out.println( baseForder.getId() + "返回主键");
			Map<String, Object> map = new HashMap();
			map.put("forderID", baseForder.getId());
			map.put("sorders", baseForder.getSorders());
			sorderDao.addSorderBatch(map);
			baseForder = null;
			map = null;
		}
	}
}