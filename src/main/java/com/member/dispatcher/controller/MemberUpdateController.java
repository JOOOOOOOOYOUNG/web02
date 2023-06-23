package com.member.dispatcher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispatcher.Controller;
import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

public class MemberUpdateController implements Controller {

	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;
		
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		TMemberDTO dto = new TMemberDTO();
		dto.setName(req.getParameter("name"));
		dto.setId(req.getParameter("id"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setEmail(req.getParameter("email"));
		
		// 수정작업 서비스 요청(DB)
		int result = memberService.modifyMember(dto);
		
		
		return "/list.do";
		
	}

}
