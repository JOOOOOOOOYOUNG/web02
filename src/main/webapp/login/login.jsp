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
  <style>
    
    .login > .row {
      margin-bottom: 1em;
      width: 1024px;
    }
  </style>
</head>
<body>

<jsp:include page="../includes/navDiv.jsp" />

<div class="container ">
    	<div class="main w-75 m-auto ">
    	
        <form action="${ctxPath}/login" method="post" class="d-flex justify-content-center">
            <div class="login row m-3 p-3 border rounded-2 justify-content-center">
            
				
                <div class="row">
                    <div class="row col-md-6 d-flex align-items-center">
                        <label >아이디</label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" name="user_id" id="user_id" class="form-control ">
                        <div class="text-danger" id="id_message"></div>
                    </div>
                </div>
                <div class="row m-2">
                    <div class="row col-md-6 d-flex align-items-center">
                        <label >비밀번호</label>
                    </div>
                    <div class="col-md-6">
                        <input type="password" name="user_pw" id="user_pw"  class="form-control" >
                        <div class="text-danger" id="pw_message"></div>
                    </div>
                </div>
                <hr class="m-2">
                <div class="row m-2">
                	<div class="col-md-6">
	                    <div class="mb-3 form-check">
	                        <input type="checkbox" class="form-check-input" name="auto" id="auto" value="off">
	                        <label class="form-check-label">자동 로그인</label>
	                    </div>
                    </div>
                    <div class="col-md-6">
	                    <div class="mb-3 form-check">
	                        <input type="checkbox" class="form-check-input"  name="keepLogin" id="keepLogin" value="keep">
	                        <label class="form-check-label">로그인 유지</label>
	                    </div>
                    </div>
                    
                    
                </div>
                <div class="row justify-content-around">
                    <div class="row col-md-6 mb-2">
                        <input type="submit" id="btnLogin" value="로그인"  class="btn btn-success" >
                    </div>
                    <div class="row col-md-6 mb-2">
                        <input type="reset"  value="다시입력" class="btn btn-warning">
                    </div>
                </div>
            </div>
        </form>
        
        </div>
    </div>
    
  
  
<!--  jQuery CDN -->  
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		
		var user_id;
		var user_pw;
		var auto;
		
		$('#btnLogin').click(function(e){
			e.preventDefault(); // 내장된 이벤트 제거
			
			user_id = $('#user_id').val()
			user_pw = $('#user_pw').val()
			auto = $('#auto').val()
			
			// html요소 값 => js 변수 저장 => json형식변환 => ajax()으로 전송
			var _jsonData = '{'+
				'"user_id": "'+ user_id +'",'+
				'"user_pw": "'+ user_pw +'",'+
				'"auto": "'	 + auto + 
			'"}'
			
			console.log(_jsonData);
			
			$.ajax({
				type: "post",
				async: false,
				url: "${ctxPath}/login",
				dataType: "text",	// 서버로부터 받은 데이터의 타입
				data: {logindata: _jsonData},
				success: function(data, textStatus){
					// json구조 문자열을 json객체로 전환
					var jsonMessage = JSON.parse(data)
					
					console.log(jsonMessage);
					console.log(jsonMessage.code,jsonMessage.message);
					
					// clear
					$('#id_message').text('')
					$('#pw_message').text('')
					
					if (jsonMessage.code === 'id_fail')
						$('#id_message').text(jsonMessage.message)
					else if (jsonMessage.code === 'pw_fail')
						$('#pw_message').text(jsonMessage.message)
					else
						location.href="${ctxPath}/boardlist/list.do"
					
					//$('#message').text(data);
				},
				
				error: function(){},
				complete: function(){}
			
			}); // end ajax()
			
		});	// login button
		
		// 포커스 이벤트 처리
		$('#user_id').focus(function(){
			$(this).val('')
		});
		$('#user_pw').focus(function(){
			$(this).val('')
		});
		
		$('#auto').click(function(){
			if ($(this).is(":checked")==true){
				$(this).val('on')
			} else {
				$(this).val('off')
			}
			
		});
		
		// 로그인 유지: 쿠키값 읽어오기
		$('#keepLogin').click(function(){
			// 자바스크립트
			let rememberMe;
			
			if (document.cookie != ""){
		        // "notShowPop="+"false"+";path=/; expires=-1"
		        // [0]=>notShowPop=false, [1]=>path=/, [2]=>expires=-1
		        cookie = document.cookie.split(";")

		        for (let i=0; i<cookie.length; i++){
		          // notShowPop=false
		          // [0]=>notShow, [1]=>false
		          element = cookie[i].split("=")
		          value = element[0]
		          value = value.replace(/^s*/,'') // 정규식 공백제거

		       //   if (value == "notShowPop"){
		            result = element[1];
		       //   }

		        }
			 }
			
			if (result){
				console.log('쿠키값: '+result)
			} else {
				console.log('쿠키값없음')
				
			}  
	
		});
		
		
		
/*		
		$('#auto').change(function(){
			auto = this.val()
			console.log(auto)
		})
*/		
	});




</script>



</body>
</html>