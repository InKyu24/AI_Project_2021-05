package com.bummy.web.dao;

import java.util.List;

import com.bummy.web.vo.MemberVO;

public interface MemberDAO {

	void signup(MemberVO memberVO) ;

	String[] login(MemberVO memberVO);
	
	List<MemberVO> pList(MemberVO memberVO);

	List<MemberVO> uList(MemberVO memberVO);
	
	void pAccept(MemberVO memberVO);

	String userFindId(MemberVO memberVO);

	String idCheck(MemberVO memberVO);

	String belongCheckL(MemberVO memberVO);

}
