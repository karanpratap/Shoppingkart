<%@page import="com.kart.entity.Product"%>
<%@page import="com.kart.entity.Order"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Orders</title>
</head>
<body>
	<%
		String msg=(String)request.getAttribute("MSG");
		if(msg==null)
			msg="You are not Signed in";
	%>
	<h1>Your Orders</h1>
	<hr>
	<div align="right"> <font color="blue"><%= msg %></font> <a href="Servlet2?ACTION=SIGNOUT"><input type="button" value="LOGOUT"></a></div>
	<a href="Servlet2?ACTION=HOME"><input type="button" value="Home"></a> | <a href="login.jsp"><input type="button" value="Login"></a> | <a href="signup.jsp"><input type="button" value="Signup"></a> | <a href="Servlet2?ACTION=CART"><input type="button" value="My Cart"></a> | <a href="Servlet2?ACTION=ORDERS"><input type="button" value="My Orders"></a>
	<br/>
	<% if((String)request.getAttribute("MSG")==null){ %>
		<div align="center">
		<br/><br/><br/>
		<h2><font face="LM Sans Quot 8" color="Blue">SORRY!</font></h2>
		<br/><br/><br/>
		You need to <a href="login.jsp">sign in</a> to view your orders
		<br/><br/>
		Don't have an account? <a href="signup.jsp">Sign Up Now</a>
		</div>
	<% } 
	   else{ 
		Vector<Order> orders=(Vector<Order>)request.getAttribute("ORDERS_VECT");
		System.out.print(orders);
		if(orders.isEmpty()){
	%>
		<div align="center">
		<br/><br/><br/>
		<h2><font face="LM Sans Quot 8" color="Blue">SORRY!</font></h2>
		<br/><br/><br/>
		There are no orders for your account right now!
		</div>
	<%
		}
		else{
	%>
		
		<br/><br/>
	<table>
	<thead>
		<tr>
			<td><b>Product</b></td>
			<td><b> - </b></td>
			<td><b> Price </b></td>
		</tr>
	</thead>
	<%
			for(Order order : orders){
				int total=order.getTotal();
				for( Product product : order.getProducts()){
					String name=product.getName();
					int price=product.getPrice();
	%>
	
		
			<tr>
				<td> <%= name %> </td>
				<td> - </td>
				<td> Rs. <%= price %> </td>		
			</tr>
		
		
	
	<%			}
	%>
	
		<td><b>Order Total: Rs. <%= total %></b><br/><br/></td>
			
	<%			
			}
	%>
	</table>
		
	<%		
		}
	%>
	<% } %>
</body>
</html>



