package com.example.guestbook.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.guestbook.vo.AttachVO;

@Repository("attachDao")
public class AttachMysqlDAO implements AttachDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean upload(AttachVO a) {		
		return false;
	}

	@Override
	public List<AttachVO> fileList(int num) {
		String sql = "SELECT * FROM attach_tb WHERE num=?";
		List<AttachVO> aList = jdbcTemplate.query(sql, 
				(rs, i) -> new AttachVO(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4)),num);
		return aList;
	}
	

}
