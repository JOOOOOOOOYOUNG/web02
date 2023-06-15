package com.member.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.apache.tomcat.util.buf.StringUtils;

public class MemberSQL {
	
	// 회원 목록
	public static final String LIST_MEMBERS = 
			"""
			select * from t_member
			""";
	
	// 회원 등록
	public String insertMember() {
	  String sql = new SQL()
	    .INSERT_INTO("t_member")
	    .VALUES("id, pwd, name", "#{id}, #{pwd}, #{name}")
	    .VALUES("email", "#{email}")
	    .toString();
	  return sql;
	}
	
	
	
	// 조건 검색
	public String findMembersName() {
		
		StringBuilder query = new StringBuilder();
		
		query.append("select * from t_member ");
		
		
//		if(name != null && name.length() > 0) {
//			query.append(" where ");
//			query.append("name like "+name);
//		}
//		
//		return query.toString();
		
		return new SQL() {{
		SELECT("*");
		FROM("t_member");
		WHERE("name like #{name} || '%'");
		ORDER_BY("name desc");
		}}.toString();
		
		
		
	}
}