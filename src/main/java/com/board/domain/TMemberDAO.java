package com.board.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionDB.ConnectionOracleUtil;
import lombok.Cleanup;

public class TMemberDAO {
	
	
	// sql실행 테스트
	public String getTime() throws Exception {
		String now = null;
		
		// @Cleanup => auto close() 기능: 자원해제
		@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement("select to_char(sysdate) from dual");
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		now = rs.getString(1);
		
		
		return now;
	}
	
	
	
	// 회원목록
	public List<TMemberVO> memberList() {
		
		List<TMemberVO> memberList = new ArrayList<>();
		
		try {
			
		String sql = "select * from t_member";
		

		@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		
		while(rs.next()) {
			TMemberVO vo = TMemberVO.builder()
						.id(rs.getString("id"))
						.pwd(rs.getString("pwd"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.joinDate(rs.getDate("joinDate").toLocalDate())	// sql타입의 date를 localDate타입으로 전환
						.build();
			
			memberList.add(vo);
			}
		} catch (Exception e) {}
		
		return memberList;
	}
	
	
	
	// 회원등록
	public int addMember (TMemberVO vo) {
		int result = 0;
		try {
			
		String sql = "insert into t_member (id,pwd,name,email) "
					+ "VALUES (?,?,?,?)";
	
		@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();	
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getName());
		pstmt.setString(4, vo.getEmail());
		
		// 0을 반환(false) => 쿼리문 실행 실패
		// 1을 반환(true)  => 쿼리문 정상 실행
		result = pstmt.executeUpdate();
		
		} catch (Exception e) {}
	
		return result;
	}
	
	
	
	// 회원수정
	public int modifyMember(TMemberVO vo) {
		
		int result = -10;
		try {
			
			String sql = "update t_member set pwd=?, name=?, email=? where id = ?";
			
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();	
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {}
		
		return result;
	}
	
	
	// 회원조회
	public TMemberVO findMember(String id) {
		
		TMemberVO vo = null;
	
		try {
			
			String sql = "select * from t_member where id = ?";
			
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			
			rs.next();
			vo = TMemberVO.builder()
					.id(rs.getString("id"))
					.pwd(rs.getString("pwd"))
					.name(rs.getString("name"))
					.email(rs.getString("email"))
					.joinDate(rs.getDate("joinDate").toLocalDate())
					.build();
			
		} catch (Exception e) {}
		
		return vo;
	}
		
	
	// 회원삭제
	public int deleteMember(String id) {
		
		int result = 0;
		String sql = "delete from t_member where id = ?";
		
		
		try {
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {}
		
		return result;
	}
	
	
	// ID 중복체크
	public Boolean checkId(String id) {
		Boolean isCheck = false;
		try {
			
			String sql = "select decode (count(*),1,'true','false') as isCheck from t_member where id = ?";
			
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			
			rs.next();
			// 'false' => false, 'true' => true
			isCheck = Boolean.parseBoolean(rs.getString("isCheck"));
					
			
		} catch (Exception e) {}
		
		return isCheck;
		
	}
	
	
	//======================================================================//
	
	// 자동 로그인 체크시 수행하는 메서드
		public void updateUuid(String id, String uuid) throws Exception {
			String sql = "update t_member set uuid=? where id=?";
			

			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uuid);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
		}
		
		// 쿠키값으로 DB정보 추출하는 메서드
		public TMemberVO selectUUID(String uuid) throws Exception {
			String sql = "SELECT * FROM t_member WHERE UUID=?";
			
			@Cleanup Connection conn = ConnectionOracleUtil.INSTANCE.getConnection();
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uuid);
			
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			TMemberVO tmemberVO = TMemberVO.builder()
							.id(rs.getString(1))
							.pwd(rs.getString(2))
							.name(rs.getString(3))
							.uuid(rs.getString(4))
							.build();
			
			return tmemberVO;
		}
		
	
	
	
	
}
