<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Manage Users - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2 class="pageheading">Review Management</h2>
	</div>
	<c:if test="${message!=null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th> Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Comment</th>
				<th>Customer</th>
				<th> Review On</th>
				<th>Action</th>
			</tr>
			<c:forEach var="rev" items="${listReview}" varStatus="status">
				<tr>
					<td>${status.index} </td>
					<td>${rev.reviewId}</td>
					<td>${rev.book.title}</td>
					<td>${rev.rating}</td>
					<td>${rev.headline}</td>
					<td>${rev.comment}</td>
					<td>${rev.customer.fullName}</td>
					<td>${rev.reviewTime}</td>
					
					<td>
						<!-- <a href="edit_category?id=${rev.reviewId}">Edit</a> &nbsp; -->
						<a href="javascript:void(0);" class="deleteLink" id="${rev.reviewId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
	
	
	<jsp:directive.include file="footer.jsp"/>
	<script>
	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click",function(){
				reviewId=$(this).attr("id");
				if(confirm('Are you sure you want to delete the review with ID '+reviewId+' ?')){
					window.location='delete_review?id='+reviewId;
				}
			})
		});
	});
	</script>
</body>
</html>