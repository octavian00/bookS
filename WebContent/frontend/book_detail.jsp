<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${book.title} Book Store</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<table width="80%" style="border:0">
			<tr height="">
				<td colspan="3" align="left">
					<h2>${book.title}</h2> by ${book.author}
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<img src="data:image/jpg;base64,${book.base64Image}"width="250" height="300"/>
				</td>
				<td valign="top" align="left">
					Rating *****
				</td>
				<td valign="top" rowspan="2"width="20%">
					<h2>$${book.price}</h2>	
					<br></br>
					<button type="submit">Add cart</button>
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align: justify;">
					${book.desciption}
				</td>
			</tr>
			<tr>
				<td><h2>Customer Reviews</h2></td>
				<td colspan="2" align="center">
					<button>Write a Customer Review </button>
				</td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>