<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="<%=request.getContextPath() %>"    /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>

</head>
<body>
<div class="main">
    <div class="container">
      <div class="w-75 m-auto">
        <h4 class="text-center">글 상세 내용</h4>
        <hr>
      </div>
      
      <div class="w-75 m-auto">
      <form action="${ctxPath}/boardlist/update.do" enctype="multipart/form-data" method="post">
        <div class="mb-3 row">
          <label for="no" class="col-sm-2 col-form-label">글번호</label>
          <div class="col-sm-10">
            <input type="text" name="articleNO" readonly class="form-control-plaintext" id="no" value="${dto.articleNO}">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="id" class="col-sm-2 col-form-label">ID</label>
          <div class="col-sm-10">
            <input type="text" name="id" readonly class="form-control-plaintext" id="id" value="${dto.id}">
          </div>
        </div>

        <div class="mb-3 row">
          <label for="title" class="col-sm-2 col-form-label">제목</label>
          <div class="col-sm-10">
            <input type="text" name="title" class="form-control-plaintext border rounded-1 p-2" id="title" value="${dto.title}">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="content" class="col-sm-2 col-form-label">내용</label>
          <div class="col-sm-10">
            <textarea id="content" name="content" class="form-control-plaintext border rounded-1 p-2" rows="10">${dto.content}</textarea>
          </div>
        </div>

		<!-- 첨부파일 있을 경우와 없을 경우 처리 -->
		<c:if test="${not empty dto.imageFileName && dto.imageFileName != null}">
        <div class="mb-3 row">
          <label for="imageFileName" class="col-sm-2 col-form-label">이미지</label>
          <div class="col-sm-10">
            <div>
              <!-- 원래 이미지파일 보관 -->
              <input type="hidden" name="originalFileName" value="${dto.imageFileName}">
              <div class="alert alert-light p-1" role="alert">
                <label id="previewName">${dto.imageFileName}</label>
              </div>
              <div class="alert alert-light p-1" role="alert">
              	<img src="${ctxPath}/download.do?imageFileName=${dto.imageFileName}&articleNO=${dto.articleNO}" id="preview" width="100%">
              </div>
            </div>
            <!-- 수정된 이미지 파일 이름 보관 -->
            <input type="file" onchange="readURL(this)" class="form-control-plaintext border rounded-1 p-2" id="imageFileName" name="imageFileName" value="${dto.imageFileName}">
          </div>
        </div>
		</c:if>
		
        <div class="mb-3 row">
          <label for="writeDate" class="col-sm-2 col-form-label">등록일자</label>
          <div class="col-sm-10">
            <input type="text" readonly class="form-control-plaintext" name="writeDate" id="writeDate" value="${dto.writeDate}">
          </div>
        </div>

      </div>


      <div class="w-75 m-auto d-flex justify-content-center">
        <div class="w-75 d-flex justify-content-between">
          <div>
          <button type="submit" class="btn btn-secondary btn-sm">수정완료</button>
          <button type="button" class="btn btn-secondary btn-sm" onClick="list()">취소</button>
          </div>
        </div>
      </div>


	</form>
    </div>
  </div>
  
  
  <script type="text/javascript">
  	function list(){
  		location.href="${ctxPath}/boardlist/list.do"
  	}
  	
  	function reply(){
  		location.href="${ctxPath}/boardlist/reply.do"
  	}
  
  	function modify(){
  		location.href="${ctxPath}/boardlist/modify.do"
  	}
  	
 // 제이쿼리를 이용하여 파일 첨부시 미리보기 기능 구현
    function readURL(imageFileName){
  		//$('#show').show()
  		
      if (imageFileName.files && imageFileName.files[0]){
        // 파일 입출력 처리하는 객체 생성
        var reader = new FileReader;
        
        // 파일읽기객체 .onload: 입력이 정상적으로 완료되는 처리하는 메소드
        reader.onload = function(e){
         
         var str = imageFileName.value;
         var idx = str.lastIndexOf('\\')
         var name = str.substring(idx+1)

         // html에 소스 넘겨주는 기능
         $('#preview').attr('src',e.target.result)
	  	 $('#previewName').text(name)


         // console.log(str)
         // console.log(str.substring(idx+1))
        }
        reader.readAsDataURL(imageFileName.files[0]);


      }
    }
  
  </script>
  
  
</body>
</html>