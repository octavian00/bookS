<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Write a Review-Online Book Store</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp"/>
		<div align="center">
			<form id="reviewForm" >
				<table class="normal" width="60%">
					<tr>
						<td>
							<h2>You already wrote a review for this book</h2>
						</td>
						<td>&nbsp;</td>
						<td>
							<h2>${loggedCustomer.fullName}</h2>
						</td>
					</tr>
					<tr>
						<td colspan="3"><hr/></td>
					</tr>
					<tr>
						<td>
							<span id="#book-title"><b>${book.title}</b></span></br></br>
							 <img  src="data:image/jpg;base64,${book.base64Image} " width=200px" /> 
						</td>
						<td>
							<div id="rateYo"></div></br>
							<input type="text" name="headline" size="60" readonly="readonly" value="${review.headline}"/></br></br>
							<textarea name="comment"rows="10" cols="65"readonly="readonly"> ${review.comment}</textarea>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">

	$(document).ready(function(){
		  $("#rateYo").rateYo({
			    starWidth:"40px",
			    fullStar:true,
			    rating:${review.rating},
				readonly:true	   
		   });
			 
	});
</script>
</html>