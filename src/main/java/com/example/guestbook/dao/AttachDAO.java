package com.example.guestbook.dao;

import java.util.List;

import com.example.guestbook.vo.AttachVO;

public interface AttachDAO {
	
	boolean upload(AttachVO a);
	List<AttachVO> fileList(int num);

}
