<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Internal Server Error</title>
</head>
<body>
	<div align="center">
		<div>
			<img src="${pageContext.request.contextPath}/images/BookstoreLogo.png"/>
		</div>
		<div align="center">
			<h2>Sorry, the server has encounted an error while fulfilling your request.</h2>
			<h3>Please check back later or contact our adminstrators</h3>
		</div>
		<div>
			<a href="javascript:history.go(-1);">Go Back </a>
		</div>
	</div>
</body>
</html>