<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Page</title>
</head>
<body>
	<h2>Contact Us</h2>
	<form action="contact.jsp" method="post">
		Name: <input type="text" name="name" /><br/><br/>
		Email: <input type="email" name="email" /><br/><br/>
		Message: <textarea name="message" rows="4" cols="30"></textarea><br/><br/>
		<input type="submit" value="Send" />
	</form>
	
	<%
		String name = request.getParameter("name");
		if(name != null) {
			out.println("<h3>Thank you, " + name + "! Your message has been received.</h3>");
		}
	%>
</body>
</html>