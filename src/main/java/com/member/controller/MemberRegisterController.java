package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/tmember/insert.do")
public class MemberRegisterController extends HttpServlet {
	
	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;
	
	// 공통된 경로를 제외한 url을 작업구분자로 적용
	String action = null;
	String nextPage = null;
	
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 입력폼
		// 회원등록 폼 요청시
		nextPage = "/Member/memberForm.jsp";
		req.getRequestDispatcher(nextPage).forward(req, resp);	
		
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// insert db 처리
		
		// 입력할 데이터 전달받기
		TMemberDTO dto = new TMemberDTO();
		
		dto.setId(req.getParameter("id")); 
		dto.setPwd(req.getParameter("pwd"));
		dto.setName(req.getParameter("name"));
		dto.setEmail(req.getParameter("email"));
		
		// 회원정보등록 서비스 요청
		int result = memberService.addMember(dto);
		
		//nextPage = "/member/listMember.do";
		//resp.sendRedirect(req.getContextPath()+nextPage);	
	}
		
	
		
}
