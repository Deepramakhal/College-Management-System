<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/checkAssignment.css">
</head>
<body>
  <header>
    <div><button id="logout" onclick="window.location.href='../Logout'">Logout</button></div>
    <h1>Check Assignment</h1>
    <div><button id="dashboard" onclick="window.location.href='FacultyDashboard.jsp'">Dashboard</button></div>
  </header>
  <p style="color:blue; text-align:center"><i><b>[*to enable resubmission please enter 'reupload' as remarks]</b></i></p>
  <form action="../faculty/chk" method="post">
    <table>
        <tr>
          <th>SL no.</th>
          <th>Student Id</th>
          <th>Assignment link</th>
          <th>Remarks</th>
          <th>Check</th>
        </tr>
        <c:choose>
		    <c:when test="${not empty assignments}">
		        <c:forEach var="a" items="${assignments}">
		            <tr>
		                <td>${a.id}</td>
		                <td>${a.student_id}</td>   
		                <td><a href="../${a.content}" target="_blank">View</a></td>
		                <td><input type="text" name="remarks"></td>
		                <td><button type="submit">&#10003;</button></td>
		                <input type="hidden" name="id" value="${a.id}">
		                <input type="hidden" name="subject_code" value="${a.subject_code}">
		                <input type="hidden" name="student_id" value="${a.student_id}">
		            </tr>
		        </c:forEach>
		    </c:when>
		    <c:otherwise>
		        <tr><td colspan="5"><i>No assignment found</i></td></tr>
		    </c:otherwise>
		</c:choose>

       </table><input type="hidden" name="batch_id" value="<%=request.getParameter("batch")%>">
       </form>
</body>
</html>