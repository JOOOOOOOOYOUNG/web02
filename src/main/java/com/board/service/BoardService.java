package com.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.dto.BoardDTO;

import util.MapperUtil;

public enum BoardService {
	INSTANCE;
	
	private BoardDAO boardDAO;
	private ModelMapper modelMapper;
	
	
	BoardService() {
		boardDAO = new BoardDAO();
		modelMapper = MapperUtil.INSTANCE.get();
	}
	
	// 게시글 목록 서비스
	public Map<String, Object> boardList(Map<String, Integer> pageingMap) {
		
		
		// 게시글 목록
		List<BoardVO> boardList = boardDAO.listBoard(pageingMap);
		
		// 게시글 총 개수
		int totArticles = boardDAO.selectTotArticles();
		
		// vo -> dto
		List<BoardDTO> dtoList = boardList.stream()
							.map(vo -> modelMapper.map(vo, BoardDTO.class))
							.collect(Collectors.toList());
		
		// 반환시 여러개의 값을 저장할 경우 Collection구조 타입으로 사용
		Map<String, Object> articleMap = new HashMap<>();
		articleMap.put("dtoList", dtoList);
		articleMap.put("totArticles", totArticles);
		
		
		return articleMap;
	}
	
	// 등록된 게시글 전체 개수
	public int selectTotArticles() {
		int totArticles = boardDAO.selectTotArticles();
		
		return totArticles;
	}
	
	
	// 게시글 조회
	public BoardDTO selectArticleOne(int articleNO) {
		BoardVO vo = boardDAO.selectArticleOne(articleNO);
		BoardDTO dto = modelMapper.map(vo, BoardDTO.class);
		
		
		return dto;
	}
	
	// 게시글 등록
	public int insertArticleNO(BoardDTO dto) {
		int articleNO = boardDAO.getNewArticleNO();
		dto.setArticleNO(articleNO);
		// dao 요청 : dto -> vo, db저장
		BoardVO vo = modelMapper.map(dto, BoardVO.class);
		int rs = boardDAO.insertNewArticle(vo);
		
		
		return rs;
		
	}
	
	
	// 게시글 수정
	public int updateArticleNO(BoardDTO dto) {
		
		BoardVO vo = modelMapper.map(dto, BoardVO.class);
		int rs = boardDAO.updateArticle(vo);
		
		return rs;
	}
	
	
	// 게시글 삭제
	public List<Integer> deleteArticleNO(int articleNO) {
		// 특정 글번호 기준으로 자식 게시글번호 추출 (하위게시글까지 리스트로 불러옴)	=> 이미지 첨부파일 폴더를 찾아서 삭제하기 위해
		List<Integer> articleNOList = boardDAO.selectRemoveArticles(articleNO);
		
		// DB에 있는 특정 글게시글 번호 삭제 (답글 게시글 포함)
		int rs = boardDAO.deleteArticle(articleNO);
		
		return articleNOList;
	}
	
	
	 
}
