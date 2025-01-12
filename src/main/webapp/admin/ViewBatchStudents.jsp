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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="../js/Dashboard.js"></script>
</head>
<body>
    <header>
        <div class="header"><h1 class="course-name"><%=request.getParameter("batch") %></h1>
            <div class="header-button">
                <form action="../Logout" method="post" id="Log-div">
                    <button type="submit" class="logout-button">Logout</button>
                </form>
                <button class="dashboard-btn" onclick="window.location.href='HodDashboard.jsp'">Dashboard</button>
            </div>
        </div>
    </header>
    <main>
        <div class="student-list">
            <h2>Student Details</h2>
            <form action="../admin/SearchStudent" method="post"><input type="text" name="search" placeholder="Search by id,name, or email.." />
		    <input type="hidden" name="batch" value="<%=request.getParameter("batch") %>"><input type="hidden" name="output" value="../admin/ViewBatchStudents">
		    <button type="submit"><i class="fa fa-search"></i></button></form>
            <div class="table-header">
                <span class="cell id">Profile Picture</span>
                <span class="cell">ID</span>
                <span class="cell">Name</span>
                <span class="cell email">Email</span>
                <span class="cell phone">Phone</span>
                <span class="cell action">Semester</span>
                <span class="cell phone">Guardian</span>
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
	                                <span class="cell">${student.currentSem}</span>
	                                <span class="cell">${student.guardianName}</span>
                                </span>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="no-data"><i> No students found for the selected batch.</i></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
</body>
</html>
