package com.member.dispatcher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.BoardDTO;
import com.board.service.BoardServiceTImpl;
import com.member.dispatcher.Controller;

public class boardListController implements Controller {

	// 서비스 객체를 생성(dao, modelMapper)
	private BoardServiceTImpl boardServiceTImpl;
	
	public boardListController() {
		boardServiceTImpl = new BoardServiceTImpl();
	}
	
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		

		String _section = req.getParameter("pageBlock");
		String _pageNum = req.getParameter("pageNum");
		
		
		System.out.println("section: "+_section);
		System.out.println("page Number: "+_pageNum);
		
		
		int section = Integer.parseInt((_section == null) ? "1" : _section);
		int pageNum = Integer.parseInt((_pageNum == null) ? "1" : _pageNum);
		
		
		Map<String, Integer> pageingMap = new HashMap<>();
		
		pageingMap.put("section", section);
		pageingMap.put("pageNum", pageNum);
		
		
		
		try {
			List<BoardDTO> boardList = boardServiceTImpl.boardList(null);
			
		} catch (Exception e) {e.printStackTrace();}
		
		
		return "board/listBoard";
		
	}

}
