<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
		<h1>LOGIN</h1>
		<hr>
		<%
		String msg=(String)request.getAttribute("MSG");
		if(msg==null)
			msg="You are not Signed in";
		%>
		<div align="right"> <font color="blue"><%= msg %></font> <a href="Servlet2?ACTION=SIGNOUT"><input type="button" value="LOGOUT"></a></div>
		<a href="Servlet2?ACTION=HOME"><input type="button" value="Home"></a> | <a href="login.jsp"><input type="button" value="Login"></a> | <a href="signup.jsp"><input type="button" value="Signup"></a> | <a href="Servlet2?ACTION=CART"><input type="button" value="My Cart"></a> | <a href="Servlet2?ACTION=ORDERS"><input type="button" value="My Orders"></a>
		<br/><br/><br/><br/>
		<div align="center">
		<form class = "form" action = "Servlet2?ACTION=LOGIN" method = "post">
		Username: <input type="text" class = "input" name = "userID">
		<br/><br/>
		Password: <input type="password" class = "text" name = "password">
		<br/><br/>
		<input type="submit" class = "button" value = "Login">
		</form>
		<br/><br/>Don't have an account? <a href="signup.jsp">Register now</a> 
		</div>

</body>
</html>