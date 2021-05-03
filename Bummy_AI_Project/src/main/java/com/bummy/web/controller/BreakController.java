package com.bummy.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bummy.web.service.BreakService;
import com.bummy.web.vo.BreakVO;

@Controller
public class BreakController {
	
	@Autowired
	BreakService breakService;
	
	// 회원가입 시 쉬는시간 초기화
	@RequestMapping(value ="/signL", produces = "application/text; charset=utf8", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void signL(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String user_belong=request.getParameter("user_belong");
		BreakVO breakVO = new BreakVO(user_belong);
		breakService.signL(breakVO);
	}
	
	// 쉬는 시간 설정
	@RequestMapping(value="/break_set", method= {RequestMethod.POST}, produces="application/text; charset=utf8")
	@ResponseBody
	public String breakSet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user_belong=request.getParameter("user_belong");
		int breakTime = Integer.parseInt(request.getParameter("breakTime"));
		String breakTimeMsg=request.getParameter("breakTimeMsg");
		// 스트링을 불리언으로 변경하기!
		boolean breakbool = false; 
		
		BreakVO breakVO = new BreakVO(user_belong, breakTime, breakTimeMsg, breakbool);
		breakService.breakSet(breakVO);
		return "쉬는 시간 설정 완료";
	}
	
	// 쉬는 시간 설정
	@RequestMapping(value="/break_get", method= {RequestMethod.POST}, produces="application/text; charset=utf8")
	@ResponseBody
	public void breakGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user_belong=request.getParameter("user_belong");
		
		BreakVO breakVO = new BreakVO(user_belong);
		Boolean breakbool= breakService.breakCheck(breakVO);
		System.out.println(breakbool);
		// 쉬는 시간 설정이 되어 있다면
		if (breakbool==true) {
			int breakTime = breakService.breakTimeGet(breakVO);
			String breakTimeMsg = breakService.breakTimeMsgGet(breakVO);
			System.out.println(breakTime);
			System.out.println(breakTimeMsg);
		}
	}
}
