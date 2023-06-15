package com.member.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.member.domain.TMemberVO;

import connectionDB.ConnectionOracleUtil;
import lombok.extern.log4j.Log4j2;
import util.MapperUtil;

@Log4j2
public class OracleDAOTest {


	private ModelMapper modelMappr;
	private MemberMapper memberMapper;
	private MemberSqlDivMapper memberSqlDivMapper;
	
	SqlSessionFactory factory;
	SqlSession session;
	
	@BeforeEach
	public void read() {
		//dao = new BoardDAO();
		modelMappr = MapperUtil.INSTANCE.get();
		factory = ConnectionOracleUtil.INSTANCE.getSqlSessionFactory();
		
		try {
			
			session = factory.openSession();
			memberMapper = session.getMapper(MemberMapper.class);	// java 어노테이션으로 연결한 query문
			memberSqlDivMapper = session.getMapper(MemberSqlDivMapper.class);
			
		} catch (Exception e) {}
		
		
	}
	
	@After
	public void close() {
		session.close();
	}
	
	@Test
	public void testGetTime() {
		
		// HikariCP적용 시 java source로 sqlSessionFactory객체 정의
		try {
			//SqlSessionFactory factor = ConnectionOracleUtil.INSTANCE.getSqlSessionFactory();
			//session = factory.openSession();
			//memberMapper = session.getMapper(MemberSqlMapper.class);
			
			String getTime = memberMapper.getTime();
			
			log.info("=== getTime(): mybatis: " + getTime);
			System.out.println("=== getTime(): mybatis: " + getTime);
			
		} catch (Exception e) {}
	}
	
	
	@Test
	public void testMemberSelectAll() {
		
		// HikariCP적용 시 java source로 sqlSessionFactory객체 정의
		try {
			//SqlSessionFactory factor = ConnectionOracleUtil.INSTANCE.getSqlSessionFactory();
			session = factory.openSession();
			
			// java 어노테이션으로 연결한 query문
			MemberMapper memberMapper = session.getMapper(MemberMapper.class);
			
			List<TMemberVO> memberVO = memberMapper.selectAll();
			
			
			System.out.println("\n=== mybatis mapper memberVO: " + memberVO);
			
		} catch (Exception e) {}
	}	
		
	
	@Test
	public void testMemberRegister() {
		try {
			
			TMemberVO memberVO = TMemberVO.builder()
				.id("hong2")
				.pwd("1111")
				.name("홍길동2")
				.email("hong2@gmail.com")
				.build();
		
		System.out.println(memberVO);
		
		// myBatis에서 제공하는 Sqlsession활용 테스트
		int insertResult = memberMapper.addMember(memberVO);
		session.commit();
		System.out.println("insert => " + insertResult);
			
		} catch (Exception e) {
		}
		
	}
	
	@Test
	public void testUpdate() {
		TMemberVO memberVO = TMemberVO.builder()
				.id("hong2")
				.pwd("2222")
				.name("홍길동2")
				.email("hong222@gmail.com")
				.build();
		
		int updateOK = memberMapper.memberUpdate(memberVO);
		session.commit();
		
		System.out.println("update => " + updateOK);
		
	}
	
	
	// 회원삭제 테스트
	@Test
	public void testDelete() {
		int deleteOK = memberMapper.memberDelete("hong2");
		session.commit();
		
		System.out.println("delete => " + deleteOK);
	}
	
	

//	@Test
//	public void testFindMembersAll() {
//		List<TMemberVO> vo = memberMapper.findMemberAll();
//		session.commit();
//			
//		System.out.println("findMemberAll => " + vo);
//	}
	
	
	
	
	// 분리된 SQL Mapper 테스트
	
	@Test
	public void testFindMembersAll() {
		List<TMemberVO> vo = memberSqlDivMapper.selectAll();
		session.commit();
			
		System.out.println("selectAll => " + vo);
	}
	
	
	
	
	
	@Test
	public void testfindMembersByName() {
		List<TMemberVO> vo = memberMapper.findMembersByName("동순");
		session.commit();
		
		log.info("mybatis mapper vo : " + vo);
		System.out.println("findMembersByName => " + vo);
		
		//vo.stream().forEach((System.out::println);
	}
	
	
	

	
	/*
	
	@Test
	public void testMemberList() throws Exception {
		List<BoardVO> list = dao.listBoard();
		
		//System.out.println("==== vo list");
		
		//list.forEach(vo -> {log.info(vo);
		//System.out.println(vo);
	 
		
	// vo->dto �뀒�뒪�듃
	List<BoardDTO> dtoList =list.stream()
						.map(vo -> modelMappr.map(vo, BoardDTO.class))
						.collect(Collectors.toList());
	
		System.out.println("==== dto Test" + dtoList);
		//dtoList.forEach(dto->System.out.println(dto));
	
	}
	
	
	// 寃뚯떆�뙋 �벑濡� �뀒�뒪�듃
	@Test
	public void insertNewArticle() throws Exception{
		// �깉湲� �벑濡앺븯湲� �쐞�빐 �깉湲�踰덊샇 �깮�꽦
		int newArticleNO = dao.getNewArticleNO();
		System.out.println("---NEW NO: " + newArticleNO);
		
		// �깉湲� �벑濡�
		BoardVO vo = BoardVO.builder()
					.articleNO(newArticleNO)
					.parentNO(0)
					.title("寃뚯떆湲� �궡�슜 test0")
					.content("寃뚯떆湲� �궡�슜 test0")
					.imageFileName("imagefile0.png")
					.id("dong")
					.build();
		
		dao.insertNewArticle(vo);
		
	}
	
	@Test
	public void selectArticleOne() {
		int articleNO = 7;
		
		BoardVO vo = dao.selectArticleOne(articleNO);
		System.out.println("==== vo: "+vo);
		
		BoardDTO dto = modelMappr.map(vo, BoardDTO.class);
		System.out.println("==== dto: "+dto);
	}
	
	
	
	
	@Test
	public void testAddMember() throws Exception {
		// vo -> db �쟾�떖
		TMemberVO vo = TMemberVO.builder()
				.id("test1")
				.pwd("1234")
				.name("�솉湲몃룞�뀒�뒪�듃留�")
				.email("test1@google.com")
				.build();
		
		System.out.println(vo);
		
		dao.addMember(vo);
		
	}
	
	
	@Test
	public void testFindMember() throws Exception {
		
		String id = "dong";
		TMemberVO vo = dao.findMember(id);
		
		log.info("findMember: "+vo);
		System.out.println("findMember: "+vo);
		
	}
	
	
	@Test
	public void textModifyMember() throws Exception {
		TMemberVO vo = TMemberVO.builder()
					.id("dong2")
					.pwd("2222")
					.name("DONGSUN2")
					.email("ds@naver.com")
					.build();
		
		int result = dao.modifyMember(vo);
		
		if (result != 0)
			System.out.println("modifyMember result: "+result+" �닔�젙�셿猷�!");
		else 
			System.out.println("�닔�젙�떎�뙣");	
	}
	
	@Test
	public void testDeleteMember() throws Exception {
		
		String id = "dong2";
		int result = dao.deleteMember(id);
		
		if (result == 0)
			log.info("�궘�젣�떎�뙣");
			
		else
			log.info("�궘�젣�꽦怨�!");
			System.out.println("�궘�젣�꽦怨�!");
		
	}
	*/
	
}
