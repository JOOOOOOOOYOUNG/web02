package teamProject.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

import teamProject.ProjectController;
import teamProjectService.ProjectService;

public class LoginSeccessController implements ProjectController {

	// 서비스 객체를 생성(dao, modelMapper)
	private ProjectService Service = ProjectService.INSTANCE;
		
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		String login_auto = req.getParameter("auto");
		
		String logindata = req.getParameter("logindata");
		System.out.println("login data (JSON): " + logindata);
		
		System.out.println("id => " + id);
		
		
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		
		try {
			
			jsonObject = (JSONObject)jsonParser.parse(logindata);
			
			System.out.println("login data: " + jsonObject);
			
			
		} catch (ParseException e) {e.printStackTrace();}
		
		
		id = (String) jsonObject.get("id");
		pwd = (String) jsonObject.get("pwd");
		login_auto = (String) jsonObject.get("auto");
	
		
		
		
		
		// 자동로그인 체크항목이 on일 경우 처리
		boolean rememberMe = (login_auto != null) && (login_auto.equals("on"));
		
		
		
		TMemberDTO dto = Service.loginMember(id, pwd);
		req.setAttribute("member", dto);
		
		
		String memberID = dto.getId();
		String memberPWD = dto.getPwd();
		
		
		
		JSONObject sendData = new JSONObject();
		
		
		
		if (memberID.equals(id)) {
			
			if(memberPWD.equals(pwd)) {
				
			
		// 자동로그인 체크시 처리부분
		 if(rememberMe) { // rememberMe true면 난수발생
			 String uuid = UUID.randomUUID().toString();
			
			 System.out.println("uuid" + uuid);
			 
			 // 임의의 문자열을 생성하고 이를 데이터베이스에 보관
			 ProjectService.INSTANCE.updateUUID(id, uuid);
			 dto.setUuid(uuid);
			 
			 
			 
			 // db에 저장된 난수와 같은 문자열을 쿠키에 생성
			 Cookie rememberCookie = new Cookie("remember-me", uuid);
			 rememberCookie.setMaxAge(7*24*60*60); // 7일
			 rememberCookie.setPath("/");
			 
			 // 클라이언트에게 쿠키전송
			 resp.addCookie(rememberCookie);
			 
			 
		 } 	// 자동로그인 끝
			
		 	HttpSession session = req.getSession();
			session.setAttribute("loginInfo", dto);
		
			
		
		} else {
			// 아이디 일치, 패스워드 불일치
			sendData.put("code", "pw_fail");
			System.out.println("패스워드 불일치");
		}
		
	} else {
		
		// 아이디 불일치
		sendData.put("code", "id_fail");
		System.out.println("아이디 불일치");
		
		
	}
	
			
	
		return "teamProject/loginTest";
		
	}

	
}
