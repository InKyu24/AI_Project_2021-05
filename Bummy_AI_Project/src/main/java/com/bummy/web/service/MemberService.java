package com.bummy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bummy.web.dao.MemberDAO;
import com.bummy.web.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	public void signup(MemberVO memberVO) throws Exception{
		memberDAO.signup(memberVO);
	}

	public String[] login(MemberVO memberVO) {
		return memberDAO.login(memberVO);
	}

	public List<MemberVO> pList(MemberVO memberVO) {
		return memberDAO.pList(memberVO);
	}
	
	public List<MemberVO> uList(MemberVO memberVO) { 
		return memberDAO.uList(memberVO);
	} 

	public void pAccept(MemberVO memberVO) {
		memberDAO.pAccept(memberVO);
	}

	public String userFindId(MemberVO memberVO) {
		return memberDAO.userFindId(memberVO);
	}

	public String idCheck(MemberVO memberVO) {
		return memberDAO.idCheck(memberVO);
	}

	public String belongCheckL(MemberVO memberVO) {
		return memberDAO.belongCheckL(memberVO);
	}

}