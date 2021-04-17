package com.bummy.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bummy.web.service.MemberService;
import com.bummy.web.vo.MemberVO;

public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "join",	method= {RequestMethod.POST}, produces = "application/text; charset=utf8")
	
	@ResponseBody
	public String join(HttpServletRequest request, HttpServletResponse response)throws Exception{

		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		String user_name=request.getParameter("user_name");
		String user_type=request.getParameter("user_type");
		if (user_type == "L") {
			user_type = "관리자";
			} else if (user_type == "P") {
			user_type = "참여자";
			} else {
				System.out.println("알 수 없는 사용자 로그인");
			}
			
		System.out.println("joint:"+user_id+"\t"+user_pw+"\t"+user_name+"\t"+user_type);
		
		try {
			MemberVO m=new MemberVO(user_id,user_pw,user_name,user_type); 
			memberService.join(m);
			return user_name+user_type+"님 회원가입 되셨습니다";
		}catch(Exception e) {
			return e.getMessage();
		}		
	}
}
