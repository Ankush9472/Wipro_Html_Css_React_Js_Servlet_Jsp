<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Enrollments</title>
</head>
<body>

    <h2>Studnet Panel</h2>

    <a href="${pageContext.request.contextPath}/student/register">Register Student</a> |
    <a href="${pageContext.request.contextPath}/course/add">Add Course</a> |
    <a href="${pageContext.request.contextPath}/student/enroll">Enroll Student</a>

    <hr>

    <c:choose>
        <c:when test="${empty students}">
            <p>No students registered . Click "Register Student" to begin.</p>
        </c:when>
        <c:otherwise>

            <table border="1" cellpadding="8" cellspacing="0">
                <thead>
                    <tr>
                        <th>Stu ID</th>
                        <th>Stu Name</th>
                        <th>Email of student</th>
                        <th>Enrolled Courses</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${students}">
                        <tr>
                            <td>${s.id}</td>
                            <td>${s.name}</td>
                            <td>${s.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty s.courses}">
                                        No courses enrolled
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="c" items="${s.courses}" varStatus="loop">
                                            ${c.title}<c:if test="${!loop.last}">, </c:if>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:otherwise>
    </c:choose>

</body>
</html>