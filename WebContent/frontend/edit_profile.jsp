<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Edit My Profile</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/jquery-ui.min.css"/>
	
	
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">
			Edit My Profile
		</h2>
	</div>
	<div align="center">
			 <form action="update_profile" method="post" id="customerForm" >
		<table class="form">
			<tr>
				<td align="right">E-mail:</td>
				<td align="left"><b>${loggedCustomer.email}</b>(Cannot be changed)</td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" name="fullName" id="fullName" size="45" value="${loggedCustomer.fullName}" ></td>
			</tr>
			
			<tr>
				<td align="right">Phone Number:</td>
				<td align="left"><input type="text" name="phoneNumber" id="phoneNumber" size="45" value="${loggedCustomer.phone}" ></td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td align="left"><input type="text" name="adress" id="adress" size="45" value="${loggedCustomer.address}"></td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td align="left"><input type="text" name="city" id="city" size="45" value="${loggedCustomer.city}"></td>
			</tr>
			<tr>
				<td align="right">Zip Code:</td>
				<td align="left"><input type="text" name="zipCode" id="zipCode" size="45" value="${loggedCustomer.zipcode}" ></td>
			</tr>
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input type="text" name="country" id="country" size="45" value="${loggedCustomer.country}"></td>
			</tr>
			<tr>
				<td colspan="2">
					<i>(Leave password fields blank if you don't want to change password)</i>
				</td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" name="password" id="password" size="45" ></td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td align="left"><input type="password" name="confirmPassword" id="confirmPassword" size="45" ></td>
			</tr>
			<tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>	
		</table>
		</form>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#customerForm").validate({
			rules:{
				email : {
					required:true,
					email:true
				},
				fullName:"required",
				confirmPassword:{
					equalTo:"#password"
				},
				phoneNumber:"required",
				adress:"required",
				city:"required",
				country:"required",
				zipCode:"required",
			},
			
			messages:{
				email : {
					 required:"Please enter e-mail adress",
					 emai:"Please enter a valid adress"
					},
				fullName:"Please enter full Name",
				confirmPassword:{
					equalTo:"Confirm does not match password "
				},
				phoneNumber:"Please enter phone",
				adress:"Please enter address",
				city:"Please enter city ",
				country:"Please enter country",
				zipCode:"Please enter zipCode",
			}
		});
		$("#buttonCancel").click(function(){
			history.go(-1);
			
		});
	});
	
	
</script>
</html>