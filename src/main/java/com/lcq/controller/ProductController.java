package com.lcq.controller;

import com.lcq.domain.Category;
import com.lcq.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

@Controller
public class ProductController extends BaseController {
	private String baseUrl = "product";
	/*
	* 跳转到query
	* */
	@RequestMapping("product-query")
	public String queryProduct () {
		System.out.println("进入 --> com.lcq.controller.ProductController.queryProduct()");
		System.out.println("退出 --> com.lcq.controller.ProductController.queryProduct()");
		return baseUrl + "/query";
	}


	/*
	* 请求service层，以获得product join category infos
	* */
	@RequestMapping("productsjoincategory")
	@ResponseBody
	public List<Product> getProducts (String name, String page, String rows) {
		System.out.println("进入 --> com.lcq.controller.ProductController.getProducts()");
		// 页面可能会传入name属性
		List<Product> list = productService.queryJoinCategoryForPage(name, page, rows);
		System.out.println("退出 --> com.lcq.controller.ProductController.getProducts()");
		return list;
	}

	/*
	* 删除商品
	* */
	@RequestMapping("deleteProducts")
	@ResponseBody
	public String deleteProducts (String ids) {
		System.out.println("进入 -> com.lcq.controller.ProductController.deleteProducts()");
		boolean result = productService.deleteProducts(ids);
		if (result) {
			System.out.println("退出 -> com.lcq.controller.ProductController.deleteProducts()");
			return "true";
		}
		System.out.println("退出 -> com.lcq.controller.ProductController.deleteProducts()");
		return "failed";
	}

	/*
	* 预添加商品信息
	* */
	@RequestMapping("product-pre-save")
	public String addProductPre () {
		System.out.println("进入 --> com.lcq.controller.ProductController.addProductPre()");
		System.out.println("退出 --> com.lcq.controller.ProductController.addProductPre()");
		return "product/save";
	}


	/*
	* 保存商品
	* */
	@RequestMapping("product-save")
	public String saveProduct( Product product, @RequestParam("image")CommonsMultipartFile file) {
		System.out.println("进入 -> com.lcq.controller.ProductController.saveProduct()");

		String newFileName = fileUpload.uploadFile(file);
		product.setPic( newFileName );
		product.setDate( new Date() );

		try {
			productService.saveProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}

		System.out.println("退出 -> com.lcq.controller.ProductController.saveProduct()");
		return "false";
	}

	/*
	* 预更新商品
	* */
	@RequestMapping("product-pre-update")
	public String updateProductPre () {
		System.out.println("进入 -> com.lcq.controller.ProductController.updateProductPre()");
		// 查找出对应id的product信息
		System.out.println("退出 -> com.lcq.controller.ProductController.updateProductPre()");
		return "product/update";
	}

	/*
	* 更新商品
	* */
	@RequestMapping("product-update")
	@ResponseBody
	public String updateProduct (Product product, @RequestParam("image")CommonsMultipartFile file) {
		System.out.println("进入 -> com.lcq.controller.ProductController.updateProduct()");
		if ( !(file==null))  {
			//  保存 文件 到服务器 /WEB-INF/upload/ 的目录下
			String newFileName = fileUpload.uploadFile(file);
			product.setPic( newFileName );
			product.setDate(new Date());
			productService.updateProduct(product);
		}

		System.out.println("退出 -> com.lcq.controller.ProductController.updateProduct()");
		return null;
	}


	@RequestMapping("product/images/{fileName}")
	public String getProductImage (@PathVariable ("fileName")String fileName) {
		System.out.println("进入 -> com.lcq.controller.ProductController.getProductImage()");
		System.out.println(fileName);
		System.out.println("进入 -> com.lcq.controller.ProductController.getProductImage()");
		return "upload/" + fileName;
	}
}