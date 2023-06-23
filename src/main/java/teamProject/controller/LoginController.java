package teamProject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dto.TMemberDTO;
import com.member.service.MemberService;

import teamProject.ProjectController;
import teamProjectService.ProjectService;

public class LoginController implements ProjectController {

	// 서비스 객체를 생성(dao, modelMapper)
	private ProjectService Service = ProjectService.INSTANCE;
		
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		
		return "teamProject/loginForm";
		
	}

	
}
