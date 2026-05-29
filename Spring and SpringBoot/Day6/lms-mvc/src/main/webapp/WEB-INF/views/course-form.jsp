<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course</title>
</head>
<body>

    <h2>Add Course</h2>

    <a href="${pageContext.request.contextPath}/student/list">View Enrollments</a> |
    <a href="${pageContext.request.contextPath}/student/register">Register Student</a> |
    <a href="${pageContext.request.contextPath}/student/enroll">Enroll Student</a>

    <hr>

    <form:form method="post" modelAttribute="course"
               action="${pageContext.request.contextPath}/course/add">

        <p>
            <label>Course Title:</label><br>
            <form:input path="title" required="true"/>
        </p>

        <p>
            <label>Description:</label><br>
            <form:textarea path="description" rows="4" cols="40"/>
        </p>

        <p>
            <button type="submit">Add Course</button>
        </p>

    </form:form>

</body>
</html>