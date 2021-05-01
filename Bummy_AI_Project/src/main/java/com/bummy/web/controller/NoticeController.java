package com.bummy.web.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bummy.web.service.NoticeService;
import com.bummy.web.vo.NoticeVO;
import com.bummy.web.vo.MemberVO;

@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	
	// 글 삭제 처리
	@RequestMapping(value = "removeNoti", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
	@ResponseBody
	public RedirectView removeNoti(HttpServletRequest request, HttpServletResponse response){
		int notice_notiNO=Integer.parseInt(request.getParameter("notice_notiNO"));
		NoticeVO noticeVO = new NoticeVO(notice_notiNO);
		noticeService.removeNoti(noticeVO);
		return new RedirectView("noticeList");
    }	
	
	// 글 수정
    @RequestMapping(value = "modNoti", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
    public RedirectView modNoti(MultipartHttpServletRequest multipartRequest) {
    	Cookie cookie[] = multipartRequest.getCookies();
    	try {
    		multipartRequest.setCharacterEncoding("utf-8");
    		
			Map<String, Object> notiMap = new HashMap<String, Object>();
			Enumeration<String> enu = multipartRequest.getParameterNames();
			
			// 글쓰기 창에서 제목과 내용을 Enumeration으로 저장 
			while (enu.hasMoreElements()) {
				String name = enu.nextElement();
				String value = multipartRequest.getParameter(name);
				notiMap.put(name, value);
			}
			
			// 파일을 저장 
			MultipartFile file = multipartRequest.getFile("file");
			// 파일이 있는 경우, 파일 이름을 문자열로 저장
			if (file != null) {
				String fileName = file.getOriginalFilename();
				// 파일이름이 공백이 아닌 경우
				if (!fileName.equals("")) {
					System.out.println(fileName);
					// 아래 경로에 파일을 저장
					file.transferTo(new File("d:\\noticefiles\\" + fileName));
					notiMap.put("notice_filename", fileName);
				} else {
					// 파일이름이 공백인 경우에는 공백을 저장
					notiMap.put("notice_filename", "");
				}
			} else {
			// 파일 이름이 없는 경우에도 공백을 저장
			notiMap.put("notice_filename", "");
			}			
			
			if(notiMap.get("notice_parentNO")==null) {
				notiMap.put("notice_parentNO", 0);
			}
			
			String user_id=cookie[1].getValue();
			String user_name=URLDecoder.decode(cookie[2].getValue(), "UTF-8");
			notiMap.put("notice_id", user_id);
			notiMap.put("notice_name", user_name);
			
			noticeService.modNoti(notiMap);
			
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
    	return new RedirectView("noticeList");
    }	

	
	// 글쓰기 처리 
    @RequestMapping(value = "noticeWrite", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
    public RedirectView noticeWrite(MultipartHttpServletRequest multipartRequest) {
    	Cookie cookie[] = multipartRequest.getCookies();
    	
    	try {
    		multipartRequest.setCharacterEncoding("utf-8");
    		
			Map<String, Object> notiMap = new HashMap<String, Object>();
			Enumeration<String> enu = multipartRequest.getParameterNames();
			
			// 글쓰기 창에서 제목과 내용을 Enumeration으로 저장 
			while (enu.hasMoreElements()) {
				String name = enu.nextElement();
				String value = multipartRequest.getParameter(name);
				notiMap.put(name, value);
			}
			
			// 파일을 저장 
			MultipartFile file = multipartRequest.getFile("file");
			// 파일이 있는 경우, 파일 이름을 문자열로 저장
			if (file != null) {
				String fileName = file.getOriginalFilename();
				// 파일이름이 공백이 아닌 경우
				if (!fileName.equals("")) {
					System.out.println(fileName);
					// 아래 경로에 파일을 저장
					file.transferTo(new File("d:\\noticefiles\\" + fileName));
					notiMap.put("notice_filename", fileName);
				} else {
					// 파일이름이 공백인 경우에는 공백을 저장
					notiMap.put("notice_filename", "");
				}
			} else {
			// 파일 이름이 없는 경우에도 공백을 저장
			notiMap.put("notice_filename", "");
			}			
			
			if(notiMap.get("notice_parentNO")==null) {
				notiMap.put("notice_parentNO", 0);
			}
			
			String user_id=cookie[1].getValue();
			String user_name=URLDecoder.decode(cookie[2].getValue(), "UTF-8");
			notiMap.put("notice_id", user_id);
			notiMap.put("notice_name", user_name);
			
			noticeService.noticeWrite(notiMap);
			
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
    	return new RedirectView("noticeList");
    }	
    
	// 모든 글 보기
	@RequestMapping(value = "noticeList", method = { RequestMethod.GET }, produces = "application/text; charset=utf8")
	public ModelAndView noticeList(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("notice");
		List<NoticeVO> notiList = noticeService.listNoti();
		mav.addObject("notiList", notiList);
		return mav;
	}

	// 글 내용 보기
	@RequestMapping(value = "viewNoti", method = RequestMethod.GET)
	public ModelAndView viewNoti(@RequestParam("notice_notiNO") int notice_notiNO, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		NoticeVO noticeVO = noticeService.viewNoti(notice_notiNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewNoti");
		mav.addObject("noti", noticeVO);
		return mav;
	}
	
	// 글 쓰기 화면 얻기
	@RequestMapping(value = "noticeWriteForm", method = {RequestMethod.GET }, produces = "application/text; charset=utf8")
	public String noticeWriteForm(HttpServletRequest request) {
		return "noticeWriteForm";
	}
}