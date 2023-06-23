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
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
</head>
<body>


  <div class="main">
    <div class="container">
      <div class="w-75 justify-content-center">
     <h2 class="text-center">회원정보 조회</h2>
      </div>

      <div class="w-75">
      <div class="viewMember border m-3 p-4">
      <form>
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

        <div class="mb-3 row">
          <div class="col-md-2 border-0 rounded-1 m-1 p-1">가입일자</div>
          <div class="col-md-6 border rounded-1 m-1 p-1">${member.joinDate}</div>
        </div>

        <div class="row d-flex">
        <div class="col-md-6">
          <a href="${ctxPath}/modify.do?id=${member.id}" class="btn btn-outline-info btn-sm m-1" id="modForm">수정</a> 
          <a href="${ctxPath}/delete.do?id=${member.id}" class="btn btn-outline-info btn-sm m-1" id="delBtn">삭제</a>
          <input type="hidden" value="${member.id}" id="memberId">
          <input type="hidden" value="${ctxPath}" id="path">
        </div>
        <div class="col-md-2">
          <a href="${ctxPath}/list.do" class="btn btn-outline-primary btn-sm m-1">목록</a>
        </div>
        </div>

      </form>
      </div>
    </div>
    </div>
  </div>
  
  
  	<script type="text/javascript">
  	$(function(){
		console.log('jQuery ok...');
		
			$('#delBtn').click(function(e){
				e.preventDefault();	//submit() 기능 중지
				
				var isDelete = confirm('삭제하시겠습니까?')
				console.log("isDelete = " + isDelete);
				
				
				if (isDelete){
					location.href=path+"/delete.do?id="+id;
				}
				
				
			});
			
		});
	</script>

</body>
</html>