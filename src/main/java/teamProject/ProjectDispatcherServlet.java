package teamProject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispatcher.HandlerMapping;
import com.member.dispatcher.ViewResolver;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("*.doo")
public class ProjectDispatcherServlet extends HttpServlet {
	
	private ProjectHandlerMapping PhandlerMapping;
	private ProjectViewResolver PviewResolver;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=====service");	
		
		String uri, path, viewName, view;
		
		// 1. 요청 uri분석 및 컨텍스트 경로 추출
		uri = req.getRequestURI();	// 전체경로
		path = uri.substring(uri.lastIndexOf("/"));
//		String action = req.getPathInfo();	// 일부경로
		
//		System.out.println("uri => " + uri);
//		System.out.println("action => " + action);
		
		
		// 2. path에 해당하는 Controller검색(추출)
		ProjectController projectController = PhandlerMapping.getProjectController(path);
		
		
		// 3. 추철한 Controller 실행
		viewName = projectController.handleRequest(req, resp);
		System.out.println("viewName: " + viewName);
		
		
		// 4. viewResolver를 통해 viewName에 해당하는 화면을 검색
		view = getNextViewPage(viewName);
		System.out.println("view: " + view);
		
		
		// 5. 검색된 view페이지로 이동하기
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
		
	}
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("=====init");
		
		// url에 해당되는 Controller객체 생성
		PhandlerMapping = new ProjectHandlerMapping();
		
		// view 페이지이동을 위한 view페이지 사전 설정하는 객체
		PviewResolver = new ProjectViewResolver();
		
		PviewResolver.setPrefix("./WEB-INF/");
		PviewResolver.setSuffix(".jsp");
		
	}
	
	// viewName에서 이동할 페이지 경로 및 파일명 최종 추출
	public String getNextViewPage(String viewName) {
		String view = "";
		if (!viewName.contains(".doo")) {
			if (viewName.equals("index")) {
				view = viewName + ".jsp";
			} else {
				view = PviewResolver.getView(viewName);
			}
		} else {
			view = viewName;
		}
		
		return view;
	}
	
	
	
}
