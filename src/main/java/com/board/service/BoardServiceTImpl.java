package com.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.modelmapper.ModelMapper;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.dto.BoardDTO;
import com.member.mapper.MybatisManager;

import util.MapperUtil;

public class BoardServiceTImpl implements BoardServiceT {

	private BoardDAO boardDAO;
	private ModelMapper modelMapper;
	private SqlSession session;
	
	
	public BoardServiceTImpl() {
		boardDAO = new BoardDAO();
		modelMapper = MapperUtil.INSTANCE.get();
		
		SqlSession session = MybatisManager.getInstance().openSession();
	}
	
	@Override
	public List<BoardDTO> boardList(Map<String, Integer> pageingMap) {

		List<BoardVO> boardList = null;
		
		try {
  			
			
			// 게시글 목록
			boardList = session.selectList("member.boardList", pageingMap);
			
			
	
	 } catch (Exception e) {e.printStackTrace();}
	finally {session.close();}
				
		// vo -> dto
		List<BoardDTO> dtoList = boardList.stream()
							.map(vo -> modelMapper.map(vo, BoardDTO.class))
							.collect(Collectors.toList());
				
		return dtoList;
	}

}
