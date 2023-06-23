package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.board.dto.BoardDTO;
import com.board.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/boardlist/*")
public class BoardController extends HttpServlet {
	
		// 글에 첨부한 이미지 저장위치
		private static String ARTICLE_IMAGE_REPO = "d:\\file_repo";
	
	
		// 서비스 객체를 생성(dao, modelMapper)
		private BoardService boardService = BoardService.INSTANCE;
		
		
		// session에 부모글 보관
		HttpSession session;
		
		
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doHandler(req, resp);
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doHandler(req, resp);
		}
		protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			// 공통된 경로를 제외한 url을 작업구분자로 적용
			String action = req.getPathInfo();
			String nextPage = null;
			
			String _section = req.getParameter("pageBlock");
			String _pageNum = req.getParameter("pageNum");
			
			
			System.out.println("section: "+_section);
			System.out.println("page Number: "+_pageNum);
			
			
			int section = Integer.parseInt((_section == null) ? "1" : _section);
			int pageNum = Integer.parseInt((_pageNum == null) ? "1" : _pageNum);
			
			
			Map<String, Integer> pageingMap = new HashMap<>();
			
			pageingMap.put("section", section);
			pageingMap.put("pageNum", pageNum);
			
				
			
			if (action == null || action.equals("/list.do")) {
			
				
				
			List<BoardDTO> boardList = boardService.boardList(pageingMap);		
				
			// 게시글 총 개수
			int totArticles = boardService.selectTotArticles();
			
			
			// 총페이지 블럭계산(1블록: 10개 페이지)
			// 총블럭 = 총페이수/10(블럭묶음단위) + 1
			// 991페이지/10페이지 = 99.1블럭 => (99.1)+1 => 100.1 => 100(정수만 처리)
			
			// 전체 페이지수 = 전체레코드수/10(1page: 10개묶음) + 1
			int totalPage = (int)Math.ceil(totArticles*1.0/10); // 소수점이하기 있으면 자리올림(10.1=>11)
			int totalPageBlock =(int)Math.ceil(totalPage*1.0/10);
			
			
			System.out.println("totArticles: " + totArticles);
			System.out.println("totalPage: " + totalPage);
			System.out.println("totalPageBlock: " + totalPageBlock);
			
			
			// 마지막 페이지 계산 : 현재페이지 카운터에서 마지막페이지 초과하지않게 설정
			// 페이지계산은 1-10형식으로 반복처리됨.
			int lastPage = 1;
			for (int i=1; i<=10; i++) {
				// 블럭에 대한 페이지번호 생성
				int endPage = (section-1)*10 + i;
				
				System.out.println("("+section+"-1)*10) + "+i+": "+(endPage));
				
				
				if (pageNum == i) {
					System.out.println("현재페이지와 동일 번호: "+i);
					
				}
				
				if (endPage <= totalPage) {
					lastPage = i;
				}
				
			}
			
			System.out.println("section: " + section);
			System.out.println("lastPage: " + lastPage);
			
			
			req.setAttribute("list", boardList);
			
			req.setAttribute("totArticles", totArticles);
			req.setAttribute("section", section);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("totSection", totalPageBlock);
			// 페이지 블럭에서 마지막페이지 값 보관
			req.setAttribute("lastPage", lastPage);
			
		
			
			nextPage ="/board/listBoard.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);	
			
			
			} else if (action.equals("/viewArticle.do")){
				int articleNO = Integer.parseInt(req.getParameter("articleNO"));
				// 서비스 요청
				BoardDTO article = boardService.selectArticleOne(articleNO);
				req.setAttribute("dto", article);
				
				nextPage = "/board/viewBoard.jsp";
				req.getRequestDispatcher(nextPage).forward(req, resp);
				
			
			
			} else if (action.equals("/register.do")){	// 입력 폼 요청
//				int articleNO = Integer.parseInt(req.getParameter("articleNO"));
				// 서비스 요청
				try {
					
				} catch (Exception e) {
				}
				
				nextPage = "/board/boardForm.jsp";
				resp.sendRedirect(req.getContextPath()+nextPage);
				
				
			} else if (action.equals("/insert.do")){
				
				BoardDTO dto = new BoardDTO();
				
//				dto.setArticleNO(Integer.parseInt(req.getParameter("articleNO")));
//				dto.setId(req.getParameter("id"));
//				dto.setTitle(req.getParameter("title"));
//				dto.setContent(req.getParameter("content"));
//				dto.setImageFileName(req.getParameter("imageFileName"));
				
				// 업로드기능 호출
				Map<String, String> articleMap = upload(req, resp);	// 업로드기능 호출
			
				dto.setId(articleMap.get("id"));
				dto.setTitle(articleMap.get("title"));
				dto.setContent(articleMap.get("content"));
				dto.setImageFileName(articleMap.get("imageFileName"));
				
				
//				System.out.println("=== articleNO: "+ articleNO);
				System.out.println(dto);
				
		
				// db에 게시글 등록 서비스 요청
				try {
					int isOK = boardService.insertArticleNO(dto);
					
					// 첨부파일 경우만 처리
					if (dto.getImageFileName() != null && dto.getImageFileName().length() != 0) {
						
						// temp폴더에 임시로 업로드 (file_refo 안에 temp 폴더 생성해야함)
						File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + dto.getImageFileName());
						
						// ARTICLE_IMAGE_REPO 하위경로에 글 번호가 이름인 폴더를 생성: "d:\\file_refo\글번호폴더
						File descFile = new File(ARTICLE_IMAGE_REPO + "\\" + dto.getArticleNO());
						descFile.mkdirs();
						
						// temp폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동
						FileUtils.moveFileToDirectory(srcFile, descFile, true);
						
						
						
					}
					
					// 클라이언트에게 메세지 전송 응답
					req.setCharacterEncoding("utf-8");
					resp.setContentType("text/html; charset=utf-8");
					
					PrintWriter pw = resp.getWriter();
					

					
					if (isOK != 0) {
						pw.print("<script>" + 
									"alert('새글 등록 완료.');" + "location.href='" + req.getContextPath() + "/boardlist/list.do';" +
								"</script>"
								);
						
					} else {
						pw.print("<script>" + 
									"alert('글을 등록할 수 없습니다.');" + "location.href='" + req.getContextPath() + "/boardlist/list.do';" +
								"</script>"
								
								);
					}
					
					
				} catch (Exception e) {}
				
				return;
				
				
//				nextPage = "/boardlist/list.do";
//				resp.sendRedirect(req.getContextPath()+nextPage);
				
			
			} else if (action.equals("/modify.do")){	// 수정 폼
				
				BoardDTO dto = new BoardDTO();
				
				dto.setId(req.getParameter("id"));
				dto.setArticleNO(Integer.parseInt(req.getParameter("articleNO")));
				dto.setTitle(req.getParameter("title"));
				dto.setContent(req.getParameter("content"));
				dto.setImageFileName(req.getParameter("imageFileName"));
				// "문자형식의 날짜" => LocalDate타입의 날짜형으로 전환
				dto.setWriteDate(LocalDate.parse(req.getParameter("writeDate")));
		
				
				// 수정할 데이터 저장
				req.setAttribute("dto", dto);

				
				// 수정 페이지 요청
				nextPage = "/board/modifyForm.jsp";
				req.getRequestDispatcher(nextPage).forward(req, resp);

					
				
			} else if (action.equals("/update.do")){	// db 수정
				
				req.setCharacterEncoding("utf-8");
				resp.setContentType("text/html; charset=utf-8");
				
				BoardDTO dto = new BoardDTO();
				
//				dto.setArticleNO(Integer.parseInt(req.getParameter("articleNO")));
//				dto.setId(req.getParameter("id"));
//				dto.setTitle(req.getParameter("title"));
//				dto.setContent(req.getParameter("content"));
//				dto.setImageFileName(req.getParameter("imageFileName"));
				
				
				// 업로드 기능이 있을 경우 처리
				Map<String, String> articleMap = upload(req, resp);	// 업로드기능 호출
				
				
				// 게시글 번호
				// form => enctype="multipart/form-data" => request 작동안됨
//				int articleNO = Integer.parseInt(req.getParameter("articleNO"));
				
				dto.setArticleNO(Integer.parseInt(articleMap.get("articleNO")));
				dto.setId(articleMap.get("id"));
				dto.setTitle(articleMap.get("title"));
				dto.setContent(articleMap.get("content"));
				dto.setImageFileName(articleMap.get("imageFileName"));
				
				System.out.println("dto");
		
				// db 수정 서비스 요청
				try {
					
					int isOK = boardService.updateArticleNO(dto);
					
					// 첨부파일 경우만 처리
					if (dto.getImageFileName() != null && dto.getImageFileName().length() != 0) {
						
						// temp폴더에 임시로 업로드 (file_refo 안에 temp 폴더 생성해야함)
						File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + dto.getImageFileName());
						
						// ARTICLE_IMAGE_REPO 하위경로에 글 번호가 이름인 폴더를 생성: "d:\\file_refo\글번호폴더
						File descFile = new File(ARTICLE_IMAGE_REPO + "\\" + dto.getArticleNO());
						descFile.mkdirs();
						
						// temp폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동
						FileUtils.moveFileToDirectory(srcFile, descFile, true);
						
					    //-----------------//
						// 수정전 이미지 삭제  //
						//----------------//	request객체의 정보는 file upload 라이브러리를 통해 데이터를 추출해야함
						String originalFileName = articleMap.get("originalFileName");
						File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + dto.getArticleNO() + "\\" + originalFileName);
						oldFile.delete();
			
						
					}
					
					// 클라이언트에게 메세지 전송 응답
					req.setCharacterEncoding("utf-8");
					resp.setContentType("text/html; charset=utf-8");
					
					PrintWriter pw = resp.getWriter();
					

					
					if (isOK != 0) {
						pw.print("<script>" + 
									"alert('게시글 수정 완료.');" + "location.href='" + req.getContextPath() + "/boardlist/list.do';" +
								"</script>"
								);
						
					} else {
						pw.print("<script>" + 
									"alert('수정 실패');" + "location.href='" + req.getContextPath() + "/boardlist/list.do';" +
								"</script>"
								
								);
					
}
					
					
				} catch (Exception e) {}
				
				//return;
				//nextPage = "/boardlist/list.do";
				//resp.sendRedirect(req.getContextPath()+nextPage);
					
					
					
				
				
			} else if (action.equals("/delete.do")){
				
				int articleNO = Integer.parseInt(req.getParameter("articleNO"));
				
				System.out.println("articleNO: "+articleNO);
				
				int isOK = 0;
				int isOK2 = 0;
					
				List<Integer> articleNOList = null;
				
				// db 삭제 서비스 요청
				try {
					articleNOList = boardService.deleteArticleNO(articleNO);
					isOK = 1;
					
				} catch (Exception e) {}
				
				
				// 이미지폴더 및 파일 삭제
				for (int _articleNO: articleNOList) {
				
				File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
				
				if (imgDir.exists()) { // 폴더가 존재하면 폴더삭제
					FileUtils.deleteDirectory(imgDir);
				}
				
			}	
				
				// 삭제 성공 메세지 클라이언트에게 보내기
				req.setCharacterEncoding("utf-8");
				resp.setContentType("text/html; charset=utf-8");
				
				PrintWriter pw = resp.getWriter();
//				
//				if (isOK != 0) {
//					pw.print("<script>" + 
//								"alert('삭제 완료 current articleNO remove success');" + "location.href='" + req.getContextPath() + "/boardlist/list.do';" +
//							"</script>"
//							);
//					
//				} else {
//					pw.print("<script>" + 
//								"alert('삭제 실패 current articleNO remove fail');" + "location.href='" + req.getContextPath() + "/boardlist/list.do';" +
//							"</script>"
//							
//							);
//					
//				}
				
//				return ;
				
				nextPage = "/boardlist/list.do";
				resp.sendRedirect(req.getContextPath()+nextPage);
			
				
				
			} else if (action.equals("/reply.do")){
				
				int parentNO = Integer.parseInt(req.getParameter("parentNO"));
				System.out.println("reply.do parentNO: "+ parentNO);
				
				
				req.setAttribute("parentNO", parentNO);
				
//				session = req.getSession();
//				session.setAttribute("parentNO", parentNO);
				
				
				
				nextPage = "/board/replyForm.jsp";
				req.getRequestDispatcher(nextPage).forward(req, resp);
				
				
			
				
			} else if (action.equals("/addReply.do")){	// DB에 답글쓰기
				
				BoardDTO dto = new BoardDTO();
				
				// 업로드 기능이 있을 경우 처리
				Map<String, String> articleMap = upload(req, resp);	// 업로드기능 호출
			
				dto.setId(articleMap.get("id"));
				dto.setParentNO(Integer.parseInt(articleMap.get("parentNO")));
				dto.setTitle(articleMap.get("title"));
				dto.setContent(articleMap.get("content"));
				dto.setImageFileName(articleMap.get("imageFileName"));
				

				System.out.println(dto);
				
		
				// db에 게시글 등록 서비스 요청
				try {
					int isOK = boardService.insertArticleNO(dto);
					
					// 첨부파일 경우만 처리
					if (dto.getImageFileName() != null && dto.getImageFileName().length() != 0) {
						
						// temp폴더에 임시로 업로드 (file_refo 안에 temp 폴더 생성해야함)
						File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + dto.getImageFileName());
						
						// ARTICLE_IMAGE_REPO 하위경로에 글 번호가 이름인 폴더를 생성: "d:\\file_refo\글번호폴더
						File descFile = new File(ARTICLE_IMAGE_REPO + "\\" + dto.getArticleNO());
						descFile.mkdirs();
						
						// temp폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동
						FileUtils.moveFileToDirectory(srcFile, descFile, true);
						
						
						
					}
					
					// 클라이언트에게 메세지 전송 응답
					req.setCharacterEncoding("utf-8");
					resp.setContentType("text/html; charset=utf-8");
					
					PrintWriter pw = resp.getWriter();
					

					
					if (isOK != 0) {
						pw.print("<script>" + 
									"alert('답글 등록 완료.');" + "location.href='" + req.getContextPath() + "/boardlist/list.do';" +
								"</script>"
								);
						
					} else {
						pw.print("<script>" + 
									"alert('답글을 등록할 수 없습니다.');" + "location.href='" + req.getContextPath() + "/boardlist/list.do';" +
								"</script>"
								
								);
					}
					
					
				} catch (Exception e) {}
				
				return;
				
				
				
				
				
			} else {
				
				
				
			 
			 List<BoardDTO> boardList = boardService.boardList(pageingMap); // 게시글 총 개수
			 int totArticles = boardService.selectTotArticles();
				  
				  
			 req.setAttribute("list", boardList); req.setAttribute("totArticles",
			 totArticles); req.setAttribute("section", section);
			 req.setAttribute("pageNum", pageNum);
				 
			 nextPage ="/board/listBoard.jsp";
			 req.getRequestDispatcher(nextPage).forward(req, resp);
		
				
				
			nextPage = "/";
			resp.sendRedirect(req.getContextPath()+nextPage);
			
			
			}
				
			
			
			
			
			} //doHandler
		
		
		
		// 이미지 업로드 메서드 선언
		private Map<String, String> upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Map<String, String> articleMap = new HashMap<String, String>();
			String encoding = "utf-8";
			
			// 문자열 -> 시스템 파일로 변환
			File currentPath = new File(ARTICLE_IMAGE_REPO);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(currentPath);
			factory.setSizeThreshold(1024*1024);	// 1024byte = 1KB*1024 = 1MB
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				// request에 저장되어 있는 매개변수를 List에 저장
				List<FileItem> items = upload.parseRequest(req);
				for (int i=0; i<items.size(); i++) {
					
					FileItem fileItem = items.get(i);
					if (fileItem.isFormField()) {	// form요소이면 
						System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));
						
						// 게시글 등록 => title, content,...
						articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
						
					} else {
						// file객체이면 처리
						System.out.println("파라미터: "+fileItem.getFieldName());
						System.out.println("파일이름: "+fileItem.getName());
						System.out.println("파일크기: "+fileItem.getSize()+"bytes");
						
						articleMap.put(fileItem.getFieldName(), fileItem.getName());
						
						if (fileItem.getSize() > 0) {
							
							// 운영체제별 파일 경로 기호 추출: "/", "\\",..
							String separator = File.separator;
							
							// "d:\aaa\bbb\image.jpg"
							int idx = fileItem.getName().lastIndexOf("\\");
							if (idx == -1) {
								idx = fileItem.getName().lastIndexOf("/");
							}
							
							String fileName = fileItem.getName().substring(idx+1);
							// currentPath = "d:\\file_repo"
//							File uploadFile = new File(currentPath + separator + fileName);	// "d:\\file_repo" + "\\" + "test.jpg"
							File uploadFile = new File(currentPath + separator + "temp" + separator + fileName);

							
							// 파일시스템으로 전환된 파일경로 + 파일이름을 업로드 실행
							fileItem.write(uploadFile);
							
						} //if (file size)
					} //if (file 객체 구분)
					
				}
			} catch (Exception e) {}
			
			return articleMap;
		}
		
		
}
