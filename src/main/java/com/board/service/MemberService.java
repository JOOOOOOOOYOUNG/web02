package com.board.service;

import org.modelmapper.ModelMapper;

import com.board.domain.TMemberDAO;
import com.board.domain.TMemberVO;
import com.board.dto.TMemberDTO;

import lombok.extern.log4j.Log4j2;
import util.MapperUtil;

@Log4j2
public enum MemberService {
	INSTANCE;
	
	private TMemberDAO dao;
	private ModelMapper modelMapper;
	
	private MemberService() {
		dao = new TMemberDAO();
		modelMapper = MapperUtil.INSTANCE.get();
		
	}
	
	// 로그인 서비스
	public TMemberDTO login (String id) throws Exception {
		TMemberVO vo = dao.findMember(id);
		TMemberDTO tmemberDTO = modelMapper.map(vo, TMemberDTO.class);
		
		return tmemberDTO;
	}
	
	// 자동로그인 체크시 uuid에 임의문자열을 저장(수정)
	public void updateUuid(String id, String uuid) throws Exception {
		dao.updateUuid(id, uuid);
	}
	// 자동 로그인 상태일 경우 쿠키값을 읽어 db정보을 추출하는 메서드
	public TMemberDTO getByUUID(String uuid) throws Exception {
		TMemberVO vo = dao.selectUUID(uuid);
		
		TMemberDTO memberDTO = modelMapper.map(vo, TMemberDTO.class);
		return memberDTO;
	}
}
