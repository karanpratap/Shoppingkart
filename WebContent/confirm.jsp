<%@page import="com.kart.entity.Product"%>
<%@page import="com.kart.entity.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout Confirmation</title>
</head>
<body>

<%

	String uid=(String)request.getAttribute("USERID");
	Order order=(Order)request.getAttribute("ORDER_DETAILS");
	String oid=(String)request.getAttribute("ORDER_ID");

%>

	<h1>Confirm Checkout</h1>
	
	<div align="center">ORDER DETAILS
	<br/>=============
	<br/><br/>
	USER: <%= uid %> <br/>
	ORDER # <%= oid %> <br/>
	ORDER DETAILS: <br/>
	
<%

	for(Product product : order.getProducts()){

%>
		ITEM NAME: <%= product.getName() %> PRICE: Rs. <%= product.getPrice() %> <br/>
<%

	}

%>

	TOTAL AMOUNT: <%= order.getTotal() %> 
	
	<br/><br/><a href="Servlet2?ACTION=CONFIRM"><input type="button" value="Confirm and Checkout"></a><a href="Servlet2?ACTION=CART"><input type="button" value="Cancel"></a>
	</div>
</body>
</html>