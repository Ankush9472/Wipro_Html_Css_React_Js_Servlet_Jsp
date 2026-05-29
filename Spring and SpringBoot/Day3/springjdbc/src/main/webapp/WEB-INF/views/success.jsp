<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Success</title>
<style>
    body {
        background-color: skyblue;
        text-align: center;
    }
</style>
</head>
<body>

<h2>${message}</h2>
Id : <h1>${data.id}</h1>
User Name : <h1>${data.name}</h1>
Email Id : <h1>${data.email}</h1>
<a href="register">Add Another User</a>

</body>
</html>