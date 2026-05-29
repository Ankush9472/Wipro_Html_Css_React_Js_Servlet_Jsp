<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.Cookie" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Login</h2>

<%
String user = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie c : cookies) {
        if (c.getName().equals("user")) {
            user = c.getValue();
        }
    }
}
%>

<form action="login" method="post">
    Username: <input type="text" name="username" value="<%= user %>"><br><br> 
     Password: <input type="password" name="password"><br><br>
     
       <input type="checkbox" name="remember"> Remember Me<br><br>   
    <input type="submit" value="Login">
</form>

</body>
</html>