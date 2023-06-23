package com.member.dispatcher.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispatcher.Controller;
import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

public class MemberListController implements Controller {
	
	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("====== 요청한 서비스 호출");
		
		// 공통된 경로를 제외한 url을 작업구분자로 적용
		//String action = req.getPathInfo();	// 일부경로

		//System.out.println("getPathInfo(): "+action);
				
	
					
			try {
				List<TMemberDTO> memberList = memberService.memberList();
				req.setAttribute("memberList", memberList);
						
			} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		System.out.println("====== view페이지로 이동");
		return "member/listMember";
	}

	
	
	
	
}
