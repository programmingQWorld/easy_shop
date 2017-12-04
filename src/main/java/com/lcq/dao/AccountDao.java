package com.lcq.dao;

import com.lcq.domain.Account;

import java.util.List;

public interface AccountDao {
	public List<Account> getAccounts();  // 获取所有的account管理员信息
}
