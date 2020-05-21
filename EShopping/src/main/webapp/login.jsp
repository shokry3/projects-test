<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<table align="center">
		<tr>
			<td><h1>Liogin</h1></td>
		</tr>
		<tr>
			<td style="font-style: italic; color: red;">${SPRING_SECURITY_LAST_EXCEPTION.message}</td>
		</tr>
	</table>
	<form id="loginForm" action="login" method="post">
		<table align="center">
			<tr>
				<td><label>Username: </label></td>
				<td><input type="text" name="username" id="username" /></td>
			</tr>
			<tr>
				<td><label>Password:</label></td>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="left"><input type="submit" name="submit"
					value="submit" /></td>
			</tr>
			<tr></tr>
			<!-- <tr>
				<td></td>
				<td><a href="home.jsp">Home</a></td>
			</tr> -->
		</table>
	</form>
</body>
</html>