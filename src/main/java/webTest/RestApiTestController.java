package webTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/rest_api")
public class RestApiTestController extends HttpServlet {
	
	private JSONParser jsonParser;
	private JSONArray jsonArray;
	private JSONObject jsonObj;
	
	public RestApiTestController() {
		jsonParser = new JSONParser();
		jsonArray = new JSONArray();
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doDelete():delete");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doGet():select");
		
		resp.sendRedirect(req.getContextPath()+"/test/RESTFul_API.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPost():create");
		
		String jsonInfo = req.getParameter("jsonInfo");
		String text = req.getParameter("text");
		
		log.info("jsonInfo=>"+jsonInfo);
		log.info("text=>"+text);
		
		
		System.out.println("jsonInfo => " + jsonInfo);
		System.out.println("text => " + text);
		
		
		
		// json구조 문자열 -> json객체변환 -> data가능
//		JSONParser jsonParser = new JSONParser();
		try {
			jsonObj = (JSONObject) jsonParser.parse(jsonInfo);
			
			log.info("== json data 인식하기");
			log.info("아이디:"+jsonObj.get("id"));
			log.info("이름:"+jsonObj.get("name"));
			
			System.out.println("== json data 인식하기");
			System.out.println("아이디: " + jsonObj.get("id"));
			System.out.println("이름: " + jsonObj.get("name"));
			
			
		} catch (ParseException e) {e.printStackTrace();}
		
		// 클라이언에게 응답
		//resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		
		PrintWriter writer = resp.getWriter();
		resp.setStatus(HttpServletResponse.SC_OK);
		writer.print("정상처리되었습니다.");
		
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPut():update");
		// list회원정보 클라이언트에게 보내기
		
		JSONObject memberInfo = new JSONObject();
		memberInfo.put("id", "hong100");
		memberInfo.put("name", "홍길동");
		
		jsonArray.add(memberInfo);
		
		JSONObject memberInfo2 = new JSONObject();
		memberInfo2.put("id", "hong100");
		memberInfo2.put("name", "홍길동");
		
		jsonArray.add(memberInfo2);
		
		JSONObject jsonMember = new JSONObject();
		jsonMember.put("member", jsonArray);
		
		//json객체를 -> 문자열로 전환
		String jsonInfo = jsonMember.toJSONString();
		
		// 클라이언에게 응답
		//resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		
		PrintWriter writer = resp.getWriter();
		resp.setStatus(HttpServletResponse.SC_OK);
		writer.print(jsonInfo);

		
	}

}
