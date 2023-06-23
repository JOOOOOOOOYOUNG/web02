<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>REST API 테스트</h3>
<hr>
<form >
	<div><input type="button" id="post" value="post방식요청"></div>
	<div><input type="button" id="get"value="get방식요청"></div>
	<div><input type="button" id="put"value="put방식요청"></div>
	<div><input type="button" id="delete"value="delete방식요청"></div>
</form>
<hr>
응답 결과
<div id="result"></div>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		console.log('jquery OK')
		let  _id = $('#id').val()
		
		var jsonObj = {
			id:'hong1000',//id:_id,
			name:'길순이'
		}
		// json객체 -> 문자열 전환
		var jsonObjString = JSON.stringify(jsonObj);
		console.log("json:"+jsonObj, "jsonString:"+JSON.stringify(jsonObj))
		
		$('#post').click(function(){
			
			$.ajax({
				type: "post",
				url:"/web02/rest_api",
				data : {
					jsonInfo : jsonObjString,
					text : 'hello!!'
				},
				success: function(data, sts){
					console.log('success')
					console.log(data,sts)
				},
				error: function(data, sts){
					console.log('error')
					console.log(data,sts)
				}
				
			});
		})
		$('#get').click(function(){
			$.ajax({
				type: "get",
				url:"/web02/rest_api",
				data : JSON.stringify(jsonObj),
				success: function(data, sts){
					console.log('success')
					console.log(data,sts)
				},
				error: function(data, sts){
					console.log('error')
					console.log(data,sts)
				}
				
			});
		})
		$('#put').click(function(){
			$.ajax({
				type: "put",
				url:"/web02/rest_api",
				data : JSON.stringify(jsonObj),
				success: function(data, sts){
					console.log('success')
					console.log(data,sts)
					
					let jsonInfo = JSON.parse(data)
					console.log(JSON.stringify(jsonInfo,null,' '))
					
					let memberInfo =""
					for(let i in jsonInfo.member){
						memberInfo += jsonInfo.member[i].id+"<br>"
						memberInfo += jsonInfo.member[i].name+"<br>"
					}
					
					$('#result').html(memberInfo)
					
					
				},
				error: function(data, sts){
					console.log('error')
					console.log(data,sts)
				}
				
			});
		})
		$('#delete').click(function(){
			$.ajax({
				type: "delete",
				url:"/web02/rest_api",
				data : JSON.stringify(jsonObj),
				success: function(data, sts){
					console.log('success')
					console.log(data,sts)
				},
				error: function(data, sts){
					console.log('error')
					console.log(data,sts)
				}
				
			});
		})
		
	})
	
</script>
</body>
</html>



















