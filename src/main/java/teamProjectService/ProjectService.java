package teamProjectService;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.modelmapper.ModelMapper;

import com.member.domain.TMemberVO;
import com.member.dto.TMemberDTO;
import com.member.mapper.MemberMapper;

import connectionDB.ConnectionOracleUtil;
import teamProject.util.ProjectMemberMapper;
import util.MapperUtil;

public enum ProjectService {
	
	INSTANCE;
	
	private ModelMapper modelMapper;
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	private MemberMapper memberMapper;
	
//	private ProjectMemberMapper Pmapper;	안됨
	
	// 생성자
	private ProjectService() {
	try {
		
		modelMapper = MapperUtil.INSTANCE.get();
		sqlSessionFactory = ConnectionOracleUtil.INSTANCE.getSqlSessionFactory();
		session = sqlSessionFactory.openSession();
		
		
	} catch (Exception e) {e.printStackTrace();}
		
	}
	
	
	// 로그인 서비스
	public TMemberDTO loginMember(String id, String pwd) {
		
		memberMapper = session.getMapper(MemberMapper.class);
		
		TMemberVO vo = memberMapper.login(id, pwd);
		TMemberDTO dto = modelMapper.map(vo, TMemberDTO.class);
		
		
		return dto;
	}
	
	
	
	// 자동로그인 체크시 uuid에 임의 문자열을 저장(수정)
	public void updateUUID(String id, String uuid) {
		
		memberMapper = session.getMapper(MemberMapper.class);
		
		TMemberVO vo = memberMapper.updateUUID(id, uuid);
		TMemberDTO dto = modelMapper.map(vo, TMemberDTO.class);
		
		
	}
	
	// 자동로그인 상태일 경우 쿠키값을 읽어 db정보를 추출하는 메서드
	public TMemberDTO getByUUID(String uuid) {
		
		memberMapper = session.getMapper(MemberMapper.class);
		
		TMemberVO vo = memberMapper.selectUUID(uuid);
		TMemberDTO dto = modelMapper.map(vo, TMemberDTO.class);
		
		return dto;
		
	}
	

}
