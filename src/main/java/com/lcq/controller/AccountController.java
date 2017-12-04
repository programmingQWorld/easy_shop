package com.lcq.controller;

import com.lcq.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
* 管理员账号信息的增删改查
* */
@Controller
public class AccountController extends BaseController {
	@RequestMapping("account-get-all")
	@ResponseBody
	public List<Account> getAllAccount () {
		System.out.println("进入 -> com.lcq.controller.AccountController.getAllAccount()");
		List<Account> list = accountService.getAccounts();
		System.out.println("退出 -> com.lcq.controller.AccountController.getAllAccount()");
		return list;
	}
}
