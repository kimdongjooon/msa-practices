package com.poscodx.emaillist.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.emaillist.vo.DirectionVo;


@Repository
public class DirectionRepository {
	@Autowired
	private SqlSession sqlSession;

	public DirectionVo findAll(String coil_no) {
		System.out.println("결과 : "+sqlSession.selectOne("direction.findAll", coil_no).toString());
		return sqlSession.selectOne("direction.findAll", coil_no);
	}

//	public EmaillistVo insert(EmaillistVo email) {
//		sqlSession.insert("emaillist.insert",email);
//		return sqlSession.selectOne("emaillist.latestEmail");
//	}
//	public Boolean insert(EmaillistVo email) {
//		return sqlSession.insert("emaillist.insert",email) == 1;
//	}
	

}
