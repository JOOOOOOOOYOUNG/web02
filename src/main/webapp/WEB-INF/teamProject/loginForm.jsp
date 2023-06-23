<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="ctxPath" value="<%=request.getContextPath() %>" /> 
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="https://kit.fontawesome.com/01debed61a.js" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"> 
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

  <style>

    .search:focus {
      outline: none;
    }

    .hearder_link {
      color: black;
      text-decoration-line: none;
    }

    .login {
      margin-top: 8%;
      margin-bottom: 2%;
    }

    .id_Form, .pw_Form {
      border: none;
      height: 2.2em;
    }

    .id_Form:focus, .pw_Form:focus {
      outline: none;
    }

    .a_tag {
      color: gray;
      text-decoration: none;
      margin: 1em;
      font-size: 0.8em;
    }

    .footer_bar {
      margin-top: 8%;
    }


  </style>

</head>
<body>
  


  
  <div class="main">
    <div class="container">

      <div class="logo row p-1">

        <div class="col-4 p-2 m-auto">
          <a href="#">
          <img src="https://image.a-rt.com/art/system/site/202207/1658299296317.png">
          </a>
        </div>

        <div class="col-4 mt-4">
         <div class="border-2 border-bottom border-secondary row d-flex justify-content-center">
          <div class="col-10">
            <input type="search" class="search border border-0 w-100 h-100" id="search" placeholder="검색어를 입력하세요.">
          </div>
          <div class="col-2 d-flex justify-content-end">
            <div class="d-flex m-2">
             <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-funnel" viewBox="0 0 16 16">
             <path d="M1.5 1.5A.5.5 0 0 1 2 1h12a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.128.334L10 8.692V13.5a.5.5 0 0 1-.342.474l-3 1A.5.5 0 0 1 6 14.5V8.692L1.628 3.834A.5.5 0 0 1 1.5 3.5v-2zm1 .5v1.308l4.372 4.858A.5.5 0 0 1 7 8.5v5.306l2-.666V8.5a.5.5 0 0 1 .128-.334L13.5 3.308V2h-11z"/>
             </svg>
            </div>
            <div class="d-flex m-2">
             <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
             <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
             </svg>
            </div>
          </div>
         </div>
        </div>

        <div class="col-4">
          <div class="m-auto d-flex justify-content-between w-25">

          <a href="#" class="hearder_link">
          <div class="row p-3 d-block align-content-center">
              <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
              <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
              <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
              </svg>
              <label class="hearder_label mt-1 text-center" style="font-size: 0.8em;">LOGIN</label>
          </div>
          </a>
    
          <a href="#" class="hearder_link">
          <div class="row p-3 d-block align-content-center">
              <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
              <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
              </svg>
              <label class="hearder_label mt-1 text-center" style="font-size: 0.8em;">JOIN</label>
          </div>
          </a>
    
          <a href="#" class="hearder_link">
          <div class="row p-3 d-block align-content-center">
              <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-bag" viewBox="0 0 16 16">
              <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"/>
              </svg>
              <label class="hearder_label mt-1 text-center" style="font-size: 0.8em;">CART</label>
          </div>
          </a>
    
          <a href="#" class="hearder_link">
          <div class="row p-3 d-block align-content-center">
              <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-shop-window" viewBox="0 0 16 16">
              <path d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h12V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zm2 .5a.5.5 0 0 1 .5.5V13h8V9.5a.5.5 0 0 1 1 0V13a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V9.5a.5.5 0 0 1 .5-.5z"/>
              </svg>
              <label class="hearder_label mt-1 text-center" style="font-size: 0.8em;">STORE</label>
          </div>
          </a>
          
          </div>
        </div>

     </div>  <!-- logo -->
    </div>  <!-- container -->

     
      <nav class="navbar navbar-expand-lg p-1" style="background-color: rgb(240, 36, 36);">
        <div class="container-fluid">
         <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <div class="col-2"></div>
            <ul class="navbar-nav me-auto col-4 justify-content-start">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#" style="color: yellow; margin-right: 1em;">BRAND</a>
              </li>
              <li class="nav-item P-2">
                <a class="nav-link active" aria-current="page" href="#" style="color: white; margin-right: 1em;">MEN</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#" style="color: white; margin-right: 1em;">WOMEN</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#" style="color: white; margin-right: 1em;">KIDS</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#" style="color: white;">SALE</a>
              </li>
            </ul>
            <ul class="navbar-nav me-auto col-4 justify-content-end">
              <li class="nav-item P-2">
                <a class="nav-link active" aria-current="page" href="#" style="color: white;"><strong>기획전</strong></a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#" style="color: white; margin-left: 0.8em;"><strong>이벤트/쿠폰</strong></a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#" style="color: white; margin-left: 0.8em;"><strong>고객센터</strong></a>
              </li>
            </ul>
            <div class="col-2"></div>
          </div>
        </div>
      </nav>



      <div class="login">
        <h2 class="text-center"><strong>로그인</strong></h2>
      </div>

      <div class="d-flex">
        <div class="col-4"></div>

        <div class="col-4 text-center p-2">

          <form action="${ctxPath}/loginSeccess.doo" method="post">

          <div class="border-bottom d-flex">
            <div class="justify-content-start m-1">
              <svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
              <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
              </svg>
            </div>
            <input type="text" class="id_Form w-100" name="id" id="id" value="${member.id}" placeholder="아이디를 입력해 주세요.">
          </div>

          <div class="border-bottom d-flex mt-4">
            <div class="justify-content-start m-1">
              <svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16">
              <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>
              </svg>
            </div>
            <input type="password" class="pw_Form w-100" name="pwd" id="pwd" value="${member.pwd}" placeholder="비밀번호를 입력해 주세요.">
          </div>


          <div class="d-flex mt-3 m-2">
            <div>
              <input type="checkbox" name="auto" id="auto" class="justify-content-start" value="off">
              <label>아이디 저장</label>
            </div>
          </div>


          <div class="mt-4">
            <input type="submit" value="로그인" id="LoginBtn" name="LoginBtn" class="btn btn-dark w-100 rounded-0 p-3">
          </div>



         </form> 
        </div>  <!-- col-4 -->

        <div class="col-4"></div>
      </div>





      
      <div class="mt-2">
        <div class="d-flex justify-content-center">
          <a href="#" class="a_tag">아이디 찾기</a>
          <a href="#" class="a_tag">비밀번호 찾기</a>
          <a href="#" class="a_tag">회원가입</a>
      </div>
     




 
      <div class="footer_bar p-2 bg-secondary bg-opacity-10 border border-start-0 border-end-0">
        <div class="d-flex">
          <div class="col-2"></div>
          <div class="col-5">
            <div class="d-flex">
              <p class="m-1" style="font-size: 0.9em;"><strong>통합고객센터</strong></p>
              <p class="m-1" style="font-size: 0.9em;">1588-1234</p>
              <p class="m-1" style="color: rgb(144, 143, 143); font-size: 0.9em;"">월~금 09:00 ~ 12:00 / 13:00 ~ 18:00 (주말,공휴일 휴무)</p>
            </div>
          </div>
          <div class="col-1">
            <div class="m-1">
              <a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-0" style="font-size: 0.9em;"><strong>NOTICE</strong></a>
            </div>
          </div>
        </div>
      </div>     

    <!-- footer -->

    <div>
      <div class="row mb-3">
       <div class="col-2"></div>
       <div class="col-5">
         <img src="https://image.a-rt.com/art/system/site/202207/1658299296317.png" style="width: 18%;" class="mb-3 mt-3">
         <p style="font-size: 0.7em; color: rgb(105, 105, 105);">
           (주)에이비씨마트 코리아 대표이사 ｜ 이기호 주소 ｜ 서울특별시 중구 을지로 100, B동 21층 (을지로 2가, 파인에비뉴)<br>
           사업자등록번호 ｜ 201-81-76174 통신판매업신고 ｜ 제 2015-서울중구-1036호<br>
           개인정보보호 책임자 ｜ 박영수 이메일 ｜ abcmartcs@abcmartkorea.com
         </p>
         <p style="font-size: 0.7em;">
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">사업자정보확인</a> ｜ 
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">개인정보처리방침</a> ｜ 
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">이용약관</a> ｜ 
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">멤버십 이용약관</a>
         </p>
       </div>
       <div class="col-1 mt-4">
         <p style="font-size: 0.9em; color: rgb(36, 36, 36);"><strong>HELP</strong></p>
         <p style="font-size: 0.9em; color: rgb(105, 105, 105);">
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">고객센터</a><br>
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">매장찾기</a><br>
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">입점/제휴문의</a>
         </p>
       </div>

       <div class="col-1 mt-4">
         <p style="font-size: 0.9em; color: rgb(36, 36, 36);"><strong>BRAND</strong></p>
         <p style="font-size: 0.9em; color: rgb(105, 105, 105);">
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">HAWKINS</a><br>
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">SPERRY</a><br>
           <a href="#" class="link-secondary link-offset-2 link-underline-opacity-0">DANNER</a>
         </p>
       </div>

       
       
      </div>
     </div>  <!-- footer -->  



  </div>   <!-- main -->
  
  
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script type="text/javascript">
  
  
  $(function(){
	
	  var id;
	  var pwd;
	  var auto;
	  
	  
	  $('#LoginBtn').click(function(e){
		  e.preventDefault();
		  
		  id = $('#id').val()
		  pwd = $('#pwd').val()
		  auto = $('#auto').val()
		  
		  var _jsonData = '{'+
			'"id" : "'+ id +'",'+
			'"pwd" : "'+ pwd +'",'+
			'"auto" : "' + auto + 
			'"}'
		
		console.log(_jsonData);
			
			
		  $.ajax({
				type: "post",
				async: true,
				url: "${ctxPath}/loginSeccess.doo",
				dataType: "text",	// 서버로부터 받은 데이터의 타입
				data: {logindata: _jsonData},
				success: function(data, textStatus){
					
					
					// json구조 문자열을 json객체로 전환
					var jsonMessage = JSON.parse(data)
					
					console.log(jsonMessage);
					console.log(jsonMessage.code,jsonMessage.message);
					
					// clear
					//$('#id_message').text('')
					//$('#pw_message').text('')
					
					if (jsonMessage.code === 'id_fail')
						alert('아이디가 정확하지 않습니다.')
					else  (jsonMessage.code === 'pw_fail')
						alert('비밀번호가 정확하지 않습니다.')
					
				},
				
				error: function(){},
				complete: function(){}
			
			}) // ajax()
			
		})	// #LoginBtn	
			
		
		// 포커스 이벤트 처리
		$('#id').focus(function(){
			$(this).val('')
		});
		$('#pwd').focus(function(){
			$(this).val('')
		});
		
		$('#auto').click(function(){
			if ($(this).is(":checked")==true){
				$(this).val('on')
			} else {
				$(this).val('off')
			}
			
		});	
		
		
		
		//로그인 유지 : 쿠키값 읽어오기
		$('#LoginBtn').click(function() {
			let result;
			// 자바스크립트
			 if (document.cookie != ""){
	               
	                cookie = document.cookie.split(";")

	                for (let i=0; i<cookie.length; i++){
	                    
	                    element = cookie[i].split("=")
	                    value = element[0]
	                    value = value.replace(/^s*/,'')// 정규식 공백제거
	                    
                        result = element[1];

	                }

	            }
			
			if (result){
				console.log('쿠키값: '+result)
			} else {
				console.log('쿠키값없음')
				
			}
			
		});	// 로그인 유지 끝
		
			
		
	  
	  
	  
	  
  });
  
  




</script>


</body>
</html>