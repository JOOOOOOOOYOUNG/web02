package com.member.dispatcher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispatcher.Controller;
import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

public class MemberViewController implements Controller {
	
	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		String id = req.getParameter("id");
		
		TMemberDTO memberDTO = memberService.findMember(id);
		req.setAttribute("member", memberDTO);	// 뷰에 보여질 데이터를 저장
		
	
		return "member/viewMember";
	}

}
