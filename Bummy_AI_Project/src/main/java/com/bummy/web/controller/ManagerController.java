package com.bummy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bummy.web.service.MemberService;

@Controller
public class ManagerController {
	
	@Autowired
	MemberService memberService;	
}
