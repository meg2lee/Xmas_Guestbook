package com.example.demo.dao;

import java.util.List;

import com.example.demo.controller.AttachVO;

public interface AttachDAO {
	
	boolean upload(AttachVO a);
	List<AttachVO> fileList(int num);

}
