
package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/list/list.do")
public class MemberListController extends HttpServlet {
	
	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 공통된 경로를 제외한 url을 작업구분자로 적용
				String action = req.getPathInfo();
				String nextPage = null;
				
				log.info("/list/list.do");
				//log.info("getPathInfo(): "+action);
				
				System.out.println("/list/list.do");
				//System.out.println("getPathInfo(): "+action);
				
				try {
					List<TMemberDTO> memberList = memberService.memberList();
					System.out.println("controller=>" + memberList);
						
					req.setAttribute("memberList", memberList);
						
				} catch (Exception e) {e.printStackTrace();}
					
				nextPage = "/Member/list.jsp";
				req.getRequestDispatcher(nextPage).forward(req, resp);
					
				
	}
	
	

}
