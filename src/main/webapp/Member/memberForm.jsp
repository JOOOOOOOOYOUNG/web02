<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />


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


 <div class="main">
    <div class="container">
      <div>
        <h3 class="text-center m-3">회원정보 등록</h3>
      </div>
      <hr>
      <div class="w-75 border p-3 m-3 m-auto">
      <form action="${ctxPath}/member/insertMember.do" method="post" id="updateForm">

      <div class="mb-3 row">
      <div class="col-8">
        <label class="form-label">ID</label>
        <input type="text" name="id" class="form-control" id="id" placeholder="아이디를 입력하세요">
      </div>
      <div class="col-4 d-flex align-items-end">
      <button type="button" id="chkBtn" class="btn btn-outline-warning">중복체크</button>
      </div>
      <div class="message_id text-danger"></div>
      </div>

      <div class="mb-3">
        <label for="pwd" class="form-label">PASSWORD</label>
        <input type="password" name="pwd" class="form-control" id="pwd" placeholder="비밀번호">
        <div class="message_pwd text-danger"></div>
      </div>
      
      <div class="mb-3">
        <label for="pwd2" class="form-label">PASSWORD **</label>
        <input type="password" class="form-control" id="pwd2" placeholder="비밀번호 확인">
        <div class="message_pwd2 text-danger"></div>
      </div>

      <div class="mb-3">
        <label for="name" class="form-label">NAME</label>
        <input type="text" name="name" class="form-control" id="name" placeholder="이름">
        <div class="message_name text-danger"></div>
      </div>

      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" name="email" class="form-control" id="email" placeholder="이메일">
        <div class="message_email text-danger"></div>
      </div>

      <div class="mb-3">
        <input type="submit" id="updateBtn" class="btn btn-outline-info" value="저장">
        <a href="${ctxPath}/member/listMember.do" class="btn btn-outline-primary">목록</a>
      </div>

      </form>
    </div>
    </div>
  </div>


	<script type="text/javascript">
		$(function(){
			console.log('jQuery ok...');
			
			$('#chkBtn').click(function(e){
				e.preventDefault();	//submit() 기능 중지
				
				var send_id = $('#id').val()
				console.log('id: '+send_id)
				
				
				
				if (send_id.length==0 || send_id==''){
					alert("ID를 입력하세요.")
					
					$('#id').focus()	// 커서를 id항목으로 위치
					return;
				}
				
				// 중복체크 요청(Ajax방식)
				$.ajax({
					type: 'post',
					async: false,
					url: '/web01/member/checkId.do',
					dataType: "text",
					data: {id: send_id},
					success: function(data, textStatus){
						if (data == 'usable'){
							alert('사용가능한 아이디입니다.')
							$('.message_id').text('사용할 수 있는 아이디입니다.')
						//	$('#chkBtn').prop('disabled',true)
						} else {
							alert('이미 등록된 아이디입니다.');
							$('.message_id').text('이미 등록된 아이디입니다.')
						}	
					}
					
				});	//end ajax
				
			}); //chkBtn()
			
			$('#updateBtn').click(function(e){
				e.preventDefault();
				
				let pwd = $('#pwd').val()
				let pwd2 = $('#pwd2').val()
				
				if ($('#id').val() == '' || $('#id').val().length==0){
					$('.message_id').text('아이디는 필수항목입니다.')
					$('#id').focus();
					return;
				}
				
				if ($('#pwd').val() == '' || $('#pwd').val().length==0){
					$('.message_pwd').text('비밀번호는 필수항목입니다.')
					$('#pwd').focus();
					return;
				}
				
				if ($('#pwd2').val() == '' || $('#pwd2').val().length==0){
					$('.message_pwd2').text('비밀번호 확인은 필수항목입니다.')
					$('#pwd2').focus();
					return;
				}
				
				if (pwd != pwd2){
					alert('비밀번호 불일치')
					$('#pwd').focus()
					return;
				}
	
				
				if ($('#email').val() == '' || $('#email').val().length==0){
					$('.message_email').text('이메일은 필수항목입니다.')
					$('#email').focus();
					return;
				}
				
				
				
				$('#updateForm').submit()
			});
			
		});
	</script>


</body>
</html>