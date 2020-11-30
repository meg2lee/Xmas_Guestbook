package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.AttachVO;
import com.example.demo.controller.BoardVO;
import com.example.demo.controller.UserVO;

@Repository("boardDao")
public class BoardMysqlDAO implements BoardDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int add(BoardVO b) {
		String sql = "INSERT INTO board_tb VALUES(NULL,?,?,?,?)";
			
		KeyHolder keyHolder = new GeneratedKeyHolder();
		//생성된 primary key를 갖게되는 key를 저장
		jdbcTemplate.update(conn->{
			PreparedStatement pstmt = conn.prepareStatement( //수정으로 pstmt생성 - > sql실행
				sql, new String[] {"num"}); // 배열생성->원소초기화(num값)->메모리할당
			pstmt.setString(1, b.getAuthor());
			pstmt.setString(2, b.getWdate());
			pstmt.setString(3, b.getTitle());
			pstmt.setString(4, b.getContents());
			return pstmt; //함수종료후, 함수선언/호출한 conn으로 돌아감
		},keyHolder); // 두번째 argument에 pstmt 실행후, AI값 저장
		return keyHolder.getKey().intValue();
	}

	@Override
	public BoardVO select(int num) {
		String sql = "SELECT * FROM board_tb WHERE num=?";
		return jdbcTemplate.queryForObject(sql, (rs,i)-> 
			new BoardVO(rs.getInt(1),rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5))
			,num);
	}

	@Override
	public ArrayList<BoardVO> getList() {
		String sql = "SELECT * FROM board_tb";
		List<BoardVO> bList = jdbcTemplate.query(sql, 
		(rs,i)-> new BoardVO(rs.getInt(1),rs.getString(2),
				rs.getString(3), rs.getString(4),rs.getString(5))); 
		return new ArrayList<BoardVO>(bList);
	}

	@Override
	public boolean update(BoardVO b) {
		String sql = "UPDATE board_tb SET contents=? WHERE num=?";
		int rows = jdbcTemplate.update(sql, b.getContents(), b.getNum());
		return rows>0;
	}

	@Override
	public boolean delete(int num) {
		String sql = "DELETE FROM board_tb WHERE num=?";
		int rows = jdbcTemplate.update(sql, num); /*update method는 int값만 return 가능*/
		return rows>0;
	}

	@Override
	public boolean attach_insert(AttachVO attach) {
		String sql = "INSERT INTO attach_tb VALUES(?,?,?,?)";
		int rows = jdbcTemplate.update(sql, attach.getNum(), attach.getFilename(), attach.getFilesize(), attach.getContentType());
		return rows > 0;
	}


}
