package com.bummy.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassController {

	@RequestMapping(value = "/class_room", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf8")
	public ModelAndView classRoom(HttpServletRequest request) {
			ModelAndView mav = new ModelAndView("class_room");
			//int checkTimeGet = checkTime(request);
			int checkTimeGet = 3;
			mav.addObject("checkTime", checkTimeGet);
			return mav;
	}

}