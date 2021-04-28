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

	public void pAccept(MemberVO memberVO) {
		memberDAO.pAccept(memberVO);
	}

}
