<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Student</title>
</head>
<body>

    <h2>Register New Student</h2>

    <a href="${pageContext.request.contextPath}/student/list">View Enrollments</a> |
    <a href="${pageContext.request.contextPath}/course/add">Add Course</a> |
    <a href="${pageContext.request.contextPath}/student/enroll">Enroll Student</a>

    <hr>

    <form:form method="post" modelAttribute="student"
               action="${pageContext.request.contextPath}/student/register">

        <p>
            <label>Name:</label><br>
            <form:input path="name" required="true"/>
        </p>

        <p>
            <label>Email:</label><br>
            <form:input path="email" type="email" required="true"/>
        </p>

        <p>
            <button type="submit">Register Student</button>
        </p>

    </form:form>

</body>
</html>