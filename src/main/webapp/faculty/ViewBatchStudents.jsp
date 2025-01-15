<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student</title>
    <link rel="stylesheet" href="../css/ViewBatchStudent.css">
</head>
<body>
    <script src="hod2.js"></script>
    <header>
        <div class="header">
            <div class="header-center">
                <h1 class="course-name"><%=request.getParameter("batch") %></h1>
            </div>
            <div class="header-button">
                <form action="../Logout" method="post" id="Log-div">
                    <button type="submit" class="logout-button">Logout</button>
                </form>
                <button class="dashboard-btn" onclick="window.location.href='FacultyDashboard.jsp'">Dashboard</button>
            </div>
        </div>
    </header>
    <main>
        <div class="student-list">
            <h2>Student Details</h2>
            <div class="table-header">
                <span class="cell id">Profile Picture</span>
                <span class="cell">ID</span>
                <span class="cell">Name</span>
                <span class="cell email">Email</span>
                <span class="cell phone">Phone</span>
            </div>
            <div class="table-content">
                <c:choose>
                    <c:when test="${not empty students}">
                        <c:forEach var="student" items="${students}">
                            <div class="table-content">
                                <span class="cell">
                                    <c:choose><c:when test="${student.profileImage != null}"><img src="../${student.profileImage}" /></c:when>
									    <c:otherwise><img src="../upload/profile.jpg" /></c:otherwise>
									</c:choose>
                                </span>
                                <span class="cell">${student.id}</span>
                                <span class="cell">${student.name}</span>
                                <span class="cell email"><a href="mailto:${student.email}">${student.email}</a></span>
                                <span class="cell phone">${student.phone}</span>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="no-data">
                            No students found for the selected batch.
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
    <div id="confirmation" style="display: none;">
        <div class="confirmation-popup">
            <p>Are you sure you want to remove this student?</p>
            <span class="close" onclick="closeConfirmation(event)">×</span>
            <button class="yes-btn">Confirm</button>
            <button class="no-btn" onclick="closeConfirmation(event)">Cancel</button>
        </div>
    </div>
</body>
</html>
