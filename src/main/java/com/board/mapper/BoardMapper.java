package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.board.domain.BoardVO;



@Mapper
public interface BoardMapper {
	

	
	String sql = "select level, "
			+ "	    articleNO, "
			+ "	    parentNO, "
			+ "	    title, "
			+ "	    content, "
			+ "	    writeDate, "
			+ "	    id "
			+ "	from t_board "
			+ "	start with parentNO=0  "
			+ "	connect by prior articleNO = parentNO "
			+ "	order siblings by articleNO Desc ";
	
//	  @Select("select * from t_board")
	  @Select(sql)
	  public List<BoardVO> selectAll();
	  
	  
//	  @Select("SELECT * FROM comp_users WHERE username = #{username}")
//	  public CompUsers findByUsername(String username);
}
