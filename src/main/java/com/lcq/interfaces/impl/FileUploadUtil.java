package com.lcq.interfaces.impl;

import com.lcq.interfaces.FileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.UUID;

/*
* 文件上传工具类具体实现
* */
@Component("fileUpload")   // 注意了，需要让bean配置文件来扫描这里的类
public class FileUploadUtil implements FileUpload {
	private String filePath;

	public String getFilePath() {
		return filePath;
	}
	@Value("#{configProperties['filePath']}")        // @Value注解表示去beans.xml文件中找id=“propertyConfigurer”的bean,他是通过注解的方式读取properties配置文件的，然后去响应的配置文件中读取key=filepath
	public void setFilePath(String filePath) {
		System.out.println("加载 file path " + filePath);
		this.filePath = filePath;
	}

	// 1. 通过我文件名获取扩展名
	private String getFileExt(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	// 2. 生成UUID随机数，作为新的文件名
	public String newFileName(String fileName) {
		String ext = getFileExt(fileName);
		System.out.println(" ext: " + ext);
		return UUID.randomUUID().toString() + "." + ext;
	}

	// 3. 实现文件上传的功能能，返回上传后新的文件名称
	public String uploadFile(CommonsMultipartFile file) {
		// 获取新唯一文件名
		String pic = newFileName(file.getOriginalFilename()); // 根据名称获得uuid字符串随机生成的字符串作为名字  创建新文件File对象 file.getItem().write()
		File newFile = new File(filePath +"/" + pic);  // filePath : xxx/xx/upload
		try {
			file.getFileItem().write(newFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pic;
	}
}
