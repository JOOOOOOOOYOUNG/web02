<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxpath" value="${pageContext.request.contextPath}" />   

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

<div class="main">
        <div class="container">
	        <div class="alert alert-secondary text-center mt-3" role="alert">
			  회원정보
			</div>
            <table class="table">
            <thead>
                <tr>
	                <th scope="col">#</th>
	                <th scope="col">ID</th>
	                <th scope="col">PWD</th>
	                <th scope="col">NAME</th>
	                <th scope="col">EMAIL</th>
	                <th scope="col">DATE</th>
                </tr>
            </thead>
            <tbody>
            
            </tbody>
            <c:choose>
            	<c:when test="${memberList.size()==0 || memberList==null}">
            		<tr>
            			<td colspan="6">등록된 회원이 없습니다.</td>
            		</tr>
            		
            	</c:when>
            	<c:when test="${memberList!=null}">
            		<c:forEach var="mem" items="${memberList}" varStatus="loop">
		                <tr>
		                	<th scope="row">${loop.index+1}</th>
			                <td>
			                <a href="${ctxpath}/reg/view.do?id=${mem.id}" class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">${mem.id}</a>
			                </td>
			                <td>${mem.pwd}</td>
			                <td>${mem.name}</td>
			                <td>${mem.email}</td>
			                <td>${mem.joinDate}</td>
		                </tr>
	            	</c:forEach>
            	</c:when>
            </c:choose>
            </table>
            <p><a href="${ctxpath}/member/memberForm.do" class="link-warning link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">회원가입하기</a></p>
        </div>
    </div>
</body>
</html>