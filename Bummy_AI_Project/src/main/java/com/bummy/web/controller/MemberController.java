package com.bummy.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bummy.web.service.MemberService;
import com.bummy.web.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "signup.do",	method= {RequestMethod.POST}, produces = "application/text; charset=utf8")
	
	@ResponseBody
	public String join(HttpServletRequest request, HttpServletResponse response)throws Exception{

		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		String user_name=request.getParameter("user_name");
		String user_phone=request.getParameter("user_phone");
		String user_email=request.getParameter("user_email");
		String user_belong=request.getParameter("user_belong");
		String user_type=request.getParameter("user_type");
			
		System.out.println("아이디: "+user_id+"\n비밀번호: "+user_pw+"\n이름: "+user_name+"\n전화번호: "+user_phone+"\n이메일: "+user_email+"\n소속: "+user_belong+"\n타입: "+user_type);
		
		try {
			MemberVO m=new MemberVO(user_id,user_pw,user_name); 
			memberService.signup(m);
			return user_name+"님 회원가입 되셨습니다";
		}catch(Exception e) {
			return e.getMessage();
		}		
	}
}
