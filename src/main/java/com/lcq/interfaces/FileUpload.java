package com.lcq.interfaces;

import com.lcq.pojo.FileImage;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
* 文件上传工具类接口
* */
public interface FileUpload {
	// 实现文件上传的功能，返回上传后新的文件名称
	public String uploadFile(CommonsMultipartFile file);
	public String newFileName(String fileName);
}
