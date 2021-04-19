package com.bummy.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bummy.web.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void signup(MemberVO memberVO) {
		sqlSession.insert("mapper.member.signup", memberVO);
	}

}
