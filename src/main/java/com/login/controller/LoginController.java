package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.board.dto.TMemberDTO;
import com.board.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/login")
public class LoginController extends HttpServlet {

	String nextPage = "";
//	private Logger logger = 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		log.info("/login Get....");
		System.out.println("/login get 한글");
		
		String ctxPath = req.getContextPath();
		System.out.println("context path: "+ctxPath);
		
		// 로그인 페이지 전환
		//req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		nextPage ="/login/login.jsp";
		resp.sendRedirect(req.getContextPath()+nextPage);
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("/login Post....");
		
		
		
		req.setCharacterEncoding("utf-8");
		
		// 1. 폼에서 전송받은 매개변수 처리
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		String login_auto = req.getParameter("auto");
		
		
		// 2. 클라이언트에서 json구조형식 데이터를 ajax()으로 보냈을 경우
		String logindata = req.getParameter("logindata");
		log.info("login data (JSON): " + logindata);
		
		
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		
		try {
			
			jsonObject = (JSONObject)jsonParser.parse(logindata);
			log.info("login data: " + jsonObject);
			log.info("login data: " + jsonObject.get("user_id"));
			log.info("login data: " + jsonObject.get("user_pw"));
			log.info("login data: " + jsonObject.get("auto"));
			
			
		} catch (Exception e) {e.printStackTrace();}
		
		user_id = (String) jsonObject.get("user_id");
		user_pw = (String) jsonObject.get("user_pw");
		login_auto = (String) jsonObject.get("auto");
		
		log.info("user_id => "+user_id);
		log.info("user_pw => "+user_pw);
		log.info("login_auto => "+login_auto);
		
		System.out.println("user_id => "+user_id);
		System.out.println("user_pw => "+user_pw);
		System.out.println("login_auto => "+login_auto);
		
		// 자동로그인 체크항목이 on일 경우
		boolean rememberMe = (login_auto != null) && (login_auto.equals("on"));
		log.info("rememberMe status: "+rememberMe);
		System.out.println("rememberMe status: "+rememberMe);
		
		
		// db에 회원여부 확인 후 => 로그인 상태로 전환 => 세션작업
		
		TMemberDTO member = new TMemberDTO();
		String memberID = "";
		String memberPWD = "";
		
		// ID,PW 체크
//		int isOK = 0;
		
		
		try {
			
			member = MemberService.INSTANCE.login(user_id);
			memberID = member.getId();
			memberPWD = member.getPwd();
			
			} catch (Exception e) {}
			
		
		
			// 클라이언트에게 메세지 전송 응답
			req.setCharacterEncoding("utf-8");
			
			// 1. text 타입으로 응답
/*			resp.setContentType("text/plane; charset=utf-8");	*/
			
			// 2. html 타입으로 응답
			resp.setContentType("text/html; charset=utf-8");

			PrintWriter pw = resp.getWriter();
			
			//JSONArray messageArray = new JSONArray();
			JSONObject sendData = new JSONObject();
			//sendData.put(key, Value);
		
			
			
		
			if (memberID.equals(user_id)) {
				// 아이디 일치
				if (memberPWD.equals(user_pw)) {
					
					// 자동로그인 체크시 처리부분
					if (rememberMe) {	// rememberMe true면 난수발생
						String uuid = UUID.randomUUID().toString();
						
					
					
					// 사용자가 로그인 할 때 임의의 문자열을 생성하고 이를 데이터베이스에 보관
					try {
						// 로그인 승인된 회원 id로 db접속하여 uuid값을 저장
						MemberService.INSTANCE.updateUuid(user_id, uuid);
					} catch (Exception e) {e.printStackTrace();}
					
					// uuid 생성하여 db에 저장하기 전이기 때문에 직접 uuid값 보관
					member.setUuid(uuid);
					
					
					// DB에 저장된 난수와 같은 문자열을 쿠키값 생성
					Cookie rememberCookie = new Cookie("remember-me", uuid);
					rememberCookie.setMaxAge(7*24*60*60);	// 쿠키 유효기간 7일
					rememberCookie.setPath("/");
					
					
					// 클라이언트에게 쿠키전송
					resp.addCookie(rememberCookie);
					
					}	// 자동로그인 체크시 처리부분 끝
					
					
					
					
					
					// 등록회원으로 로그인 승인 => 세션 작업 수행 (기본정보를 저장)
					HttpSession session = req.getSession();
					session.setAttribute("loginInfo", member.getName());
					
					sendData.put("code", "ok");
					sendData.put("message", "로그인승인");
					
					pw.print(sendData);
					
				} else {
					// 아이디 일치, 패스워드 불일치
					sendData.put("code", "pw_fail");
					sendData.put("message", "비밀번호가 일치하지 않습니다.");
					pw.print(sendData);
					
				}
				
			} else {
				
				// 아이디 불일치
				sendData.put("code", "id_fail");
				sendData.put("message", "아이디가 일치하지 않습니다.");
				pw.print(sendData);
				
			}
			
			
			

			
			
			
		
		
			// 자동로그인 체크시 처리부분
			if (rememberMe) {	// rememberMe true면 난수발생
				String uuid = UUID.randomUUID().toString();
			}	
//				// 사용자가 로그인 할 때 임의의 문자열을 생성하고 이를 데이터베이스에 보관
//				MemberService2.INSTANCE.updateUuid(user_id, uuid);
//				member.setUuid(uuid);
//				
//				
//				// DB에 저장된 난수와 같은 문자열을 쿠키값 생성
//				Cookie rememberCookie = new Cookie("remember-me", uuid);
//				rememberCookie.setMaxAge(7*24*60*60);	// 쿠키 유효기간 7일
//				rememberCookie.setPath("/");
//				
//				
//				// 클라이언트에게 쿠키전송
//				resp.addCookie(rememberCookie);
//			} 
//			
//			
//			HttpSession session = req.getSession();
//			session.setAttribute("loginInfo", memberDTO);
//		
//			resp.sendRedirect(req.getContextPath()+"/todo/list");
//			
//			
//		} catch (Exception e) {
//			resp.sendRedirect(req.getContextPath()+"/login?result=error");
//		}
//		
//		
//		
	}




}


/*
 
	쿠키와 세션을 같이 활용하기

 	자동 로그인 준비
 	- 사용자가 로그인 할 때 임의의 문자열을 생성하고 이를 데이터베이스에 보관
 	- 쿠키에는 생성된 문자열을 값으로 삼고 유효기간은 1주일로 지정
 	
 	로그인 체크 방식
 	- 현재 사용자의 HttpSession에 로그인 정보가 없는 경우에만 쿠키를 확인
 	- 쿠키의 값과 데이터베이스의 값을 비교하고 같다면 사용자의 정보를 읽어와서 HttpSession에 사용자 정보를 추가
 
 
 
 
 


 */
