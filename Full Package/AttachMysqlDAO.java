package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.AttachVO;

@Repository("attachDao") 
/*해당 클래스를 bean객체로 생성해주는 annotation으로서, 어디서든 공유가능. @Repository는 DB나 파일같은 외부 I/O 작업을 처리함*/
public class AttachMysqlDAO implements AttachDAO { 
/*interface형 AttachDAO클래스를 상속한 AttachMysqlDAO*/
	
	@Autowired /*bean객체를 자동으로 주입받을 수 있도록하는 annotation*/
	JdbcTemplate jdbcTemplate;

	@Override /*부모클래스 method override -> 부모-자식 간 변경되는 내용이나 틀린 내용을 에러로 알 수 있게함*/
	public boolean upload(AttachVO a) {		
		return false;
	}

	@Override
	public List<AttachVO> fileList(int num) { /*AI num을 parameter로 받아 AttchVO형 list객체 생성*/
		String sql = "SELECT * FROM attach_tb WHERE num=?"; // sql query 명령
		List<AttachVO> aList = jdbcTemplate.query(sql, (rs, i) -> new AttachVO(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4)),num);
		/*AttachVO의 각 getter값을 resultset으로 i행만큼 list에 반복적으로 담음(mapping)
		  위의 sql query의 ?에 parameter로 받은 num값을 넣어, 해당 num과 동일한 rs을 list에 담아*/
		return aList; /*aList로 return*/
	}
	

}
