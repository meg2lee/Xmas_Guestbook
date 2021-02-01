<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
	<!doctype html>
	<html>
	<head>
	</head>
	<body>
		<div class="ai-preloader active">
			<div class="ai-preloader-inner h-100 d-flex align-items-center justify-content-center">
				<div class="ai-child ai-bounce1"></div>
				<div class="ai-child ai-bounce2"></div>
				<div class="ai-child ai-bounce3"></div>
			</div>
		</div>
		<div class="wrapper">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
			<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		</div>
	</body>
	</html>