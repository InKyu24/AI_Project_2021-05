package com.bummy.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bummy.web.service.MemberService;
import com.bummy.web.vo.MemberVO;
import com.bummy.web.util.FindCookies;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	// 아이디 찾기
	@RequestMapping(value = "/findId", produces = "application/text; charset=utf8", method= {RequestMethod.POST})
	@ResponseBody
	public String findId (HttpServletRequest request, HttpServletResponse response) {
	    String user_name=request.getParameter("user_name");
	    String user_phone= request.getParameter("user_phone");
	    String user_email= request.getParameter("user_email");
	    String user_belong= request.getParameter("user_belong");
	    
        MemberVO memberVO = new MemberVO (user_name, user_phone, user_email, user_belong);
        String user_id = memberService.userFindId(memberVO);
        if (user_id == null) {
            return "아이디를 찾지 못했습니다";
        } else {
        	System.out.println(user_id);
            return user_id+"로 가입하셨던 것 같네요.\n메인페이지에서 로그인 해주세요";
        }
	}	
	// 회원 관리
	@RequestMapping(value = "/pList", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView p_Manager(HttpServletRequest request) throws IOException{
		if (request.getParameter("user_id")!=null) {
			String user_id=request.getParameter("user_id");
			System.out.println(user_id);
			MemberVO memberVO=new MemberVO(user_id);
			memberService.pAccept(memberVO); 
		} else {}
		
		FindCookies fc = new FindCookies();

		String user_id=fc.FindCookie(request, "user_id");
		String user_type=fc.FindCookie(request, "user_type");
		String user_belong="멀티캠퍼스";
		
		System.out.println(user_id);
		System.out.println(user_type);
		System.out.println(user_belong);
		
		ModelAndView mav = new ModelAndView("p_manager");
		System.out.println(mav);
		MemberVO memberVO = new MemberVO(user_id,user_belong,user_type);
		System.out.println(memberVO);
		List<MemberVO> pList = memberService.pList(memberVO);
		System.out.println(pList.size());
		System.out.println(memberVO);
		mav.addObject("pList",pList);
		return mav;
	}
	
	// 회원 관리
	@RequestMapping(value = "/uList", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView u_Manager(HttpServletRequest request) throws IOException{
		if (request.getParameter("user_id")!=null) {
			String user_id=request.getParameter("user_id");
			System.out.println(user_id);
			MemberVO memberVO=new MemberVO(user_id);
			memberService.pAccept(memberVO); 
		} else {}
		
		FindCookies fc = new FindCookies();

		String user_id=fc.FindCookie(request, "user_id");
		String user_type=fc.FindCookie(request, "user_type");
		String user_belong="멀티캠퍼스";
		
		System.out.println(user_id);
		System.out.println(user_type);
		System.out.println(user_belong);
		
		ModelAndView mav = new ModelAndView("u_manager");
		System.out.println(mav);
		MemberVO memberVO = new MemberVO(user_id,user_belong,user_type);
		System.out.println(memberVO);
		List<MemberVO> uList = memberService.uList(memberVO); 
		System.out.println(uList.size());
		mav.addObject("uList",uList);
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
			String user_belong= user_logined[2];
			if(user_name!=null && user_type!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("member", memberVO);
					json.put("user_id", user_id);
					json.put("user_name", user_name);
					json.put("user_type", user_type);
					json.put("user_belong", user_belong);
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
		String user_img="D:\\registry\\"+user_id+".jpg";
			
		System.out.println("아이디: "+user_id+"\n비밀번호: "+user_pw+"\n이름: "+user_name+"\n전화번호: "+user_phone+"\n이메일: "+user_email+"\n소속: "+user_belong+"\n타입: "+user_type+"\n사진경로: "+user_img);
		
		try {
			// ID 중복 조회
			MemberVO memberVO =new MemberVO(user_id);
			String idCheck= memberService.idCheck(memberVO);
			if (idCheck != null) {
				return "이미 가입된 아이디";
				
			// user_type이 L인 경우, 소속 중복여부 체크 [소속에는 단 하나의 L만이 존재해야 한다.]
			}else if (user_type == "L") {
				memberVO =new MemberVO(user_id,user_belong,user_type);
				String belongCheckL = memberService.belongCheckL(memberVO);
				if (belongCheckL != null) {
					return user_belong+"에 주최자 아이디가 "+belongCheckL+"로 존재합니다.";
				}
			}
			
			memberVO =new MemberVO(user_id,user_pw,user_name,user_phone,user_email,user_belong,user_type,user_img); 
			memberService.signup(memberVO);
			return user_name+"님 회원가입 되셨습니다";
		}catch(Exception e) {
			e.printStackTrace();
			return "가입 에러, 다시 시도해주세요";
		}		
	}
}
