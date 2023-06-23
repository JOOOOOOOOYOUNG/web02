package com.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.member.domain.TMemberVO;

@Mapper
public interface MemberMapper {
	
//	@Select("select now()")  => mariaDB sql��
	
	
	
	//============ TeamProject ============ 파라미터 두개 이상은 @param 쓰기 //
	
	
	
	// 로그인
	@Select("select * from t_member where id = #{id} and pwd = #{pwd}")
	public TMemberVO login(@Param("id") String id, @Param("pwd") String pwd);
	
	
	
	// 자동로그인 체크했을 경우
	@Update("UPDATE t_member set uuid = #{uuid} where id = #{id}")
	public TMemberVO updateUUID(@Param("uuid") String uuid, @Param("id") String id);
	
	
	
	// 쿠키값으로 db정보 추출하는 메서드
	@Select("SELECT * FROM t_member where uuid = #{uuid}")
	public TMemberVO selectUUID(@Param("uuid") String uuid);
	
	
	
	//============ TeamProject ============ //
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Select("select sysdate from dual")
	public String getTime();
	
	
	@Select("select * from t_member")
	public List<TMemberVO> selectAll();
	
	// java객체의 속성과 매개변수#{}부분의 필드명이 일치해야 값이 전달됨
	@Insert("insert into t_member (id, pwd, name, email) values (#{id}, #{pwd}, #{name}, #{email})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addMember(TMemberVO vo);
	
	
	@Select("select * from t_member where id = #{id}")
	public TMemberVO memberSelectOne(String id);
	
	
	
	
	// jdbk 17이상
	String update_sql = """
			update t_member set pwd= #{pwd}, name= #{name}, email= #{email} where id = #{id}
			""";
	@Update(update_sql)
	public int memberUpdate(TMemberVO vo);
	
	
	
	
	@Delete("delete from t_member where id=#{id}")
	public int memberDelete(@Param("id") String id);


	// 중복ID체크
	String checkID = """
			select decode (count(*),1,'true','false') as isCheck from t_member where id = #{id}
			""";
	@Select(checkID)
	public String checkID(@Param("id") String id);
	
	
	
	

	
	// 동적쿼리 추가
//	@Select(MemberSQL.LIST_MEMBERS)
//	public List<TMemberVO> selectAll_sql();
	
	
	
	
	// java객체의 속성과 매개변수#{}부분의 필드명이 일치해야 값이 전달됨
//	@InsertProvider(type=MemberSqlDivMapper.class, method="insertMember")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	public int addMember_sql(TMemberVO vo);
//	


	
	
	
	
	
	// 동적쿼리 추가
//	@Select(MemberSQL.FIND_MEMBERS)
//	public List<TMemberVO> findMemberAll();
	
	
//	@SelectProvider(type=MemberSQL.class, method="findMembersName")
//	public List<TMemberVO> findMembersByName(@Param("name") String name);
	
	
//	@SelectProvider(type=MemberSQL.class, method="findMembersName")
//	public List<TMemberVO> findMembersByName2(@Param("name") String name);
	
}


/*
마이바티스 프레임워크 특징
SQL 실행결과를 자바 빈즈 또는 Map 객체에 매핑해주는 persistence솔루션으로 관리
SQL을 XML(Mapper 인터페이스 자바코드)로 분리 SQL문과 프로그래밍 코드를 분리해서 구현
데이터베이스 처리기능 제공 
 */