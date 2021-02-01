<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {background-image: url("/images/xmas2.jpg");}
	form {margin-left: auto;margin-right: auto; font-size: 30px; font-family: fantasy;
	background-size: cover;margin-top: 15%}
	input,textarea {height:50px;font-size: 20px; background: transparent;}	
	textarea {width:300px}
	p {text-align: center; font-family: cursive;}
	div {margin-top:18%}
	
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	function add(){
		
		var form = $('form')[0];
		var formdata = new FormData(form);
		
		$.ajax({ 
			url:'upload', 
			type:'post',
			data: formdata,
			enctype: 'multipart/form-data', /*전송방식*/
			processData: false,  /*query string 형식으로 보내지않음*/
	        contentType: false,  /*내가 서버에 보내는 데이터 */
			dataType:'text',
			success:function (res){
				if(res.trim()=='true'){
					alert("successfully uploaded");
					location.href="board";
				} else {
					alert("failed");
					return false;
				}			
			}, 
			error:function(xhr, status, err){
				alert(status+', '+err);
			}
		
		});
	}

</script>
</head> 
<body>
<h3>Kindly leave your sweet messages to meg:)</h3>
<form action="upload" method="post" enctype="multipart/form-data">
	<p><label for="author">Written by</label>
	<input type="text" name="author"><br>
	<p><label for="wdate">Written on</label>
	<input type="date" name="wdate"><br>
	<p><label for="title">Title</label>
	<input type="text" name="title"><br>
	<p><label for="contents">Contents</label>
	<input type="text" name="contents" class="it"><br>
	<label for="contents">Files</label>
	<input type="file" name="files" id="files" multiple="multiple">	
	<button type="button" onclick="add();">Upload</button>
</form>
<p>
	<a href="board">Main Page</a>
</p>
</body>
</html>