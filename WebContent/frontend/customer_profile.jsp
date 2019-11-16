<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Customer profile - Online Book Store</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
			<br/>
				<h2>Welcome , ${loggedCustomer.fullName}</h2>
			<br/>
		
		<table class="normal">
			<tr>
				<td><b>E-mail Address</b></td>
				<td>${loggedCustomer.email}</td>
			</tr>
			<tr>
				<td><b>Full Name</b></td>
				<td>${loggedCustomer.fullName}</td>
			</tr>
			<tr>
				<td><b>Phone Number</b></td>
				<td>${loggedCustomer.phone}</td>
			</tr>
			<tr>
				<td><b>Address</b></td>
				<td>${loggedCustomer.address}</td>
			</tr>
			<tr>
				<td><b>City</b></td>
				<td>${loggedCustomer.city}</td>
			</tr>
			<tr>
				<td><b>Zip Code</b></td>
				<td>${loggedCustomer.zipcode}</td>
			</tr>
			<tr>
				<td><b>Country</b></td>
				<td>${loggedCustomer.country}</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center"><a href="edit_profile">Edit My Profile</a>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>