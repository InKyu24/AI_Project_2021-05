package com.bummy.web.dao;

import java.util.List;

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
		String user_belong = sqlSession.selectOne("mapper.member.login_userbelong", memberVO);
		sqlSession.update("mapper.member.login_attend", memberVO);
		String[] user_logined =  {user_name, user_type, user_belong};
		return user_logined;
		
	}

	@Override
	public List<MemberVO> pList(MemberVO memberVO) {
		return sqlSession.selectList("mapper.member.pList", memberVO);
	}

	@Override
	public void pAccept(MemberVO memberVO) {
		sqlSession.update("mapper.member.pAccept", memberVO);
		
	}

}
