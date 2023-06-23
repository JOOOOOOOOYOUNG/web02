<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="ctxPath" value="<%=request.getContextPath() %>" /> 
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
</head>
<body>

	<div class="m-3">

<h1> 회원정보 </h1>

      	<div class="mb-3 row">
          <div class="col-md-2 border-0 rounded-1 m-1 p-1">아이디</div>
          <div class="col-md-6 border rounded-1 m-1 p-1">${member.id}</div>
        </div>
      
        <div class="mb-3 row">
          <div class="col-md-2 border-0 rounded-1 m-1 p-1">비밀번호</div>
          <div class="col-md-6 border rounded-1 m-1 p-1">
          <input type="password" value="${member.pwd}" class="form-control border-0" readonly="readonly">
          </div>
        </div>
         
        <div class="mb-3 row">
          <div class="col-md-2 border-0 rounded-1 m-1 p-1">이름</div>
          <div class="col-md-6 border rounded-1 m-1 p-1">${member.name}</div>
        </div>

        <div class="mb-3 row">
          <div class="col-md-2 border-0 rounded-1 m-1 p-1">이메일</div>
          <div class="col-md-6 border rounded-1 m-1 p-1">${member.email}</div>
        </div>
 
	</div>

      
  
  	<script type="text/javascript">
		
	</script>

</body>
</html>