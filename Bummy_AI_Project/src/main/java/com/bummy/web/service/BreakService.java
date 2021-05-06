package com.bummy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bummy.web.dao.BreakDAO;
import com.bummy.web.vo.BreakVO;
import com.bummy.web.vo.MemberVO;

@Service
public class BreakService {
	
	@Autowired
	BreakDAO breakDAO;
	
	public void breakSet(BreakVO breakVO) {
		breakDAO.breakSet(breakVO);
	}

	public void signL(BreakVO breakVO) {
		breakDAO.signL(breakVO);
		
	}

	public Boolean breakCheck(BreakVO breakVO) {
		return breakDAO.breakCheck(breakVO);
	}

	public int breakTimeGet(BreakVO breakVO) {
		return breakDAO.breakTimeGet(breakVO);
	}

	public String breakTimeMsgGet(BreakVO breakVO) {
		return breakDAO.breakTimeMsgGet(breakVO);
	}

	public void breakBreak(BreakVO breakVO) {
		breakDAO.breakBreak(breakVO);
	}

	public String findLeaderID(MemberVO memberVO) {
		return breakDAO.findLeaderID(memberVO);
	}
}
