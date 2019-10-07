<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result for ${keyword} - EverGreen Book Store</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div class="center">
	<c:if test="${fn:length(result)==0}">
		<center><h2>No result for "${keyword} " </h2></center>
	</c:if>
	<c:if test="${fn:length(result)>0}">
		<div align="left" style="width: 80%;margin: 0 auto; ">
			<center><h2>Result for "${keyword}" :</h2></center>
			<c:forEach items="${result}" var="book">
				<div>
					<div style=" display:inline-block; margin:20px ;width: 10%"align="left" >
						<div align="left">
							<a href="view_book?id=${book.bookId}">
								<img src="data:image/jpg;base64,${book.base64Image}"width="128" height="164"/>
							</a>
						</div>
					</div>
						<div style="display:inline-block; margin:20px; vertical-align: top;width: 60%" align="left">
							<div><h2><b><a href="view_book?id=${book.bookId}">${book.title}</a></b></h2></div>
							<div>Rating *****</div>
							<div><i>${book.author}</i></div>
							<div><p>${fn:substring(book.desciption,0,100)}</p></div>
							</div>
						<div style="display:inline-block; margin:20px;vertical-align:top;">
							<h3><b>$ ${book.price}</b></h3>
							<h3><a href="">Add to Cart</a></h3>
						</div>
				
				</div>
			</c:forEach>
		</div>
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>