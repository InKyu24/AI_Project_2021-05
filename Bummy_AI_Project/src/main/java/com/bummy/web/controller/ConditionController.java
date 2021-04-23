package com.bummy.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bummy.web.util.CompareCheck;

@RestController
public class ConditionController {
	
	CompareCheck compareCheck = new CompareCheck();
	@PostMapping("condition")
	public String realtime_upload(@RequestParam("file") MultipartFile file,HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie[] = req.getCookies();
		try {
			String user_id = cookie[1].getValue();
			file.transferTo(new File("D:\\real\\"+file.getOriginalFilename()));
			String returnMsg = compareCheck.compareFaces(user_id);
				return returnMsg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "확인 실패";
	}
}
