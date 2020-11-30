<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>You are always welcome</title>
<style>
	body {background-image: url("/images/xmas2.jpg");}
	table {margin-left: auto;margin-right: auto; font-size: 50px; font-family: fantasy;
	background-size: cover;}
	form {margin-left: auto;margin-right: auto; font-size: 30px; font-family: fantasy;
	background-size: cover;margin-top: 15%}
	input {height:20px;font-size: 20px}	
	p {text-align: center; font-family: cursive;}
	div {text-align: center;}
</style>
	<script src='https://code.jquery.com/jquery-3.5.1.min.js'></script>
	<script>
	
	function deleteIt(method,num) { 
		
		$.ajax({ 
		url:'detail', 
		method:method, /* 전송방식은 delete */
		data: {'num': num},
		dataType:'text',
		success:function (res){
			if(confirm("Would you like to delete below?")){
				location.href='board';
			} else {
				return false;
			}
		}, 
		error:function(xhr, status, err){
			alert(status+', '+err);
		}
	
		});
	}
		
	function updateIt() { 
		
		$.ajax({ 
		url:'detail', 
		method: 'post', /* 전송방식은 put */
		data: $('form').serialize(), //from 전체값을 query string으로 제출
		dataType:'text',
		success:function (res){
			if(res=='true'){
				alert("saved");
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
	<form action ="detail" method="post" onsubmit="javascript:updateIt();">
			<p><label for="num">Content no</label>
			<input type="text" name="num" id="num" value="${board.num}" readonly></p>
			<p><label for="author">Written by</label>
			<input type="text" name="author" id="author" value="${board.author}" readonly></p>
			<p><label for="wdate">Written on</label>
			<input type="text" name="wdate" id="wdate" value="${board.wdate}" readonly></p>
			<p><label for="title">Title</label>
			<input type="text" name="title" id="title" value="${board.title}" readonly></p>
			<p><label for="contents">Contents</label>
			<input type="text" name="contents" id="contents" value="${board.contents}"></p>
			<p id="btn">
				<button type="submit">Submit</button>
				<button type="reset">Cancel</button>
			</p>
	</form>
	<div>
		<c:forEach var="result" items="${fileList}">		
			<a href="download/${result.filename}" id ="f">${result.filename}</a>
		</c:forEach>
	</div>
	<p>
		<a href="board">Main Page</a>
		<a href="javascript:deleteIt('delete',${board.num});">Delete</a>
	</p>
</body>
</html>