<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Register User</title>
<style>
    body {
        background-color: skyblue;
        text-align: center;
    }
</style>
</head>
<body>

<h2>Register User</h2>
<form action="register" method="post">
    Id : <input type="text" name="id"><br/>
    Name: <input type="text" name="name"/><br/>
    Email: <input type="text" name="email"/><br/>
    <input type="submit" value="Register"/>
</form>

</body>
</html>