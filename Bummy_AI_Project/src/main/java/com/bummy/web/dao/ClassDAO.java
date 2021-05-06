package com.bummy.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bummy.web.vo.MemberVO;

@Repository
public class ClassDAO {
	
	@Autowired
	SqlSession sqlSession;

	public void checkTimeSet(MemberVO memberVO) {
		sqlSession.update("mapper.member.checkTimeSet", memberVO);
	}

	public int checkTimeGet(MemberVO memberVO) {
		return sqlSession.selectOne("mapper.member.checkTimeGet", memberVO);
	}

	public void attendP(MemberVO memberVO) {
		sqlSession.update("mapper.member.attendP", memberVO);
	}

	public void attendBreak(MemberVO memberVO) {
		sqlSession.update("mapper.member.attendBreak", memberVO);
		
	}

	public List<String> attendCheck(MemberVO memberVO) {
		return sqlSession.selectList("mapper.member.attendCheck", memberVO);
	}
}