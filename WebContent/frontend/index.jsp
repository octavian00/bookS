<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EverGreen Book Store</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<br/><br/>
		<div align="center" style="width: 80%;margin: 0 auto; ">
			<h2>New Books: </h2>
			<c:forEach items="${listNewBook}" var="book">
				<div style="float:left; inline-block; margin:20px">
					<div>
						<a href="view_book?id=${book.bookId}">
							<img src="data:image/jpg;base64,${book.base64Image}"width="128" height="164"/>
						</a>
					</div>
					<div><b><a href="view_book?id=${book.bookId}">${book.title}</a></b></div>
					<div>Rating *****</div>
					<div><i>${book.author}</i></div>
					<div><b>$ ${book.price}</b></div>
					
				</div>
				
			</c:forEach>
		</div>
		<div align="center"style="clear: both;">
			<h2>Best-Selling Books: </h2>
		</div>
		<div align="center"style="clear: both;">
			<h2>Most-favored Books: </h2>
		</div>
		<br/><br/>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>