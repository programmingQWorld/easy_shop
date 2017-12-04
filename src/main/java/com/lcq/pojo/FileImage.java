package com.lcq.pojo;

import java.io.File;

public class FileImage {
	private File file;
	private String contentTye;
	private String filename;

	public File getFile() {
		return file;
	}

	public void setUpload(File file) {  // set方法可以不用和属性名一样，但是前台传进来时的参数得和set方法名相同。即前台穿的参数为fileImage.upload
		this.file = file;
	}

	public String getContentTye() {
		return contentTye;
	}

	public void setContentTye(String contentTye) {
		this.contentTye = contentTye;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
