package com.member.dispatcher;

import java.util.HashMap;
import java.util.Map;

import com.member.dispatcher.controller.MemberCheckIdController;
import com.member.dispatcher.controller.MemberDeleteController;
import com.member.dispatcher.controller.MemberInsertController;
import com.member.dispatcher.controller.MemberListController;
import com.member.dispatcher.controller.MemberModifyController;
import com.member.dispatcher.controller.MemberRegisterController;
import com.member.dispatcher.controller.MemberUpdateController;
import com.member.dispatcher.controller.MemberViewController;
import com.member.dispatcher.controller.boardListController;

public class HandlerMapping {
	
	// Controller를 구현한 객체들을 저장하는 Map
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<>();
		
		mappings.put("/list.do", new MemberListController());
		mappings.put("/register.do", new MemberRegisterController());
		mappings.put("/insert.do", new MemberInsertController());
//		mappings.put("/checkId.do", new MemberCheckIdController());
		mappings.put("/view.do", new MemberViewController());
		mappings.put("/delete.do", new MemberDeleteController());
		mappings.put("/modify.do", new MemberModifyController());
		mappings.put("/update.do", new MemberUpdateController());
		
//		mappings.put("/board.list.do", new boardListController());
		
	}
	
	public Controller getController(String path) {
		// Map에 등록된 Controller들 중에서 특정 경로에 해당하는 Controller 반환
		// url: Controller => 1:1 맵핑
		return mappings.get(path);
	}
}
