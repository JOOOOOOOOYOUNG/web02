<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>글 보기</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

</head>
<body>


  <div class="main">
    <div class="container">
      <div class="w-75 m-auto">
        <h4 class="text-center">글 상세 내용</h4>
        <hr>
      </div>
      <form action="${ctxPath}/boardlist/modify.do" method="post">
      <div class="w-75 m-auto">
        <div class="mb-3 row">
          <label for="no" class="col-sm-2 col-form-label">글번호</label>
          <div class="col-sm-10">
            <input type="text" name="articleNO" readonly class="form-control-plaintext" id="no" value="${dto.articleNO}">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="id" class="col-sm-2 col-form-label">ID</label>
          <div class="col-sm-10">
            <input type="text" name="id" readonly class="form-control-plaintext border rounded-1 p-2" id="id" value="${dto.id}">
          </div>
        </div>

        <div class="mb-3 row">
          <label for="title" class="col-sm-2 col-form-label">제목</label>
          <div class="col-sm-10">
            <input type="text" name="title" readonly class="form-control-plaintext border rounded-1 p-2" id="title" value="${dto.title}">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="content" class="col-sm-2 col-form-label">내용</label>
          <div class="col-sm-10">
            <textarea readonly id="content" name="content" class="form-control-plaintext border rounded-1 p-2" rows="10">${dto.content}</textarea>
          </div>
        </div>

		<!-- 첨부파일 있을 경우와 없을 경우 처리 -->
		<c:if test="${not empty dto.imageFileName && dto.imageFileName != null}">
        <div class="mb-3 row">
          <label for="imageFileName" class="col-sm-2 col-form-label">이미지</label>
          <div class="col-sm-10">
            <div>
              <img src="${ctxPath}/download.do?imageFileName=${dto.imageFileName}&articleNO=${dto.articleNO}" width="300">
              <div>${dto.imageFileName}</div>
              
            </div>
           <input type="hidden" name="imageFileName" value="${dto.imageFileName}" />
           <!--  <input type="file" readonly class="form-control-plaintext border rounded-1 p-2" id="imageFileName" name="imageFileName" value="${dto.imageFileName}"> -->
          </div>
        </div>
		</c:if>
		
        <div class="mb-3 row">
          <label for="writeDate" class="col-sm-2 col-form-label">등록일자</label>
          <div class="col-sm-10">
            <input type="text" readonly class="form-control-plaintext border rounded-1 p-2" name="writeDate" id="writeDate" value="${dto.writeDate}">
          </div>
        </div>

      </div>


      <div class="w-75 m-auto d-flex justify-content-center">
        <div class="w-75 d-flex justify-content-between">
          <div>
          <button type="submit" class="btn btn-secondary btn-sm">수정</button>
          <button type="button" class="btn btn-secondary btn-sm" onClick="del()">삭제</button>
          
          <button type="button" class="btn btn-danger btn-sm" id="delBtn">모달삭제</button>
          
          <button type="button" class="btn btn-secondary btn-sm" onClick="list()">목록</button>
          </div>
          <div>
          <button type="button" class="btn btn-secondary btn-sm" onClick="reply()">답글쓰기</button> 
          </div>
        </div>
      </div>

	</form>
    </div>
  </div>
  
  
  <!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="delModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">게시글 삭제</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        현재 ${dto.articleNO}번 게시글을 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
     	<button type="button" class="btn btn-primary" id="delOK">확인</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
  
  
  <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
  <script type="text/javascript">
  
  
  
  	// jQuery로 모달창 띄우기
  	$('#delBtn').click(function(){
  		$('#delModal').modal('show')
  	})
  	
  	
  	// 삭제 모달창에서 확인버튼 클릭시 삭제기능 수행
  	$('#delOK').click(function(){
			location.href="${ctxPath}/boardlist/delete.do?articleNO=${dto.articleNO}"
  	})
  	
  	
  	function list(){
  		location.href="${ctxPath}/boardlist/list.do?pageBlock=${section}&pageNum=${pageNum}"
  	}
  	
  	function reply(){
  		location.href="${ctxPath}/boardlist/reply.do?parentNO=${dto.articleNO}"
  	}
  
  	function del(){
  		
  		var isOK = confirm('현재 ${dto.articleNO}번 게시글을 삭제하시겠습니까?')
  		console.log('isOK? '+ isOK)
  		
  		if (isOK){
  			location.href="${ctxPath}/boardlist/delete.do?articleNO=${dto.articleNO}"
  		} else {
  			return;
  		}
  		
  	}
  	
  	
  
  </script>
  
  
</body>
</html>