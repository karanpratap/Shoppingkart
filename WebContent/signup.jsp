<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>SIGNUP</h1>
<hr>
	<%
		String msg=(String)request.getAttribute("MSG");
		if(msg==null)
			msg="You are not Signed in";
		String log=(String)request.getAttribute("LOG");
		if(log==null){
			log=" ";
		}
	%>
	<div align="right"> <font color="blue"><%= msg %></font> <a href="Servlet2?ACTION=SIGNOUT"><input type="button" value="LOGOUT"></a></div>
	<a href="Servlet2?ACTION=HOME"><input type="button" value="Home"></a> | <a href="login.jsp"><input type="button" value="Login"></a> | <a href="signup.jsp"><input type="button" value="Signup"></a> | <a href="Servlet2?ACTION=CART"><input type="button" value="My Cart"></a> | <a href="Servlet2?ACTION=ORDERS"><input type="button" value="My Orders"></a>
	<br/><br/>
	<font color=red><%= log %></font>
	<br/><br/>
	<div align="center">
	<form class = "form" action = "Servlet2?ACTION=SIGNUP" method = "post">
		Username: <input type="text" class = "input" name = "userID">
		<br/><br/>
		Password: <input type="password" class = "text" name = "password">
		<br/><br/>
		<input type="submit" class = "button" value = "Signup">
	</form>
	<br/><br/>Already have an account? <a href="login.jsp">Sign in</a> 
	</div>
</body>
</html>