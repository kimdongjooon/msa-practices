package com.poscodx.emaillist.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.emaillist.vo.EmaillistVo;

@Repository
public class EmaillistRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<EmaillistVo> findAll(String keyword) {
		return sqlSession.selectList("emaillist.findAll", keyword);
	}

//	public EmaillistVo insert(EmaillistVo email) {
//		sqlSession.insert("emaillist.insert",email);
//		return sqlSession.selectOne("emaillist.latestEmail");
//	}
	public Boolean insert(EmaillistVo email) {
		return sqlSession.insert("emaillist.insert",email) == 1;
	}
	

}
