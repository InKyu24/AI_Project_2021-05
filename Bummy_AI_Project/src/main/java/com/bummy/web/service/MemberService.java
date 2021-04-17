package com.bummy.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bummy.web.dao.MemberDAO;
import com.bummy.web.vo.MemberVO;

public class MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	public void signup(MemberVO m) throws Exception{
		memberDAO.signup(m);
	}

}