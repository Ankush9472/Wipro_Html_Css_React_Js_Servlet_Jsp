<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>
</head>
<body>
	<%
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		if("admin".equals(user) && "admin123".equals(pass)) {
			out.println("<h2>Welcome, " + user + "!</h2>");
			out.println("<a href='index.jsp'>Go to Home</a>");
		} else {
			out.println("<h2>Invalid credentials!</h2>");
			out.println("<a href='login.jsp'>Try Again</a>");
		}
	%>
</body>
</html>