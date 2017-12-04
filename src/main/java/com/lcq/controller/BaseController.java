package com.lcq.controller;

import com.lcq.domain.Account;
import com.lcq.interfaces.FileUpload;
import com.lcq.pojo.FileImage;
import com.lcq.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class BaseController {
	/*
	* 在这里应该是  各种 service 层的集中营。
	* 1. 比如 categoryService
	* */
	@Autowired
	private CategoryService categoryService;
	@Autowired
	protected AccountService accountService;
	@Autowired
	protected ProductService productService;

	@Resource
	protected ForderService forderService;
	@Resource
	protected SorderService sorderService;
	@Resource
	protected UserService userService;

	protected FileImage fileImage;   // 封装了图片信息的类
	 @Resource  // resouse注解回去查找 与 变量 fileUpload 同名同类型的bean来注入
	protected FileUpload fileUpload;  // 上传文件的工具类


	protected  Map<String, Object> pageMap = null;//让不同的控制器自己去实现

	/* modelattribute */
	//@ModelAttribute  // 该方法暂时先不用
	public void getPageAndSize ( String page,  String rows, Model model) {  // 添加多个属性
		System.out.println("进入 --> com.lcq.controller.BaseController.getPageAndSize");
		System.out.println("-- page : " +page + " -- rows : " + rows);
		model.addAttribute("page", page);
		model.addAttribute("rows", rows);
		System.out.println("退出 --> com.lcq.controller.BaseController.getPageAndSize");
	}


	/* 以下为setter 和 getter 方法 */
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/* --------------------------------------- */
	public FileImage getFileImage() {
		return fileImage;
	}

	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}
}
