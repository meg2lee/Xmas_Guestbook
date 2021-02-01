package com.example.guestbook.vo;

public class AttachVO {
	
	private int num;
	private String filename;
	private Long filesize;
	private String contentType;

	public AttachVO() {}

	public AttachVO(int num, String filename, Long filesize, String contentType) {
		this.num = num;
		this.filename = filename;
		this.filesize = filesize;
		this.contentType = contentType;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	

}
