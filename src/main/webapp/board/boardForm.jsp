<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />
<c:set var="show" value="0" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
   
   
    <!DOCTYPE html>
    <html>
    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>게시글 등록</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    
    </head>
    <body>
    
    
      <div class="main">
        <div class="container mt-3">
           <form action="${ctxPath}/boardlist/insert.do" enctype="multipart/form-data" method="post"> <!-- 업로드(이미지파일) 기능이 포함된 form -->
       <!--   <form action="${ctxPath}/boardlist/insert.do" method="post"> -->
          <div class="w-75 m-auto">
            <h4 class="text-center">게시글 등록</h4>
            <hr>
          </div>
          <div class="w-75 m-auto">
            <input type="hidden" name="articleNO" value="1000">
            <div class="mb-3 row">
              <label for="id" class="col-sm-2 col-form-label">ID</label>
              <div class="col-sm-10">
                <input type="text" name="id" class="form-control border rounded-1 p-2" id="id" placeholder="아이디">
              </div>
            </div>
    
            <div class="mb-3 row">
              <label for="title" class="col-sm-2 col-form-label">제목</label>
              <div class="col-sm-10">
                <input type="text" name="title" class="form-control border rounded-1 p-2" id="title" placeholder="글제목">
              </div>
            </div>
    
            <div class="mb-3 row">
              <label for="content" class="col-sm-2 col-form-label">내용</label>
              <div class="col-sm-10">
                <textarea id="content" name="content" maxlength="4000" class="form-control border rounded-1 p-2" rows="10"></textarea>
              </div>
            </div>
    
            <div class="mb-3 row">
              <label for="imageFileName" class="col-sm-2 col-form-label">이미지</label>
              <div class="col-sm-10">
         		<!-- jquery에서 show태그는 숨기기로 설정, 파일선택시 표시하기로 변경 -->
         		<div id="show">
                  <div class="alert alert-light p-1" role="alert">
                   <label id="previewName"></label>
                  </div>
                  <div class="alert alert-light" role="alert">
                   <img src="#" id="preview" width="300">
               	  </div>
                </div>
  
  	  
  	  
                <input type="file" onchange="readURL(this)" class="form-control p-2" id="imageFileName" name="imageFileName">
              </div>
            </div>
    
    
    
          </div>
    
          
          <div class="w-75 m-auto d-flex justify-content-center">
            <div class="w-75 d-flex justify-content-between">
              <div>
              <button type="submit" class="btn btn-secondary btn-sm">저장</button>
              <button type="button" onClick="list()" class="btn btn-secondary btn-sm">목록</button>
              </div>
              <div>
              <!-- <button type="button" class="btn btn-secondary btn-sm">답글쓰기</button>  -->
              </div>
            </div>
          </div>
    
          
        </form>
        </div>
      </div>
    
    
      <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
      <script>
      
      	// 이미지태그 숨기기
      	$('#show').hide()  //display: none
        
   		// 제이쿼리를 이용하여 파일 첨부시 미리보기 기능 구현
        function readURL(imageFileName){
      		$('#show').show()
      		
          if (imageFileName.files && imageFileName.files[0]){
            // 파일 입출력 처리하는 객체 생성
            var reader = new FileReader;
            
            // .onload: 입력이 정상적으로 완료되는 처리하는 메소드
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
      	
      	
      	function list(){
      		location.href="${ctxPath}/boardlist/list.do"
      	}
      	
    
      </script>
    
    
    </body>
    </html>