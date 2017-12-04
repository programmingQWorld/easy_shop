package com.lcq.service.impl;

import com.lcq.domain.Account;
import com.lcq.service.AbstractService;
import com.lcq.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("accountService")
public class AccountServiceImpl extends AbstractServiceImpl implements AccountService {

	public List<Account> getAccounts() {
		System.out.println("进入 --> com.lcq.service.impl.AccountServiceImpl.getAccounts()");
		List<Account> list;
		list = accountDao.getAccounts();
		System.out.println("退出 --> com.lcq.service.impl.AccountServiceImpl.getAccounts()");
		return list;
	}
}
