package com.example.guestbook.vo;

public class BoardVO {
	
	private int num;
	private String author;
	private String wdate;
	private String title;
	private String contents;
	
	public BoardVO() {}

	public BoardVO(int num, String author, String wdate, String title, String contents) {
		this.num = num;
		this.author = author;
		this.wdate = wdate;
		this.title = title;
		this.contents = contents;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}	
	
}
