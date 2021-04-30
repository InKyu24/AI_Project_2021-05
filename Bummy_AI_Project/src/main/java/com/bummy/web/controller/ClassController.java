package com.bummy.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bummy.web.service.ClassService;
import com.bummy.web.vo.MemberVO;

@Controller
public class ClassController {

	@Autowired
	ClassService classService;
	
	@RequestMapping(value = "/class_room", method = {RequestMethod.GET}, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView classRoom(HttpServletRequest request, HttpServletResponse response) throws IOException {
			ModelAndView mav = new ModelAndView("class_room");
			Cookie cookie[] = request.getCookies();
			String user_id=cookie[1].getValue();
			String user_belong=URLDecoder.decode(cookie[4].getValue(), "UTF-8");
			String user_type="L";
			// belong 받아서, check_time 조회할 수 있도록! 
			MemberVO memberVO=new MemberVO(user_id,user_belong,user_type);
			int check_time=classService.checkTimeGet(memberVO);
			
			mav.addObject("check_time", check_time);
			return mav;
	}
	
	// 출석 확인 시간 설정
	@RequestMapping(value="/check", method= {RequestMethod.POST}, produces = "application/text; charset=utf8")
	@ResponseBody
	public String checkTimeSet(HttpServletRequest request, HttpServletResponse response){
		
		String user_belong=request.getParameter("user_belong");
		String check_hour=request.getParameter("check_hour");
		String check_minute=request.getParameter("check_minute");
		String check_Second=request.getParameter("check_Second");

		// 공백값이 포함된 경우, 기본 값으로 0을 받는다.
		if(check_hour.equals("")) {check_hour="0";}
		if(check_minute.equals("")) {check_minute="0";}
		if(check_Second.equals("")) {check_Second="0";}
		for(int i = 0; i<check_hour.length(); i++){
			if(check_hour.charAt(i) < 48 || check_hour.charAt(i) > 58) {
				//해당 char값이 숫자가 아닐 경우
				return "시 입력에는 숫자만 입력 가능합니다";
			}
		} for(int i = 0; i<check_minute.length(); i++){
			if (check_minute.charAt(i) < 48 || check_minute.charAt(i) > 58) {
				return "분 입력에는 숫자만 입력 가능합니다";
			} 
		} for(int i = 0; i<check_Second.length(); i++){
			if (check_Second.charAt(i) < 48 || check_Second.charAt(i) > 58) {
				return "초 입력에는 숫자만 입력 가능합니다";
			}
		}
		int check_time = Integer.parseInt(check_Second)+Integer.parseInt(check_minute)*60+Integer.parseInt(check_hour)*60*60;
		
		MemberVO memberVO=new MemberVO(user_belong,check_time);
		classService.checkTimeSet(memberVO);
		System.out.println(check_time);
		return user_belong+" 소속 학생들은 강의실 입장 후 "+check_time+"초 후에 출석이 확인됩니다.";
	}
}