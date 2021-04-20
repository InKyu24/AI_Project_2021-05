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

	@Override
	public String[] login(MemberVO memberVO) {
		String user_name = sqlSession.selectOne("mapper.member.login_username", memberVO);
		String user_type = sqlSession.selectOne("mapper.member.login_usertype", memberVO);
		String[] user_logined =  {user_name, user_type};
		return user_logined;
		
	}

}
