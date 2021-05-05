package com.bummy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bummy.web.dao.ClassDAO;
import com.bummy.web.vo.MemberVO;

@Service
public class ClassService {
	
	@Autowired
	ClassDAO classDAO;
	
	public void checkTimeSet(MemberVO memberVO) {
		classDAO.checkTimeSet(memberVO);
	}

	public int checkTimeGet(MemberVO memberVO) {
		return classDAO.checkTimeGet(memberVO);
	}

	public void attendP(MemberVO memberVO) {
		classDAO.attendP(memberVO);
		
	}

	public void attendBreak(MemberVO memberVO) {
		classDAO.attendBreak(memberVO);
	}

	public String attendCheck(MemberVO memberVO) {
		return classDAO.attendCheck(memberVO);
	}
}
