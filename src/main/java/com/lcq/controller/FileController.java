package com.lcq.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.File;

@Controller
public class FileController {
	@Autowired
	private ServletContext servletContext;

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String uploadFile (String fileName, @RequestParam("file")CommonsMultipartFile file) {
		if (!file.isEmpty()){

			System.out.println(file.getOriginalFilename());

			String path = this.servletContext.getRealPath("/WEB-INF/upload");
			String oriFileName = file.getOriginalFilename();

			String fileType = oriFileName.substring(oriFileName.lastIndexOf("."));  // 后缀名
			System.out.println(file.toString());
			String newFileName = file.getName() + fileType;       // 文件名 + 后缀名
			File newFile = new File(path + "/" + file.getOriginalFilename());  // 路径为 /upload+newFileName

			try {
				file.getFileItem().write(newFile);      //  参数 file
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "fun";
	}

}
