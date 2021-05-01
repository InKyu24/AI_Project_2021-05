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

import com.bummy.web.service.BoardService;
import com.bummy.web.vo.BoardVO;
import com.bummy.web.vo.MemberVO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	// 글 삭제 처리
	@RequestMapping(value = "removeArticle", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
	@ResponseBody
	public RedirectView removeArticle(HttpServletRequest request, HttpServletResponse response){
		int board_articleNO=Integer.parseInt(request.getParameter("board_articleNO"));
		System.out.println(board_articleNO);
		BoardVO boardVO = new BoardVO(board_articleNO);
		boardService.removeArticle(boardVO);
		return new RedirectView("boardList");
    }	
	
	// 글 수정
    @RequestMapping(value = "modArticle", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
    public RedirectView modArticle(MultipartHttpServletRequest multipartRequest) {
    	Cookie cookie[] = multipartRequest.getCookies();
    	try {
    		multipartRequest.setCharacterEncoding("utf-8");
    		
			Map<String, Object> articleMap = new HashMap<String, Object>();
			Enumeration<String> enu = multipartRequest.getParameterNames();
			
			// 글쓰기 창에서 제목과 내용을 Enumeration으로 저장 
			while (enu.hasMoreElements()) {
				String name = enu.nextElement();
				String value = multipartRequest.getParameter(name);
				articleMap.put(name, value);
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
					file.transferTo(new File("d:\\boardfiles\\" + fileName));
					articleMap.put("board_filename", fileName);
				} else {
					// 파일이름이 공백인 경우에는 공백을 저장
					articleMap.put("board_filename", "");
				}
			} else {
			// 파일 이름이 없는 경우에도 공백을 저장
			articleMap.put("board_filename", "");
			}			
			
			if(articleMap.get("board_parentNO")==null) {
				articleMap.put("board_parentNO", 0);
			}
			
			String user_id=cookie[1].getValue();
			String user_name=URLDecoder.decode(cookie[2].getValue(), "UTF-8");
			articleMap.put("board_id", user_id);
			articleMap.put("board_name", user_name);
			
			boardService.modArticle(articleMap);
			
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
    	return new RedirectView("boardList");
    }	

	
	// 글쓰기 처리 
    @RequestMapping(value = "boardWrite", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
    public RedirectView boardWrite(MultipartHttpServletRequest multipartRequest) {
    	Cookie cookie[] = multipartRequest.getCookies();
    	try {
    		multipartRequest.setCharacterEncoding("utf-8");
    		
			Map<String, Object> articleMap = new HashMap<String, Object>();
			Enumeration<String> enu = multipartRequest.getParameterNames();
			
			// 글쓰기 창에서 제목과 내용을 Enumeration으로 저장 
			while (enu.hasMoreElements()) {
				String name = enu.nextElement();
				String value = multipartRequest.getParameter(name);
				articleMap.put(name, value);
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
					file.transferTo(new File("d:\\boardfiles\\" + fileName));
					articleMap.put("board_filename", fileName);
				} else {
					// 파일이름이 공백인 경우에는 공백을 저장
					articleMap.put("board_filename", "");
				}
			} else {
			// 파일 이름이 없는 경우에도 공백을 저장
			articleMap.put("board_filename", "");
			}			
			
			if(articleMap.get("board_parentNO")==null) {
				articleMap.put("board_parentNO", 0);
			}
			
			String user_id=cookie[1].getValue();
			String user_name=URLDecoder.decode(cookie[2].getValue(), "UTF-8");
			articleMap.put("board_id", user_id);
			articleMap.put("board_name", user_name);
			
			boardService.boardWrite(articleMap);
			
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
    	return new RedirectView("boardList");
    }	
    
	// 모든 글 보기
	@RequestMapping(value = "boardList", method = { RequestMethod.GET }, produces = "application/text; charset=utf8")
	public ModelAndView boardList(HttpServletRequest request) {
			ModelAndView mav = new ModelAndView("board");
			List<BoardVO> articlesList = boardService.listArticles();
			mav.addObject("articlesList", articlesList);
			return mav;
	}

	// 글 내용 보기
	@RequestMapping(value = "viewArticle", method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("board_articleNO") int board_articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		BoardVO boardVO = boardService.viewArticle(board_articleNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewArticle");
		mav.addObject("article", boardVO);
		System.out.println(mav);
		return mav;
	}
	
	// 글 쓰기 화면 얻기
	@RequestMapping(value = "boardWriteForm", method = {RequestMethod.GET }, produces = "application/text; charset=utf8")
	public String boardWriteForm(HttpServletRequest request) {
		return "boardWriteForm";
	}
}