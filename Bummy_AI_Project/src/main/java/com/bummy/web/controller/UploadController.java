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
			file.transferTo(new File("D:\\registry\\"+file.getOriginalFilename()));
			String imgFile="D:\\registry\\"+file.getOriginalFilename();
			int result = Integer.parseInt(detectFace.main(imgFile));
			
			if (result == 1) {
				return result+"개의 얼굴을 인식하였습니다. 얼굴 이미지 저장 완료!";
			} else if (result == 0) {
				return "인식된 얼굴이 없습니다. 정확히 다시 찍어주세요.";
			} else if (result > 1) {
				return result+"개의 얼굴을 인식하였습니다. 너무 얼굴이 많아요!";
			} else {
				return "시스템 오류, 다음에 다시 시도해주세요.";
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "upload 실패";
		} catch (IOException e) {
			e.printStackTrace();
			return "upload 실패";
		}
	}
}
