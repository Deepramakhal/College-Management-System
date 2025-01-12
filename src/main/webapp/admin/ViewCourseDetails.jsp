<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="java.sql.*" %>
<%@ page import="db.DbConnection" %>
<%@ page import="util.GetCourseId" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=request.getParameter("course") %></title>
    <link rel="stylesheet" href="../css/ViewCourseDetails.css">
</head>
<body>
    <script src="../js/Dashboard.js"></script>
    <header>
        <div class="header">
            <div class="header-center">
                <h1 class="course-name"><%=request.getParameter("course") %></h1>
            </div>
            <div class="header-button">
                <form action="../Logout" method="post" id="Log-div">
					<button type="submit" class="logout-button">Logout</button>
				</form>
                <button class="dashboard-btn" onclick="window.location.href='PrincipalDashboard.jsp'">Dashboard</button>
            </div>
        </div>
    </header>
    <main>  
        <div class="hod-details">
    <h2>Head of Department Details</h2>
    <div class="table-header">
        <span class="cell id">Profile</span>
        <span class="cell">ID</span>
        <span class="cell">Name</span>
        <span class="cell">Email</span>
        <span class="cell">Phone</span>
        <span class="cell action">Action</span>
    </div>
    <c:choose>
    	<c:when test="${not empty hod}">  			
	    	<div class="table-content">
	        <span class="cell">
	           <c:choose><c:when test="${hod.profileImage != null}"><img src="../${hod.profileImage}" alt="${hod.name}" /></c:when>
					    <c:otherwise><img src="../upload/profile.jpg" /></c:otherwise>
					</c:choose>
	        </span>
	        <span class="cell">${hod.id}</span>
	        <span class="cell">${hod.name}</span>
	        <span class="cell">${hod.email}</span>
	        <span class="cell">${hod.phone}</span>
	        <span class="cell">
	            <button class="remove-btn" onclick="deRegister('${hod.id}','HOD')">Remove</button>
	
	        </span>
	    	</div>	
    	</c:when>
    	<c:otherwise><p><i>No HOD registered for this course.</i></p></c:otherwise>
    </c:choose>
</div>

        <div class="faculty-list">
    <h2>Faculty List</h2>
    <div class="table-header">
        <span class="cell id">Profile Picture</span>
        <span class="cell">ID</span>
        <span class="cell">Name</span>
        <span class="cell">Email</span>
        <span class="cell">Phone</span>
        <span class="cell action">Action</span>
    </div>
	<c:choose>
		<c:when test="${not empty faculty}">
			<c:forEach var="faculty" items="${faculty}">
	        <div class="table-content">
	            <span class="cell">
	                <c:choose><c:when test="${faculty.profileImage != null}"><img src="../${faculty.profileImage}"  alt="${faculty.name}"/></c:when>
					    <c:otherwise><img src="../upload/profile.jpg" /></c:otherwise>
					</c:choose>
	              </span>
	              <span class="cell">${faculty.id}</span>
	              <span class="cell">${faculty.name}</span>
	              <span class="cell">${faculty.email}</span>
	               <span class="cell">${faculty.phone}</span>
	              <span class="cell">
	                <button class="remove-btn" onclick="deRegister(${faculty.id},'FACULTY')">Remove</button>
	            </span>
	        		</div>
	    		</c:forEach>
		</c:when><c:otherwise><p><i>No Faculty registered for this course.</i></p></c:otherwise>
	</c:choose>
	</div>
        <div class="batch-list">
            <h2>Batch List</h2>
            <div class="batch-button">
                <c:forEach var="b" items="${batches}">
					<a class="batch-btn" href="../admin/GetStudents?batch=${b.batchId}&output=ViewCourseStudent">${b.startingYear}-${b.endingYear}</a>
                </c:forEach>
            </div>
        </div>
    </main>
   <div id="confirmation" style="display: none;">
        <div class="confirmation-popup">
            <p>Are you sure you want to remove this student?</p>
            <span class="close" onclick="closeDeregister(event)">×</span>
            <form action="../admin/DeRegister" method="post"><input type="hidden" value="" name="id" id="dynamicData">
            <input type="hidden" value="" name="user" id="userType">
            <button type="submit" class="yes-btn">Confirm</button>
            <button class="no-btn" onclick="closeDeregister(event)">Cancel</button>
            </form>
        </div>
    </div>
    <script >
  function deRegister(id,user){
		document.getElementById("confirmation").style.display = "flex";
		document.getElementById("dynamicData").value = id;
		document.getElementById("userType").value = user;
	}
	function closeDeregister(e){
		e.preventDefault();
		document.getElementById("confirmation").style.display = "none";
		document.getElementById("dynamicData").value = "";
		document.getElementById("userType").value = "";
	}</script>
</body>
</html>