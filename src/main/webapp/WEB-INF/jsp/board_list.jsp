<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.detail {border: solid grey 1px; width:300px;height: 200px; }
	body {background-image: url("/images/xmas.jpg");background-size: cover;}
	h1 {text-align: center; font-size:90px;font-family: fantasy;}
	#a {text-align: center;font-family: cursive;}
	table {margin-top: 0px;margin: auto; font-size: 25px; font-family: fantasy; border-style: dashed;}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h1>Welcome to meg's world</h1>
	<table>
		<tr><th></th><th>TITLE</th><th>FROM</th><th>DATE</th></tr>
		<c:forEach var="b" items="${boardlist}">
			<tr>
				<td><a href="detail?num=${b.num}">${b.num}</a></td>
				<td>${b.title}</td>
				<td>${b.author}</td>
				<td>${b.wdate}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<div id="a">
		<a href = "upload">Leave Your Footprint</a>
	</div>
</body>
</html>