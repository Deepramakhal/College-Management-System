<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Performance of Students</title>
    <link rel="stylesheet" href="../css/performance.css">
</head>
<body>
    <header>
        <div class="header">
            <div class="header-center">
            <c:set var="course" value="${course}"></c:set>
            <c:set var="batch" value="${batch}"></c:set>
                <h1 class="course-name">${course} - ${batch} </h1>
            </div>
            <div class="header-button">
                <form action="../Logout" method="post" id= "Log-div"> 
            		<button type="submit" class="logout-button">Logout</button>
        		</form>
                <button class="dashboard-btn" onclick="window.location.href='PrincipalDashboard.jsp'">Dashboard</button>
            </div>
        </div>
    </header>
    <main><center><h1>Performance</h1></center>
        <div class="student-list">
            <div class="table-header">
                <span class="cell first">ID</span>
                <span class="cell">Name</span>
                <span class="cell">Current CGPA</span>
            </div>
            <c:choose>
				<c:when test="${not empty studentPerformances}">
					<c:forEach var="student" items="${studentPerformances}">
						<div class="table-content">
							<span class="cell">${student.studentId}</span>
							<span class="cell">${student.studentName}</span>
							<span id="cgpa" class="cgpa cell">${student.cgpa}</span>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="no-data">
						No result found for the selected batch.
					</div>
				</c:otherwise>
			</c:choose>
        </div>
    </main>
    <script>document.addEventListener("DOMContentLoaded", function () {
        // Select all elements with the class "cgpa"
        const cgpaElements = document.querySelectorAll(".cgpa");

        // Iterate over each CGPA element
        cgpaElements.forEach((cgpaElement) => {
            // Convert CGPA value to a float for comparison
            const cgpaValue = parseFloat(cgpaElement.innerText);

            // Check CGPA and apply color accordingly
            if (cgpaValue >= 8) {
                cgpaElement.style.color = "green";
                cgpaElement.style.textShadow = "1px 1px 2px green";
            }else if (cgpaValue >= 7 && cgpaValue < 8) {
	            cgpaElement.style.color = "green";
            }
            else if (cgpaValue >= 5 && cgpaValue < 7) {
                cgpaElement.style.color = "orange";
            } else {
                cgpaElement.style.color = "red";
            }
        });
    });

</script>
</body>
</html>