package com.member.dispatcher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispatcher.Controller;
import com.member.service.MemberService;

public class MemberDeleteController implements Controller {

	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;
		
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		String id = req.getParameter("id");
		int result = memberService.deleteMember(id);
		
		return "/list.do";
	}
	

}
