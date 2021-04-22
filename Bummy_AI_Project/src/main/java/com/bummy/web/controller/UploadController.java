package com.bummy.web.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		System.out.println("파일인" + file + "이 들어옴");
		try {
			// 경로 설정 관련 문제	
			file.transferTo(new File("D:\\"+file.getOriginalFilename()));
			return "upload 성공";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "upload 실패";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "upload 실패";
		}
	}
}
