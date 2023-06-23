package teamProject;

import java.util.HashMap;
import java.util.Map;

import teamProject.controller.LoginController;
import teamProject.controller.LoginSeccessController;

public class ProjectHandlerMapping {
	
	// Controller를 구현한 객체들을 저장하는 Map
	private Map<String, ProjectController> mappings;
	
	public ProjectHandlerMapping() {
		mappings = new HashMap<>();
		
//		mappings.put("/list.do", new MemberListController());
//		mappings.put("/register.do", new MemberRegisterController());
//		mappings.put("/insert.do", new MemberInsertController());
////		mappings.put("/checkId.do", new MemberCheckIdController());
//		mappings.put("/view.do", new MemberViewController());
//		mappings.put("/delete.do", new MemberDeleteController());
//		mappings.put("/modify.do", new MemberModifyController());
//		mappings.put("/update.do", new MemberUpdateController());
//		
//		mappings.put("/board.list.do", new boardListController());
//		
		
		mappings.put("/login.doo", new LoginController());
		mappings.put("/loginSeccess.doo", new LoginSeccessController());
		
	}
	
	public ProjectController getProjectController(String path) {
		// Map에 등록된 Controller들 중에서 특정 경로에 해당하는 Controller 반환
		// url: Controller => 1:1 맵핑
		return mappings.get(path);
	}
}
