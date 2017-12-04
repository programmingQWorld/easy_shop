package com.lcq.test;

import com.lcq.domain.Account;
import com.lcq.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountTest extends TestCase {
	@Autowired
	private AccountService accountService;
	@Test
	public void testGetAccounts () {
		List<Account> list = accountService.getAccounts();
		System.out.println(list);
	}
}
