package com.member.dispatcher.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispatcher.Controller;
import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

public class MemberCheckIdController implements Controller {
	
	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		String id = req.getParameter("id");
		
		// id중복체크 서비스 요청
		boolean isCheck = memberService.checkID(id);
		
		System.out.println(isCheck);
		if (isCheck) {
			System.out.println("이미 사용중인 아이디입니다.");
		} else {
			System.out.println("사용할 수 있는 아이디입니다.");
		}
		
		// 클라이언트에게 응답
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			System.out.println("123123");
		if (isCheck) {
			System.out.println("1");
			writer.print("not_usable");
		} else {
			System.out.println("2");
			writer.print("usable");
		}
			
		} catch (IOException e) {e.printStackTrace();}
		
		
		return "/register.do";
		
	}

	
}
