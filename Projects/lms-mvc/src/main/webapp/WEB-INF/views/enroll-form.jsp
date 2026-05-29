<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enroll Student</title>
</head>
<body>

    <h2>Enroll Student in Courses</h2>

    <a href="${pageContext.request.contextPath}/student/list">View Enrollments</a> |
    <a href="${pageContext.request.contextPath}/student/register">Register Student</a> |
    <a href="${pageContext.request.contextPath}/course/add">Add Course</a>

    <hr>

    <c:if test="${empty students}">
        <p>No students available. Please register a student first.</p>
    </c:if>

    <c:if test="${empty courses}">
        <p>No courses available. Please add a course first.</p>
    </c:if>

    <c:if test="${not empty students and not empty courses}">

        <form method="post" action="${pageContext.request.contextPath}/student/enroll">

            <p>
                <label>Select Student:</label><br>
                <select name="studentId" required>
                    <option value="">-- Choose a student --</option>
                    <c:forEach var="s" items="${students}">
                        <option value="${s.id}">${s.name} (${s.email})</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>Select Courses:</label><br>
                <c:forEach var="c" items="${courses}">
                    <input type="checkbox" name="courseIds" value="${c.id}" id="course-${c.id}"/>
                    <label for="course-${c.id}">${c.title}</label><br>
                </c:forEach>
            </p>

            <p>
                <button type="submit">Enroll Student</button>
            </p>

        </form>

    </c:if>

</body>
</html>