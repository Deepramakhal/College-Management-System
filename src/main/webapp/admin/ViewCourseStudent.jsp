<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Student</title>
    <link rel="stylesheet" href="../css/ViewCourseStudent.css">
</head>
<body>
    <script src="hod2.js"></script>
    <header>
        <div class="header">
            <h1 class="course-name"><%=request.getParameter("batch") %></h1>
            <div class="header-button">
                <form action="../Logout" method="post" id="Log-div">
					<button type="submit" class="logout-button">Logout</button>
				</form>
                <button class="dashboard-btn" onclick="window.location.href='PrincipalDashboard.jsp'">Dashboard</button>
            </div>
        </div>
    </header>
    <main>
        <div class="student-list">
    <h2>Student Details</h2>
    <form action="../admin/SearchStudent" method="post"><input type="text" name="search" placeholder="Search by id,name, or email.." />
    <input type="hidden" name="batch" value="<%=request.getParameter("batch") %>"><input type="hidden" name="output" value="../admin/ViewCourseStudent">
    <button type="submit"><i class="fa fa-search"></i></button></form>
    <div class="table-header">
        <span class="cell id">Profile Picture</span>
        <span class="cell">ID</span>
        <span class="cell">Name</span>
        <span class="cell">Email</span>
        <span class="cell">Phone</span>
        <span class="cell action">Action</span>
    </div>

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
                    <span class="cell">${student.email}</span>
                    <span class="cell">${student.phone}</span>
                    <span class="cell">
                        <button class="remove-btn" onclick="deRegister(${student.id})">Remove</button>
                    </span>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="no-data"><i>No students found for the selected batch or search</i></div>
        </c:otherwise>
    </c:choose>
</div>
    </main>
    <div id="confirmation" style="display: none;">
        <div class="confirmation-popup">
            <p>Are you sure you want to remove this student?</p>
            <span class="close" onclick="closeDeregister(event)">×</span>
            <form action="../admin/DeRegister" method="post"><input type="hidden" value="" name="id" id="dynamicData">
            <input type="hidden" value="STUDENT" name="user" id="dynamicData">
            <button type="submit" class="yes-btn">Confirm</button>
            <button class="no-btn" onclick="closeDeregister(event)">Cancel</button>
            </form>
        </div>
    </div>
  <script >
  function deRegister(id){
		document.getElementById("confirmation").style.display = "flex";
		document.getElementById("dynamicData").value = id;
	}
	function closeDeregister(e){
		e.preventDefault();
		document.getElementById("confirmation").style.display = "none";
		document.getElementById("dynamicData").value = "";
	}</script>
</body>
</html>