package com.member.dispatcher.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispatcher.Controller;
import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

public class MemberInsertController implements Controller {

	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;
	
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
	
							
		// 입력할 데이터 전달받기
		TMemberDTO dto = new TMemberDTO();
					
		dto.setId(req.getParameter("id")); 
		dto.setPwd(req.getParameter("pwd"));
		dto.setName(req.getParameter("name"));
		dto.setEmail(req.getParameter("email"));
					
		// 회원정보등록 서비스 요청
		int result = memberService.addMember(dto);
		
		
		return "/list.do";
	}

}
