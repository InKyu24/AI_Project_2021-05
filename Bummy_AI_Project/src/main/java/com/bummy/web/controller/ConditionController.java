package com.bummy.web.controller;

import java.io.File;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bummy.web.util.CompareCheck;
import com.bummy.web.util.DetectFace;

@RestController
public class ConditionController {
	DetectFace detectFace = new DetectFace();
	CompareCheck compareCheck = new CompareCheck();
	
	@PostMapping("condition")
	public String realtime_check(@RequestParam("file") MultipartFile file,HttpSession session, HttpServletRequest req,HttpServletResponse resp) {
		try {		
			file.transferTo(new File("/upload/real_" + file.getOriginalFilename()));
			String fileName = file.getOriginalFilename();
			String imgFile = "/upload/real_" + file.getOriginalFilename();
			int result = Integer.parseInt(detectFace.main(imgFile));
			if (result == 1) {
				String returnMsg = compareCheck.compareFaces(fileName);
				return returnMsg;
			} else if (result > 2) {
				return "얼굴이 너무 많습니다. 주최자에게 다시 출석체크를 요청해주세요.";
			} else {
				return "얼굴이 인식되지 않습니다. 자리에 계시나요?";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "확인 실패";
	}
}