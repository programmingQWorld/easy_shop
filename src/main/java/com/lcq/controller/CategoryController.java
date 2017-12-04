package com.lcq.controller;

import com.alibaba.fastjson.JSONObject;
import com.lcq.domain.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController extends BaseController {

	@RequestMapping("category-query")
	//@ResponseBody  不需要添加 这个注解了。
	public String categoryQuery () {
		System.out.println("进入 --> com.lcq.controller.SendController.categoryQuery()");
		System.out.println("退出 --> com.lcq.controller.SendController.categoryQuery()");
		return "category/query";
	}
	@RequestMapping("getCategoryJSON")
	@ResponseBody
	public Map getShopJSON (String type, String page, String rows) {  // 参数：商品类别名称， 第几页， 单页记录数量
		System.out.println("进入 --> com.lcq.controller.CategoryController.getCategoryJSON()");
		System.out.println("准备返回JSON字符串");
		// 用来存储分页的数据
		pageMap = new HashMap<String, Object>();
		List<Category> categories = getCategoryService().queryJoinAccountForPage(type, page, rows);  // junit:测试通过      // page : (1, first_page) (2, second_page) ... 这些都映射到limit语句中，所以；需要在service中处理转化为正确的limit参数值
		pageMap.put("rows", categories); // 存放rows
		int count = 0;
		count = getCategoryService().getCount(type);        // junit:测试通过
		pageMap.put("total", count);  // 存放total  () --> rows 和 total 的命名都是为了配合easy_ui完成datagrid的数据装载
		System.out.println("退出 --> com.lcq.controller.CategoryController.getCategoryJSON()");
		return pageMap;
	}

	// 删除类别byid
	@RequestMapping("deleteCategoryById")
	@ResponseBody
	public String deleteCategoryById (String ids) {  // ids :: 1,2,3,4,5 ...
		System.out.println("进入 --> com.lcq.controller.CategoryController.deleteCategoryById()");
		System.out.println("删除的参数是" + ids);
		Boolean result = getCategoryService().deleteCategoryById(ids);
		System.out.println("退出 --> com.lcq.controller.CategoryController.deleteCategoryById()");
		return result.toString();
	}
	@RequestMapping("category-pre-save")
	public String categoryPreSave(Model model) {
		System.out.println("进入 -> com.lcq.controller.CategoryController.categoryPreSave()");
		//model.addAttribute(new CategoryController() );
		System.out.println("退出 -> com.lcq.controller.CategoryController.categoryPreSave()");
		return "category/save";
	}

	/*
	* 添加商品
	* */
	@RequestMapping("category-save")
	@ResponseBody
	public String dcategorySave(Category category) {
		System.out.println("进入 ->com.lcq.controller.CategoryController.dcategorySave()");
		try {
			getCategoryService().save(category);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("退出 ->com.lcq.controller.CategoryController.dcategorySave()");
		return "false";
	}

	/*
	* 更新类别（ 预备期 ）
	* */
	@RequestMapping("category-pre-update")
	public String categoryPreUpdate () {
		System.out.println("进入-> com.lcq.controller.CategoryController.categoryPreUpdate()");
		// 准备数据的回显
		System.out.println("退出-> com.lcq.controller.CategoryController.categoryPreUpdate()");
		return "category/update";
	}

	/*
	* 更新类别
	* */
	@RequestMapping("category-update")
	@ResponseBody
	public void categoryUpdate (Category category) {
		System.out.println("进入 -- > com.lcq.controller.CategoryController.categoryUpdate()");
		System.out.println(category);
		try {
			getCategoryService().update(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("退出 -- > com.lcq.controller.CategoryController.categoryUpdate()");
	}
	@RequestMapping("category-get-all")
	@ResponseBody
	public List<Category> getCategories () {
		System.out.println("进入 -> com.lcq.controller.CategoryController.getCategories()");
		List<Category>list =  getCategoryService().getCategories();
		System.out.println("退出 -> com.lcq.controller.CategoryController.getCategories()");
		return list;
	}
}


