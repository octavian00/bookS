<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Create New Customer</title>
	<link rel="stylesheet" href="../css/style.css">
	<link rel="stylesheet" href="../css/jquery-ui.min.css"/>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/richtext.min.css">
	
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer!=null}">
				Edit Customer
			</c:if>
			<c:if test="${customer==null}">
				Create New Customer
			</c:if>
		</h2>
	</div>
	<div align="center">
		<c:if test="${customer!=null}">
			 <form action="update_customer" method="post" id="customerForm" >
			 <input type="hidden" name="customerId" value="${customer.customerId}">
		</c:if>
		<c:if test="${customer==null}">
			 <form action="create_customer" method="post" id="customerForm" >
		</c:if> 
		<table class="form">
			<tr>
				<td align="right">E-mail:</td>
				<td align="left"><input type="text" name="email" id="email" size="45" value="${customer.email}"></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" name="fullName" id="fullName" size="45" value="${customer.fullName}"></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" name="password" id="password" size="45" value="${customer.password}"></td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td align="left"><input type="password" name="confirmPassword" id="confirmPassword" size="45" value="${customer.password}"></td>
			</tr>
			<tr>
				<td align="right">Phone Number:</td>
				<td align="left"><input type="text" name="phoneNumber" id="phoneNumber" size="45" value="${customer.phone}"></td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td align="left"><input type="text" name="adress" id="adress" size="45" value="${customer.address}"></td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td align="left"><input type="text" name="city" id="city" size="45" value="${customer.city}"></td>
			</tr>
			<tr>
				<td align="right">Zip Code:</td>
				<td align="left"><input type="text" name="zipCode" id="zipCode" size="45" value="${customer.zipcode}"></td>
			</tr>
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input type="text" name="country" id="country" size="45" value="${customer.country}"></td>
			</tr>
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
				password:"required",
				confirmPassword:{
					required:true,
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
				password:"Please enter password",
				confirmPassword:{
					required:"Please enter password",
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