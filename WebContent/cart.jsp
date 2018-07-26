<%@page import="com.kart.entity.Product"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
</head>
<body>
	<%
		String msg=(String)request.getAttribute("MSG");
		if(msg==null)
			msg="You are not Signed in";
	%>
	<h1>Your Shopping Kart</h1>
	<hr>
	<div align="right"> <font color="blue"><%= msg %></font> <a href="Servlet2?ACTION=SIGNOUT"><input type="button" value="LOGOUT"></a> </div>
	<a href="Servlet2?ACTION=HOME"><input type="button" value="Home"></a> | <a href="login.jsp"><input type="button" value="Login"></a> | <a href="signup.jsp"><input type="button" value="Signup"></a> | <a href="Servlet2?ACTION=CART"><input type="button" value="My Cart"></a> | <a href="Servlet2?ACTION=ORDERS"><input type="button" value="My Orders"></a>
	<br/>
	<p>Items ready to be checked out!</p>
	<%
		
		Vector<String> items = (Vector<String>) session.getAttribute("ITEMS"); 
		Vector<Product> products=new Vector<Product>();
		int totalPrice=0;
		
		if(items!=null){
			for(String str : items){
				switch(str){
				case "raz":
					products.add(new Product(3660,"Razer Mouse and Keyboard Combo"));
					break;
				case "ac":
					products.add(new Product(246,"Assassin's Creed (PC)"));
					break;
				case "bmw":
					products.add(new Product(10095,"BMW Automobile"));	
					break;
				}
			}
		}
		
		session.setAttribute("PRODUCTS", products);
		
		if(products.isEmpty()){ %>
		<div align="center">
		<br/><br/><br/>
		<h2><font face="LM Sans Quot 8" color="Blue">EMPTY!</font></h2>
		<br/><br/><br/>
		There are items in your cart right now!
		</div>
		<% 
		}
		else{	
		%>
		
		
		<table>
			<thead>
	  			<tr>	
					<td><b>Product</b></td>
					<td> - </td>
					<td><b>Price</b></td>
	  			</tr>
			</thead>
			
			
		<%
			for(Product product : products) {
				totalPrice += product.getPrice(); %>
			<tr>
			
				<td><%= product.getName() %></td>
				<td> - </td>
				<td>Rs. <%= product.getPrice() %></td>
			
			</tr>
		<% 
			}
		%>
	
		</table>
		<br/><br/>
		<form action = "Servlet2?ACTION=CHECKOUT" method = "post">
			<input type="submit" value = "Checkout Now">
		</form>
		<form action = "Servlet2?ACTION=REMOVEALL" method = "post">
			<input type="submit" value = "Remove all Items">
		</form>
		<%
		}
		%>
		
</body>
</html>