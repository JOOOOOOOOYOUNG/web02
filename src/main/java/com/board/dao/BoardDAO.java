package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.board.domain.BoardVO;

import connectionDB.ConnectionOracleUtil;
import lombok.Cleanup;

public class BoardDAO {
	
	// 1. 게시글 목록
	public List<BoardVO> listBoard(Map<String, Integer> pageingMap) {
		
		int section = pageingMap.get("section");
		int pageNum = pageingMap.get("pageNum");
		
		List<BoardVO> boardList = new ArrayList<>();
		
//		System.out.println(section);
//		System.out.println(pageNum);
	
		try {
			
			String sql_backup = "select level, "
					+ "    articleNO, "
					+ "    parentNO, "
					+ "    title, "
					+ "    content, "
					+ "    writeDate, "
					+ "    id "
					+ " from t_board start with parentNO=0 "
					+ " connect by prior articleNO = parentNO "
					+ " order siblings by articleNO Desc";
			
			// jdk 17이상만 가능함 (""")
			String sql = """
					select * from (
						select
						rownum as recNum,
						LVL,
						articleNO,
						parentNO,
						title,
						content,
						writeDate,
						id
					from (
						select 
						level as LVL,
						articleNO,
						parentNO,
						title,
						content,
						writeDate,
						id
					from t_board
					start with parentNO=0
					connect by prior articleNO = parentNO
					order siblings by articleNO Desc
					)
					)
					where recNum between (?-1)*100+(?-1)*10+1 and (?)*100+ (?)*10 
					
					""";
			// where recNum between (section-1)*100+(pageNum-1)*10+1 and (section-1)*100+ (pageNum)*10 
					
					
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
		
		while(rs.next()) {
			BoardVO vo = BoardVO.builder()
						.level(rs.getInt("LVL"))
						.articleNO(rs.getInt("articleNO"))
						.parentNO(rs.getInt("parentNO"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.writeDate(rs.getDate("writeDate").toLocalDate())
						.id(rs.getString("id"))
						.build();
			
			
			boardList.add(vo);	

			
		}
			
		} catch (Exception e) {}
		
		return boardList;
	}
	
	// 1-1. 게시글 전체 개수
		public int selectTotArticles() {
			
			String sql = "select count(articleNO) from t_board";
			
			
			try {
				
				
				@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
				@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
				@Cleanup ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					return (rs.getInt(1));
				}
				

			} catch (Exception e) {}
				
			return 0;
		}
		
		
		
		
	
	// 2. 게시글 등록시 게시글번호 생성
	public int getNewArticleNO() {
		
		String sql = "select max(articleNO) from t_board";
		
		
		try {
			
			
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return (rs.getInt(1)+1);
			}
			

		} catch (Exception e) {}
			
		return 0;
	}
	
	// 3. 게시글 등록
	public int insertNewArticle(BoardVO boardVO) {
		
		int isOK = 0;
		
		String sql = "insert into t_board(articleNO, parentNO, title, content,  imageFileName, id) "
				+ " values (?,?,?,?,?,?)";
		
		
		try {
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardVO.getArticleNO());
			pstmt.setInt(2, boardVO.getParentNO());
			pstmt.setString(3, boardVO.getTitle());
			pstmt.setString(4, boardVO.getContent());
			pstmt.setString(5, boardVO.getImageFileName());
			pstmt.setString(6, boardVO.getId());
			
			
			isOK = pstmt.executeUpdate();	// DB에 존재하는 아이디인지 한번 더 확인함
			
		} catch (Exception e) {}
		
		return isOK;
	}
	
	// 4. 게시글 조회
	public BoardVO selectArticleOne(int articleNO) {
		BoardVO vo = null;
		
		String sql = "select * from t_board where articleNO=?";
		
		
		try {
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
//			vo = new BoardVO(articleNO, articleNO, sql, sql, sql, null, sql, articleNO)
			vo = BoardVO.builder()
						.articleNO(rs.getInt("articleNO"))
						.parentNO(rs.getInt("parentNO"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.imageFileName(rs.getString("imageFileName"))
						.id(rs.getString("id"))
						.writeDate(rs.getDate("writeDate").toLocalDate())
						.build();
			
		} catch (Exception e) {}
		
		return vo;
	}
	
	
	// 4. 게시글 수정
	public int updateArticle(BoardVO boardVO) {

		int rs = 0;
		
		String sql = "update t_board set title=?, content=?, imagefilename=? "
				+ "   where articleno=?";
		
		
		try {
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getContent());
			pstmt.setString(3, boardVO.getImageFileName());
			pstmt.setInt(4, boardVO.getArticleNO());
			
			
			rs = pstmt.executeUpdate();
			
		} catch (Exception e) {}
		
		return rs;
		
	}
	
	
	// 5. 게시글 삭제 (DB정보 삭제)
	public int deleteArticle(int articleNO) {
		
		int isOK = 0;
		
		String sql = "delete from t_board where articleno in ( "
				   + " select articleNO from t_board "
				   + " start with articleNO=? "
				   + " connect by prior articleNO = parentNO )";
	
		
		try {
			
			
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleNO);
			
			isOK = pstmt.executeUpdate();
			
		

		} catch (Exception e) {}
			
		return isOK;
		
		
	}
	
	// 5-2. 게시글 삭제 (이미지파일)
	public List<Integer> selectRemoveArticles(int articleNO){
		
		List<Integer> articleNOList = new ArrayList<>();
		
		int isOK = 0;
		
		String sql = " select articleNO from t_board "
				   + " start with articleNO=? "
				   + " connect by prior articleNO = parentNO ";
	
		
		try {
			
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleNO);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				articleNO = rs.getInt("articleNO");
				
				articleNOList.add(articleNO);
			}
			
			isOK = pstmt.executeUpdate();
			
		

		} catch (Exception e) {}
		
		return articleNOList;
	}
	

}