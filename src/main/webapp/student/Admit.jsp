<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.util.*" %>
    <%@ page import="models.Student"%>
<%@ page import="db.DbConnection"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admit Card</title>
    <link rel="stylesheet" href="../css/Admit.css">
</head>
<body>
<% HttpSession s = request.getSession(false);
	Student student = (Student) s.getAttribute("student");
	try(Connection con = DbConnection.getConnection()){
		PreparedStatement ps = con.prepareStatement("Select isAdmitGenerated from exam_form where student_id=? and semester=?");
		ps.setString(1,Integer.toString(student.getId()));
		ps.setInt(2,student.getCurrentSem());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			if("false".equalsIgnoreCase(rs.getString("isAdmitGenerated"))){
				%>
					<h1>Your exam form is not verified by HOD. Please contact him for further assistance </h1>
					<button>Click here to contact HOD</button>
				<%
			}
			else {
				%>
				<div class="admit-card">
			        <div class="header">
			            <div><img src="https://img.freepik.com/premium-photo/university-college-graduate-campus-logo-design-inspiration-template-design-emblem-school_1029473-590941.jpg?w=996" alt="College Logo" id="College-Logo">
			            <h1>TECHNOVA INSTITUTE</h1></div>
			            <h1>Admit</h1>
			        </div>
			        <div class="student-details">
			             <% if(student.getProfileImage() == null) { %>
				            <img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" alt="ProfileImage" class="img">
				        <% } else { %>
				            <img src="../<%=student.getProfileImage() %>" alt="ProfileImage" class="img">
				        <% } %>
			            <div class="student-info">
			                <p><strong>Name:</strong><%=student.getName() %></p>
			                <p><strong>Roll No:</strong><%=student.getId() %></p>
			                <p><strong>Course:</strong><%=(String)request.getAttribute("course") %></p>
			                <p><strong>Semester:</strong><%=student.getCurrentSem() %></p>
			            </div>
			        </div>
			        <table>
			            <thead>
			                <tr>
			                    <th>Subject Code</th>
			                    <th>Subject Name</th>
			                    <th>Invigilator Signature</th>
			                    <th>Date</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach var="s" items="${subjects}">
			                	<tr>
			                    <td>${s.subject_code }</td>
			                    <td>${s.subject}</td>
			                    <td></td>
			                    <td></td>
			               		 </tr>
			                </c:forEach>
			            </tbody>
			        </table>
			        <div class="footer">
			            <p><strong>Principal Signature:</strong><img src="https://upload.wikimedia.org/wikipedia/commons/b/b5/Nguy%E1%BB%85n_V%C4%83n_B%C3%ACnh_%2C_Nguyen_Van_Binh_signature.png" height="60" width="100"></p>
			        </div>
			        <div class="print-button">
			            <button onclick="window.print()">Print</button>
			        </div>
			    </div>
				<%
			}
		}
	}
%>
    
</body>
</html>