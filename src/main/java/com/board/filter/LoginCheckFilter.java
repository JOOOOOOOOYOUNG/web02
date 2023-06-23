package com.board.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dto.TMemberDTO;
import com.board.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@WebFilter(urlPatterns = {"/tmember/update.do","/tmember/delete.do"})
public class LoginCheckFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Login Check filter...");
		System.out.println("Login Check filter...");
		
		
		// "/todo/*" 패턴으로 요청한 url은 항상 로그인체크 구현
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		if (session.getAttribute("loginInfo") != null) {
			chain.doFilter(request, response);
			
			return;
		}
		
		// session에 loginInfo값이 없으면 처리 (로그아웃상태를 의미)
		// 쿠키상태를 체크
		Cookie cookie = findCookie(req.getCookies(), "remember-me");
		
		
		
		// 세션에도 없고 쿠키도 없다면 그냥 로그인으로 처리
		if (cookie == null) {
			resp.sendRedirect(req.getContextPath()+"/login");
			
			return;
		}
		
		// 세션은 없고 쿠키값이 존재할 경우: 자동로그인 체크인 상태임을 의미
		log.info("cookie 존재하는 상황(자동로그인 상태");
		System.out.println("cookie 존재하는 상황(자동로그인 상태");
		String uuid = cookie.getValue();
		
		// 쿠키값으로 db접속하여 회원정보 가져오고, 그 정보로 세션값을 생성
		try {
			TMemberDTO tmemberDTO = MemberService.INSTANCE.getByUUID(uuid);
			log.info("쿠키의 값으로 조회한 사용자 정보: "+tmemberDTO);
			
			if (tmemberDTO == null) {
				// throw new Exception("Cookie value is not vaild");
				resp.sendRedirect(req.getContextPath()+ "/login");
			}
			
			// 회원정보를 세션에 추가
			session.setAttribute("loginInfo", tmemberDTO);
			chain.doFilter(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath()+ "/login");
		}
		
	}
		
	
	
	// 쿠키 찾기
	private Cookie findCookie(Cookie[] cookies, String name) {
		if (cookies == null || cookies.length == 0) {
		return null;
		}
		
		Optional<Cookie> result = Arrays.stream(cookies)
						.filter(ck -> ck.getName().equals(name))
						.findFirst();
				
		return result.isPresent()?result.get():null;
		
	}
	
}
