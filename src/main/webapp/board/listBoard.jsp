<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@page import="com.board.dto.BoardDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />
<c:set var="loginCheck" value="<%=session.getAttribute(\"loginInfo\") %>" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

</head>
<body>

<!-- ${loginCheck}님 접속중 -->
<!-- ${totArticles} ${section} ${pageNum} -->
<!-- ${list} -->


<!-- 메뉴 네비게이션 바 -->
<jsp:include page="../includes/navDiv.jsp" />



    <div class="main">
        <div class="container">
            <div class="w-75 m-auto" >
            	<div class="row">
                 <div>
              	  <h4 class="text-center">답변형 게시판</h4>
                 </div>
                 </div>
                 <hr>
                </div>
                
            <div class="w-75 m-auto" >
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">글번호</th>
                        <th scope="col">작성자</th>
                        <th scope="col">제목</th>
                        <th scope="col">작성일</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:choose>
	                    <c:when test="${list == null || list.size() == 0 }">
	                    	<tr>
		                    	<td colspan="4">
			                    	<div class="alert alert-light" role="alert">
									  등록된 자료가 없습니다.
									</div>
		                    	</td>
	                    	</tr>
	                    </c:when>
	                    <c:when test="${list != null }">
		                    <c:forEach var="article" items="${list}" varStatus="articleNum">
		                      <tr>
		                        <th scope="row">${articleNum.count}/${article.articleNO} </th>
		                        <td>
		                        	<a href="${ctxPath}/boardlist/viewArticle.do?articleNO=${article.articleNO}&pageNum=${pageNum}&pageBlock=${section}"  
		                        	   class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
		                        	   ${article.id}</a>
		                        </td>
		                        <td>
		                        	<c:choose>
		                        		<c:when test="${article.level > 1 }">
		                        		<c:forEach begin="1" end="${article.level}" step="1">
		                        			<span style="padding-left:10px"></span>
		                        		</c:forEach>
		                        		<span>
		                        			<svg xmlns="http://www.w3.org/2000/svg" 
	                        					width="16" height="16" fill="currentColor" 
	                        					class="bi bi-arrow-return-right" 
	                        					viewBox="0 0 16 16">
		  										<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
											</svg>
										</span>
		                        		</c:when>
		                        	</c:choose>
		                        	
		                        	${article.title}
		                        </td>
		                        <td>${article.writeDate}</td>
		                      </tr>
		                     </c:forEach>
	                    </c:when>
                    </c:choose>
                    </tbody>
                  </table>

            </div>

             <div class="w-75 m-auto d-flex justify-content-between" ><%-- ${lastPage } --%>
            
            	<c:if test="${totArticles != null }">
                <nav aria-label="...">
                
                    <ul class="pagination">
                       	<c:forEach var="page" begin="1" end="10" step="1" >
		                     <!--  section 2이상이고 1page이면 이전 버튼 활성화 -->	
	                       	 <c:if test="${section > 1 && page==1 }">
		                      	<li class="page-item ">
		                        	<a class="page-link" href="${ctxPath}/boardlist/list.do?pageBlock=${section-1}&pageNum=1">이전</a>
		                     	</li>
		                     </c:if>  
		                     
		                     
		                     <!--  현재 보여지고 있는 페이지는 링크 안됨 -->
		                     <c:choose>
	   	                    	<c:when test="${page==pageNum}">
	   	                    	 <li class="page-item  disabled" aria-current="page">
			                      	<a class="page-link" href="${ctxPath}/boardlist/list.do?pageBlock=${section}&pageNum=${page}">${(section-1)*10 + page}</a>
			                     </li>
	   	                    	</c:when>
	   	                    	<c:otherwise>
	   	                    	 <li class="page-item ">
			                      	<a class="page-link" href="${ctxPath}/boardlist/list.do?pageBlock=${section}&pageNum=${page}">${(section-1)*10 + page}</a>
			                     </li>
	   	                    	</c:otherwise>
   	                    	 </c:choose>
   	                    	

                      	</c:forEach>
	                      	
                        <!--  다음 버튼 : 현재 section번호가 전체 section 번호 보다 작은 경우만 표시-->
                      	<c:if test="${section < totSection}">
	                      <li class="page-item">
	                        <a class="page-link" href="${ctxPath}/boardlist/list.do?pageBlock=${section+1}&pageNum=1">다음</a>
	                      </li>
                      	</c:if>
                    </ul>
                </nav>
                </c:if>
                
                
                <div>
                    <button type="button" onClick="reply()"
	                    	class="btn btn-outline-primary btn-sm">
	                    	글쓰기</button>
                </div>
            </div>
        </div>
    </div>
  
  
  
  <!-- 스크립트 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
  <script type="text/javascript">
  	function register(){
  		location.href="${ctxPath}/boardlist/register.do"
//  		location.href="${ctxPath}/remember/register.do"
  	}
  
  	
  	
  </script>
  
  
  
</body>
</html>