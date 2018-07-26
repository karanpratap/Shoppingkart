<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping kart</title>
<script type="text/javascript">
	function addedAC(){	
		document.formac.btnAC.value="Added to kart";
	}
	function addedBMW(){	
		document.formbmw.btnBMW.value="Added to kart";
	}
	function addedRAZ(){	
		document.formraz.btnRAZ.value="Added to kart";
	}
</script>
</head>
<body>
	<%
		String msg=(String)request.getAttribute("MSG");
		if(msg==null)
			msg="Guest";
		String log=(String)request.getAttribute("LOG_INFO");
		if(log==null)
			log=" ";
	%>

	<h1>Shopping Kart</h1>
	<hr>
		<div align="right">Hello, <font color="blue"><%= msg %></font> <a href="Servlet2?ACTION=SIGNOUT"><input type="button" value="LOGOUT"></a></div>
	<a href="Servlet2?ACTION=HOME"><input type="button" value="Home"></a> | <a href="login.jsp"><input type="button" value="Login"></a> | <a href="signup.jsp"><input type="button" value="Signup"></a> | <a href="Servlet2?ACTION=CART"><input type="button" value="My Cart"></a> | <a href="Servlet2?ACTION=ORDERS"><input type="button" value="My Orders"></a>
	<br/>
	<br/>
	<font color="green"><b><%= log %></b></font>
	<br/>
	<br/>
	<br/>
	<table>
		<tr>
			<td>
			<img src="images//ac.png" height=300 width=300 />
			<br/>
			<form name="formac">
			<a href="Servlet1?ITEM=ac">
				<input type="button" name="btnAC" value="Add to Cart (Rs. 246)" onclick="addedAC()"> 
			</a>	
			</form>
			</td>
			<td>
			
			</td>
			<td>
			<img src="images//raz.jpg" height=300 width=300 />
			<br/>
			<form name="formraz">
			<a href="Servlet1?ITEM=raz">
				<input type="button" name="btnRAZ" value="Add to Cart (Rs. 3660)" onclick="addedRAZ()"> 
			</a>
			</form>
			</td>
			<td>
			
			</td>
			<td>
			<img src="images//bmw.jpg" height=300 width=300 />
			<br/>
			
			<form name="formbmw">
				<a href="Servlet1?ITEM=bmw">
				<input type="button" value="Add to Cart (Rs. 10095)" name="btnBMW" onclick="addedBMW()"> 
				</a>
			</form>
			
			</td>
		</tr>
	</table>
	<br/><br/>
	<table bgcolor="grey" width="100%"><thead><tr><td><br/><br/><br/><center>KP SOFTWORKS LTD.</center><br/><br/><br/></td></tr></thead></table>
	
</body>
</html>