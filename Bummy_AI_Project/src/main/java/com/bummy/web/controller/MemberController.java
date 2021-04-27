package com.bummy.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.ServerCookies;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bummy.web.service.MemberService;
import com.bummy.web.vo.MemberVO;
import com.bummy.web.util.FindCookies;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	//
	@RequestMapping(value = "/pList", method = { RequestMethod.GET }, produces = "application/text; charset=utf8")	
	public ModelAndView p_Manager(HttpServletRequest request) throws IOException {
		FindCookies fc = new FindCookies();

		String user_id=fc.FindCookie(request, "user_id");
		String user_type=fc.FindCookie(request, "user_type");
		String user_belong="P";
		
		System.out.println(user_id);
		System.out.println(user_type);
		System.out.println(user_belong);
		
		ModelAndView mav = new ModelAndView("p_manager");
		MemberVO memberVO = new MemberVO(user_id,user_belong,user_type);
		List<MemberVO> pList = memberService.pList(memberVO);
		System.out.println(pList.size());
		System.out.println(memberVO);
		mav.addObject("pList",pList);
		return mav;
	}
	
	// 로그아웃 기능 구현
	@RequestMapping(value="/logout", method= {RequestMethod.POST}, produces = "application/text; charset=utf8")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession(false);
		session.invalidate();
		return "";
	}

	
	// 로그인 기능 구현
	@RequestMapping(value="/login", method= {RequestMethod.POST}, produces = "application/text; charset=utf8")			
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response){
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");		
		JSONObject json=new JSONObject();
		
		try {
			MemberVO memberVO=new MemberVO(user_id,user_pw);
			String[] user_logined = memberService.login(memberVO);
			String user_name= user_logined[0];
			String user_type= user_logined[1];
			if(user_name!=null && user_type!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("member", memberVO);
					json.put("user_id", user_id);
					json.put("user_name", user_name);
					json.put("user_type", user_type);
					json.put("condition_check", "<a class='nav-link' id='condition_check' href='#''>상태 확인</a>");
					json.put("timer", "<a class='nav-link' id='timer' href='#'>타이머</a>");
					json.put("p_manager", "<a class='nav-link' id='p_manager' href='#'>회원 관리</a>");
					
			} else {
				json.put("msg", "로그인 실패");
			}
		}catch(Exception e) {
			json.put("msg", e.getMessage());
		}		
		return json.toJSONString();
	}
	
	// 회원가입 구현
	@RequestMapping(value ="/signup", produces = "application/text; charset=utf8", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String signup(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		String user_name=request.getParameter("user_name");
		String user_phone=request.getParameter("user_phone");
		String user_email=request.getParameter("user_email");
		String user_belong=request.getParameter("user_belong");
		String user_type=request.getParameter("user_type");
		String user_img="D:\\"+user_id+".jpg";
			
		System.out.println("아이디: "+user_id+"\n비밀번호: "+user_pw+"\n이름: "+user_name+"\n전화번호: "+user_phone+"\n이메일: "+user_email+"\n소속: "+user_belong+"\n타입: "+user_type);
		
		try {
			MemberVO memberVO =new MemberVO(user_id,user_pw,user_name,user_phone,user_email,user_belong,user_type,user_img); 
			memberService.signup(memberVO);
			return user_name+"님 회원가입 되셨습니다";
		}catch(Exception e) {
			return e.getMessage();
		}		
	}
}
