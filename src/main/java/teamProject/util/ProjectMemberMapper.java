package teamProject.util;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.member.domain.TMemberVO;

@Mapper
public interface ProjectMemberMapper {
	
//	@Select("select now()")  => mariaDB sql��
	
	
	@Select("select * from t_member where id = #{id} and pwd = #{pwd}")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public TMemberVO login(String id, String pwd);
	

	
	
	
	@Select("select sysdate from dual")
	public String getTime();
	
//	
//	
//	@Select("select * from t_member")
//	public List<TMemberVO> selectAll();
//	
//	// java객체의 속성과 매개변수#{}부분의 필드명이 일치해야 값이 전달됨
//	@Insert("insert into t_member (id, pwd, name, email) values (#{id}, #{pwd}, #{name}, #{email})")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	public int addMember(TMemberVO vo);
//	
//	
//	@Select("select * from t_member where id = #{id}")
//	public TMemberVO memberSelectOne(String id);
//	
//	
//	
//	
//	// jdbk 17이상
//	String update_sql = """
//			update t_member set pwd= #{pwd}, name= #{name}, email= #{email} where id = #{id}
//			""";
//	@Update(update_sql)
//	public int memberUpdate(TMemberVO vo);
//	
//	
//	
//	
//	@Delete("delete from t_member where id=#{id}")
//	public int memberDelete(@Param("id") String id);
//
//
//	// 중복ID체크
//	String checkID = """
//			select decode (count(*),1,'true','false') as isCheck from t_member where id = #{id}
//			""";
//	@Select(checkID)
//	public String checkID(@Param("id") String id);
//	
//	
	
	
	
}


/*
마이바티스 프레임워크 특징
SQL 실행결과를 자바 빈즈 또는 Map 객체에 매핑해주는 persistence솔루션으로 관리
SQL을 XML(Mapper 인터페이스 자바코드)로 분리 SQL문과 프로그래밍 코드를 분리해서 구현
데이터베이스 처리기능 제공 
 */