package com.project.mvcBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UController {

	// 로그인폼
	@RequestMapping(value = "/loginform", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginform() {
		System.out.println("loginform");
		return "member/loginform";
	}
	
	// 로그인실패 페이지
	@RequestMapping(value = "/loginfail", method = RequestMethod.GET)
	public String loginfail() {
		System.out.println("loginfail");
		return "member/loginfail";
	}

	// 로그아웃폼 페이지
	@RequestMapping(value = "/logoutform", method = RequestMethod.GET)
	public String logoutform() {
		System.out.println("logoutform");
		return "member/logoutform";
	}


}
