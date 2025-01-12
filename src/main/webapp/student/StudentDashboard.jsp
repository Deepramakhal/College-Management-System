<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="models.Student"%>
<%@ page import="db.DbConnection"%>
<% String course = null;
	HttpSession s = request.getSession(false);
	Student student = (Student)session.getAttribute("student");
	int id = student.getId();
	try(Connection con = DbConnection.getConnection()){
		String sql = "select course.name from batch join course on batch.course_id = course.id where batch.name =?";
		PreparedStatement ps = con.prepareStatement(sql);	
		ps.setString(1, student.getBatchId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
            course = rs.getString("name");
        } else {
            course = "No course found";
        }
		} catch (SQLException e) {
	        e.printStackTrace();
        }
%>
<% Map<String, Boolean> actionStatus = new HashMap<>();
try (Connection con = DbConnection.getConnection()) {
    String sql = "SELECT action, isActive FROM control";
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        actionStatus.put(rs.getString("action"), rs.getBoolean("isActive"));
    }
} catch (SQLException e) {
    e.printStackTrace();
}
request.setAttribute("actionStatus", actionStatus);
 %>
 <% Map<String, String> SubmittedAssignment= new HashMap<>();
try (Connection con = DbConnection.getConnection()) {
    String sql = "SELECT remarks, id FROM student_assignment";
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        SubmittedAssignment.put(rs.getString("remarks"), Integer.toString(rs.getInt("id")));
    }
} catch (SQLException e) {
    e.printStackTrace();
}
request.setAttribute("submittedAssignment", SubmittedAssignment);
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student</title>
      <link rel="icon" type="image/x-icon" href="../upload/logo.png">
    <link rel="stylesheet" href="../css/StudentDashboard.css">
	<% String msg = request.getParameter("msg");
String err = request.getParameter("error"); 
if (err!=null){%><script>alert("<%=err%>"); window.location.href = "StudentDashboard.jsp"</script><%}
if (msg!=null){%><script>alert("<%=msg%>"); window.location.href = "StudentDashboard.jsp"</script><%}%>
</head>
<body>
    <header>
        <div class="logo">
            <img src="../upload/logo.png" alt="College Logo" id="College-Logo">
        </div>
        <h1><b>TECHNOVA INSTITUTE</b></h1>
        <form action="../Logout" method="post" id= "Log-div"> 
            <button type="submit" class="logout-button">Logout</button>
        </form>
        <button id="openSidebar" class="open-sidebar-btn">☰</button>
</header>

<main>
    <div class="sidebar" id="sidebar">
		<div>
		<div> <button id="toggleSidebar" class="toggle-btn">&times;</button></div>
		    <div class="profile">       
        <% if(student.getProfileImage() == null) { %>
            <img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" alt="ProfileImage" class="img">
        <% } else { %>
            <img src="../<%=student.getProfileImage() %>" alt="ProfileImage" class="img">
        <% } %>
        <p class="name"><%=student.getName() %></p> 
        <p class="type">Student of</p>
        <p class="id"><%=course %> Department</p>
        <p class="email"><%=student.getEmail() %></p>
        <button class="btn btn--primary" onclick="window.location.href = '../SendOtp?user=STUDENT&action=Edit profile&output=editProfile.jsp&mail=<%=student.getEmail()%>'">Edit Profile</button>  
        <button class="btn btn--primary" onclick="window.location.href = '../SendOtp?user=STUDENT&action=Change password&output=editProfile.jsp&mail=<%=student.getEmail()%>'">Change Password</button></div>  
    </div>
	</div>
    <div class="main-content">
        <h2>Operations</h2>
       <div class="operations">
            <div class="operations-Button">
                 <form action="../student/getSubjects" method="get">
                 <input type="hidden" name="output" value="../student/StudentDashboard">
				    <input type="hidden" name="semester" value="<%=student.getCurrentSem()+1%>">
				    <button onclick="storePopupState('Enroll')" 
				            <c:if test="${actionStatus['Enrollment'] == false}">Disabled style="cursor:not-allowed;"</c:if>style="cursor:pointer;">
				        Enrollment Form
				    </button>
				</form>    
                <form action="../student/internalMarks" method="post"><input type="hidden" name="student_id" value="<%=student.getId()%>">
                <input type="hidden" name="semester" value="<%=student.getCurrentSem()%>">
                <button type="submit" onclick="storePopupState('Internal')" >Internal Marks</button></form>
                <form action="../student/getResource" method="post"><input type="hidden" name="batch" value="<%=student.getBatchId() %>">
                <input type="hidden" name="current_sem" value="<%=student.getCurrentSem()%>">
                <input type="hidden" name="stdId" value="<%=student.getId()%>">
                <button onclick="storePopupState('Resource-popup')">View Resource</button></form>
                <form action="../student/getSubjects" method="get"><input type="hidden" name="output" value="../student/StudentDashboard">
				    <input type="hidden" name="semester" value="<%=student.getCurrentSem()%>">
				    <button onclick="storePopupState('examform')" 
				            <c:if test="${actionStatus['Exam'] == false}">disabled style="cursor:not-allowed;"</c:if>style="cursor:pointer;">
				        Exam Form
				    </button>
				</form>   
               <form action="../student/getSubjects" method="get"><input type="hidden" name="semester" value="<%=student.getCurrentSem()%>">
               <input type="hidden" name="output" value="../student/Admit">
               <input type="hidden" name="course" value="<%=course%>">
                <button onclick="window.location.href='Admit.jsp" 
				        <c:if test="${actionStatus['Admit'] == false}">disabled style="cursor:not-allowed;"</c:if>style="cursor:pointer;">
				    Admit Card
				</button></form>
                <form action="../student/getResults" method="post"><button onclick="storePopupState('FinalResult')">Result</button></form>    
                <form action="../student/GetAssignments" method="post"><button onclick="storePopupState('Assignment-popup')">Assignments</button></form>
                <button onclick="storePopupState('profilePopup')" id="profileButton">Student Basic Details</button>
             <button onclick="window.open('https://www.antiragging.in/affidavit_registration_disclaimer.html', '_blank')">Anti Ragging</a></button>
            </div>
        </div>
    </div>
</main> 
<!-- Internal Marks -->
<div id="Internal">
    <div class="internal-popup">
        <button onclick="closePopup('Internal')" id="close-internalmarks">&times;</button>
        <div><h1>Internal Marks</h1></div>
        <hr color="black" height="2px">
        <table class="table">
            <thead>
              <tr>
              <td>Exam type</td>
              <c:out value="${mark}"></c:out>
                <c:forEach var="mark" items="${marksList}">
                        <th>${mark.subject_code}</th>
                  </c:forEach>
              </tr>
            </thead>
            <tbody>
              <tr>
               <td>A1</td>
               <c:forEach var="mark" items="${marksList}">
                    <td>${mark.a1}</td>
                </c:forEach>
              </tr>
              <tr>
                <td>A2</td>
                   <c:forEach var="mark" items="${marksList}">
                        <td>${mark.a2}</td>
                  </c:forEach>
              </tr>
              <tr>
               <td>A3</td>
                  <c:forEach var="mark" items="${marksList}">
                        <td>${mark.a3}</td>
                  </c:forEach>
              </tr>
            </tbody>
          </table>
    </div>
  </div>
  <!-- result  -->
  <div id="FinalResult">
    <div class="FinalResult-popup">
        <button onclick="closePopup('FinalResult')" id="close-finalresult">&times;</button>
        <div><h1>Result</h1></div>
        <hr color="black" height="2px">
        <table class="table">
            <thead>
              <tr>
                <th>Semester</th>
                <th>Publication Date</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:choose>
              	<c:when test="${not empty result}">
              		<c:forEach var="r" items="${result }">
              			<tr>
              				<td>${r.semester}</td>
              				<td>${r.timestamp }</td>
              				<td><a href="Result.jsp?sgpa=${r.sgpa}&sem=${r.semester}&course=<%=course%>&dated=${r.timestamp}" style="color:blue;">${r.sgpa}</a></td>
              			</tr>
              		</c:forEach> 
              	</c:when>
              	<c:otherwise>
              	<td colspan="3"><i>You will get result after your first semester examination!</i>
              	</c:otherwise>
              </c:choose>
            </tbody>
          </table>
    </div>
  </div>
  
 <!-- View / upload assignment -->
<div id="Assignment-popup">
  <div id="Assignment">
    <div><span onclick="closePopup('Assignment-popup')" id="close-assignment">&times;</span></div>
    <h1><%=student.getBatchId()+ "Assignments"%></h1>
    <table id="view-assignment">
      <tr>
        <th>Subject Code</th>
        <th>PDF Link</th>
        <th>Date</th>
        <th>Upload File</th>
        <th>Submit</th>
        <th>Submission Status</th>
        <th>Remarks</th>
      </tr>
      <c:forEach var="a" items="${todo}">
    <form action="../student/uploadAssignment" method="post" enctype="multipart/form-data">
        <tr>
            <td class="section-label">${a.subject_code}</td>
            <td>
                <a href="../${a.content}" target="_blank" style="text-decoration:none;color:blue">${a.topic}</a>
            </td>
            <td>${a.deadline}</td>
            <td>
                <div class="file-upload">
                    <input type="file" name="file" accept=".pdf" required 
                        <c:if test="${a.isDisabled && a.submission_remarks != 'Submittedreupload'}">
                            disabled="disabled"
                        </c:if>
                    >
                </div>
            </td>
            <td>
                <button type="submit"
                    <c:if test="${a.isDisabled && a.submission_remarks != 'Submittedreupload'}">
                        disabled="disabled"
                    </c:if>
                >Submit</button>
            </td>
            <td>
                <c:choose>
                    <c:when test="${a.submission_remarks != null}">
                        Submitted
                    </c:when>
                    <c:otherwise>
                        Not Submitted
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${a.submission_remarks != null && a.submission_remarks != 'Submittednull'}">
                        ${fn:replace(a.submission_remarks, "Submitted", "")}
                    </c:when>
                    <c:when test="${ a.submission_remarks == 'Submittednull'}">
                        Unchecked
                    </c:when>
                    <c:otherwise>
                        N/A
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <!-- Hidden fields to pass data -->
        <input type="hidden" name="id" value="${a.uploaded_id}">
        <input type="hidden" name="subject_code" value="${a.subject_code}">
    </form>
</c:forEach>

    </table>
  </div>
</div>



  <!-- basic details -->
  <div id="profilePopup">
    <div id="profilePopup-content">
        <div  onclick="closePopup('profilePopup')" id="closeProfilePopup"><span class="close1">&times;</span></div>
        <h2>User Details</h2><br>
        <div>
            <table id="profile-table">
                <tr>    
                    <th>Name</th>
                    <td><%= student.getName() %></td>
                </tr>
                <tr>    
                    <th>Role</th>
                    <td>Student</td>
                </tr>
                <tr>    
                    <th>Email</th>
                    <td><%= student.getEmail() %></td>
                </tr>
                <tr>    
                    <th>Phone</th>
                    <td><%= student.getPhone() %></td>
                </tr>
                <tr>    
                    <th>Id</th>
                    <td><%= student.getId() %></td>
                </tr>
                <tr>    
                    <th>Address:</th>
                    <td><%=student.getAddress() %></td>
                </tr>
                <tr>
                	<th>Current Semester</th>
                	<td><%=student.getCurrentSem() %></td></tr>
               	<tr>    
                    <th>Batch</th>
                    <td><%= student.getBatchId()%></td>
                </tr>
            </table>
        </div>
    </div>
  </div>
<!-- enrollment? -->

<div id="Enroll">
    <div class="Enroll-popup">
        <button onclick="closePopup('Enroll')" id="close-enrollment">&times;</button>
        <div><h1>Enrollment Form</h1></div>
        <hr color="black" height="2px">
		<form action="../student/enrollment" method="post">
        <table class="table3"> 
               <tr>
                    <th>Name</th>
                    <td><%=student.getName() %></td>
                </tr>
                <tr>
                    <th>Roll no</th>
                    <td><%=student.getId() %></td>
                </tr>
                <tr>
                    <th>Course Name</th>
                    <td><%=course %></td>
                </tr>
                <tr>
                	<th>Next Semester</th>
                	<td><%=student.getCurrentSem()+1 %></td>
                </tr>
        </table> 
        <table class="table2">
            <div><h3><u>Subjects to be enrolled</u></h3></div> 
                <tr>
                    <th>Subject Code</th>
                    <th>Subject Name</th>
                </tr>
              <c:forEach var="s" items="${subjects}">
              		<tr>
              			<td>${s.subject_code}</td>
              			<td>${s.subject }</td>
              		</tr>
              	</c:forEach>
          </table>
		<br>
          <div class="submitenroll">
          	<input type="hidden" name="id" value="<%=student.getId()%>">
            <input type="checkbox" name="exam" id="exam" required>I connfirm the above details
            <button type="submit" class="submit-btn">Submit</button>
        </div></form>
          (*Note: You will be logged out after submission.)

    </div>
  </div>

<!-- examform -->
<div id="examform">
    <div class="examform-popup">
        <button onclick="closePopup('examform')" id="close-examform">&times;</button>
        <div><h1>Exam Form fillup</h1></div>
        <hr color="black" height="2px">
	<form action="../student/examFormSubmit" method="post">
        <table class="table3">
                <tr>
                    <th>Name</th>
                    <td><%=student.getName() %></td>
                </tr>
                <tr>
                    <th>Roll no</th>
                    <td><%=student.getId() %></td>
                </tr>
                <tr>
                    <th>Course Name</th>
                    <td><%=course %></td>
                </tr>
                <tr>
                	<th>Semester</th>
                	<td><%=student.getCurrentSem() %></td>
                </tr>
        </table> 
        <table class="table2">
            <div><h3><u>Enrolled Subjects</u></h3></div> 
   
                <tr>
                    <th>Subject Code</th>
                    <th>Subject Name</th>
                </tr>
              	<c:forEach var="s" items="${subjects}">
              		<tr>
              			<td>${s.subject_code}</td>
              			<td>${s.subject }</td>
              		</tr>
              	</c:forEach>
          </table>
			<br>
          <div class="submitenroll">
            <input type="checkbox" name="exam" id="exam" required>I connfirm the above details
            <button class="submit-btn">Submit</button>
        </div></form>

    </div>
  </div>
<div id="Resource-popup">
  <div id="Resourcepopup-content">
   <div><span onclick="closePopup('Resource-popup')" id="close-resource">&times;</span></div>
    <h1>Resources</h1>
    <table id="view-resource">
    <tr>
      <th>Subject code</th>
      <th>Pdf link</th>
      <th>Faculty</th>
    </tr>
   <c:set var="subject" value="${crrSubjects}" />
		<c:forEach var="res" items="${resource}">
		    <c:if test="${fn:contains(subject, res.subject_code)}">
		        <tr>
		            <td>${res.subject_code}</td>
		            <td><a href="../${res.content}">${res.title}</a></td>
		            <td>${res.faculty_name}</td>
		        </tr>
		    </c:if>
		</c:forEach>
   </table>
  </div>
</div>
<script src="../js/Dashboard.js"></script>
<footer><div id="foot"><p>Copyrights  &copy; 2024-25 Technova Institute.</p></div></footer>
</body>
</html>