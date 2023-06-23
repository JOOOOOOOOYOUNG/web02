package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.dto.BoardDTO;

public interface BoardServiceT {
	
	public List<BoardDTO> boardList(Map<String, Integer> pageingMap);
		
	
}
