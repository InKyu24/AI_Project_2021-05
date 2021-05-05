package com.bummy.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bummy.web.vo.BreakVO;

@Repository
public class BreakDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public void breakSet(BreakVO breakVO) {
		sqlSession.update("mapper.break.breakSet",breakVO);
	}

	public void signL(BreakVO breakVO) {
		sqlSession.insert("mapper.break.signL",breakVO);
		
	}

	public Boolean breakCheck(BreakVO breakVO) {
		return sqlSession.selectOne("mapper.break.breakCheck",breakVO);
	}

	public int breakTimeGet(BreakVO breakVO) {
		return sqlSession.selectOne("mapper.break.breakTimeGet",breakVO);
	}

	public String breakTimeMsgGet(BreakVO breakVO) {
		return sqlSession.selectOne("mapper.break.breakTimeMsgGet",breakVO);
	}

	public void breakBreak(BreakVO breakVO) {
		sqlSession.update("mapper.break.breakBreak",breakVO);
	}
}
