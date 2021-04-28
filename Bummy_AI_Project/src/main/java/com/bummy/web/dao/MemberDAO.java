package com.bummy.web.dao;

import java.util.List;

import com.bummy.web.vo.MemberVO;

public interface MemberDAO {

	void signup(MemberVO memberVO) ;

	String[] login(MemberVO memberVO);
	
	List<MemberVO> pList(MemberVO memberVO);

	void pAccept(MemberVO memberVO);

}
