package com.member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.modelmapper.ModelMapper;

import com.member.domain.TMemberVO;
import com.member.dto.TMemberDTO;
import com.member.mapper.MemberMapper;

import connectionDB.ConnectionOracleUtil;
import lombok.extern.log4j.Log4j2;
import util.MapperUtil;

@Log4j2
public enum MemberService {
	INSTANCE;
	
	private ModelMapper modelMapper;
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	private MemberMapper memberMapper;
	
	// 생성자
	private MemberService() {
	try {
		
		modelMapper = MapperUtil.INSTANCE.get();
		sqlSessionFactory = ConnectionOracleUtil.INSTANCE.getSqlSessionFactory();
		session = sqlSessionFactory.openSession();
		
		
	} catch (Exception e) {e.printStackTrace();}
		
	
	}
	
	// 회원목록 서비스
	public List<TMemberDTO> memberList(){
		
		memberMapper = session.getMapper(MemberMapper.class);
		
		List<TMemberVO> memberList = memberMapper.selectAll();
		
		List<TMemberDTO> dtoList = memberList.stream()
					.map(vo -> modelMapper.map(vo, TMemberDTO.class))
					.collect(Collectors.toList());
		
		//System.out.println("\n=== mybatis mapper memberVO(): " + memberList);
		System.out.println("\n=== mybatis mapper memberDTO(): " + dtoList);
		session.commit();
		
		return dtoList;
		
	}
	
	
	// 회원등록 서비스
	public int addMember(TMemberDTO dto) {
		memberMapper = session.getMapper(MemberMapper.class);
		
		TMemberVO vo = modelMapper.map(dto, TMemberVO.class);
		int insertOK = memberMapper.addMember(vo);
		session.commit();
		
		return insertOK;
	}
	
	
	// 회원조회 서비스
	public TMemberDTO findMember(String id) {
		memberMapper = session.getMapper(MemberMapper.class);
		
		TMemberVO vo = memberMapper.memberSelectOne(id);
		TMemberDTO dto = modelMapper.map(vo, TMemberDTO.class);
		
		return dto;
	}
	
	
	// 회원수정 서비스
	public int modifyMember(TMemberDTO dto) {
		memberMapper = session.getMapper(MemberMapper.class);
		
		int updateOK = 0;
		
		TMemberVO vo = modelMapper.map(dto, TMemberVO.class);
		updateOK = memberMapper.memberUpdate(vo);
		session.commit();
		
		return updateOK;
	}
	
	// 회원삭제 서비스
	public int deleteMember(String id) {
		memberMapper = session.getMapper(MemberMapper.class);
		
		int deleteOK = 0;
		deleteOK = memberMapper.memberDelete(id);
		session.commit();
		
		return deleteOK;
	}
	
	// ID 중복체크
	public Boolean checkID(String id) {
		memberMapper = session.getMapper(MemberMapper.class);
		
		String isCheck = memberMapper.checkID(id);
		return Boolean.parseBoolean(isCheck);
	}
	
	
}
