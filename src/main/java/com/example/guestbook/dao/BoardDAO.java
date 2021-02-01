package com.example.guestbook.dao;

import java.util.ArrayList;

import com.example.guestbook.vo.AttachVO;
import com.example.guestbook.vo.BoardVO;

public interface BoardDAO {

	int add(BoardVO b);
	BoardVO select(int num);
	boolean update(BoardVO b);
	boolean delete(int num);
	ArrayList<BoardVO> getList();
	// 이미지 테이블 이미지를 저장
	// 이미지는 다수의 행 삽입
	// 저장
	boolean attach_insert(AttachVO attach);
}
