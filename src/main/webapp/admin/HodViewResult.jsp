<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/HodViewResult.css">
</head>
<body>
<% String course= request.getParameter("course"); %>
    <header>
        <div><button id="logout" onclick="window.location.href='../Logout'">Logout</button></div>
        <h1><%=request.getParameter("course") %></h1>
        <div><button id="dashboard" onclick="window.location.href='HodDashboard.jsp'"><a href="HodDashboard.jsp">Dashboard</a></button></div>
    </header>
    <table>
        <tr>
            <th>Student ID</th>
            <th>Student Name</th>
            <th>Semester</th>
            <th>Result</th>
            <th>Remark</th>
        </tr>
        <!-- Check if results is empty -->
        <c:choose>
            <c:when test="${empty results}">
                <tr>
                    <td colspan="5" style="text-align: center;"><i>No data available</i></td>
                </tr>
            </c:when>
            <c:otherwise>
                <!-- Iterate through the results if not empty -->
                <c:forEach var="r" items="${results}">
                    <tr>
                        <td>${r.student_id}</td>
                        <td>${r.student_name}</td>
                        <td>${r.semester}</td>
                        <td>
						    <a href="../student/Result.jsp?sgpa=${r.sgpa}&sem=${r.semester}&studentId=${r.student_id}&course=<%=course%>&dated=${r.timestamp}" 
						       style="color:blue;">
						        ${r.sgpa}
						    </a>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${r.sgpa >= 9}">Outstanding</c:when>
                                <c:when test="${r.sgpa >= 8}">Excellent</c:when>
                                <c:when test="${r.sgpa >= 7}">Very Good</c:when>
                                <c:when test="${r.sgpa >= 6}">Good</c:when>
                                <c:when test="${r.sgpa >= 5}">Average</c:when>
                                <c:when test="${r.sgpa >= 4}">Very Poor</c:when>
                                <c:otherwise>Failed</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
</body>
</html>
