package com.bummy.web.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bummy.web.util.DetectFace;

@RestController
public class UploadController {
	DetectFace detectFace = new DetectFace();
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		System.out.println("파일인" + file + "이 들어옴");
		try {
			// 경로 설정 관련 문제	
			file.transferTo(new File("D:\\"+file.getOriginalFilename()));
			String imgFile="D:\\"+file.getOriginalFilename();
			String result = detectFace.main(imgFile);
				return result+"개의 얼굴을 인식하였습니다.";

		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "upload 실패";
		} catch (IOException e) {
			e.printStackTrace();
			return "upload 실패";
		}
	}
}
