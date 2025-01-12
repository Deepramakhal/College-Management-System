<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%@ page import ="Authentication.Auth" %>
    <%@ page import="models.Admin" %>
    <%@ page import="jakarta.servlet.http.HttpSession" %>
	<%@ page import="java.sql.*" %>
	<%@ page import="java.sql.ResultSet" %>
    <%@ page import="db.DbConnection" %>
<% String course = null;
	String courseId = null;
	String duration = null;
    	HttpSession s = request.getSession(false);
    	Admin admin = (Admin)session.getAttribute("admin");
    	try(Connection con = DbConnection.getConnection()){
    		String sql = "SELECT c.name as course, c.id, c.duration FROM course c, admin a WHERE c.hod_id = ?; ";
    		PreparedStatement ps = con.prepareStatement(sql);	
    		ps.setString(1, admin.getId());
    		ResultSet rs = ps.executeQuery();
    		if (rs.next()) {
                course = rs.getString("course");
                courseId = rs.getString("id");
                duration = Integer.toString(rs.getInt("duration"));
            } else {
                course = "No course found"; // If no course is found
            }} catch (SQLException e) {
	            e.printStackTrace();
            }
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hod Dashboard</title>
    <link rel="stylesheet" href="../css/hodDashboard.css">
      <link rel="icon" type="image/x-icon" href="../upload/logo.png">
<% String msg = request.getParameter("msg");
String err = request.getParameter("error"); 
if (err!=null){%><script>alert("<%=err%>"); window.location.href = "HodDashboard.jsp"</script><%}
if (msg!=null){%><script>alert("<%=msg%>"); window.location.href = "HodDashboard.jsp"</script><%}%>
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
		  	<div><button id="toggleSidebar" class="toggle-btn">&times;</button></div>
		    <div class="profile">       
		        <% if(admin.getProfileImage() == null) { %>
		            <img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" alt="ProfileImage" class="img">
		        <% } else { %>
		            <img src="../<%=admin.getProfileImage() %>" alt="ProfileImage" class="img">
		        <% } %>
		        <p class="name"><%=admin.getName() %></p> 
		        <p class="type">Head of the DepartMent</p>
		        <p class="id"><%=course %></p>
		        <p class="email"><%=admin.getEmail() %></p>
		        <button class="btn btn--primary" onclick="window.location.href = '../SendOtp?user=ADMIN&action=Edit profile&output=editProfile.jsp&mail=<%=admin.getEmail()%>'">Edit Profile</button>  
		        <button class="btn btn--primary" onclick="window.location.href = '../SendOtp?user=ADMIN&action=Change password&output=editProfile.jsp&mail=<%=admin.getEmail()%>'">Change Password</button> 
		  </div> 
    </div>
</div>
    <div class="main-content">
        <h2><U><i><big>Operations</big></i></U></h2>
        <div class="courses">
             <div class="Courses-Button">
                <form action="../admin/examForms" method="get"><input type="hidden" name="course" value="<%=course %>">
                <button type="submit" onclick="storePopupState('Reg-Popup')" id="open-popup">Exam forms</button></form> 
                <form action="../admin/GetBatches" method="post"><input type="hidden" name="course" value="<%=course %>"><input type="hidden" name="output" value="../admin/HodDashboard">
                <button type="submit" onclick="storePopupState('ResultPopup')">Marks Approval</button></form>
                <form action="../admin/GetBatches" method="post"><input type="hidden" name="course" value="<%=course %>"><input type="hidden" name="output" value="../admin/HodDashboard">
                <button type="submit" onclick="storePopupState('ViewResult')">View Results</button></form>          
                <button type="submit" onclick="storePopupState('profilePopup')" id="profileButton">Profile</button></form>
                <form action="../admin/GetBatches" method="post"><input type="hidden" name="course" value="<%=course %>"><input type="hidden" name="output" value="../admin/HodDashboard">
				<button type="submit" onclick="storePopupState('CoursePopup')" class="popupBtn">View Batches</button></form>     
                <button onclick="window.open('https://www.antiragging.in/affidavit_registration_disclaimer.html', '_blank')">Anti Ragging</button>
            </div>
        </div>
    </div>
</main>
<!-- popup exam Registration  -->
<div id="Reg-Popup">
    <div class="Reg-Enrol_popup">
        <button onclick="closePopup('Reg-Popup')" id="close-regpopup">&times;</button>
        <div><h1>Exam Forms</h1></div>
        <hr color="black" height="2px">
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Roll</th>
                    <th>SEM</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="form" items="${forms}">
                    <tr>
                        <td>${form.name}</td>
                        <td>${form.student_id}</td>
                        <td>${form.semester}</td>
                        <td>
                            <c:choose>
                                <c:when test="${form.isAdmitGenerated == 'true'}">
                                   <p style="color: blue; opacity: 0.8;font-size:smaller"> Admit Generated</p>
                                </c:when>
                                <c:otherwise><form action="../admin/GenerateAdmit" method="post">
                                <input type="hidden" name="student_id" value="${form.student_id}">
                                <input type="hidden" name="sem" value="${form.semester}">
                                <button type="submit" class="start-btn">Generate Admit</button>
                                </form>    
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- confirm Result -->
<div id="ResultPopup">
  <div id="Resultpopup-content">              
    <div onclick="closePopup('ResultPopup')" id="closeresult" class="close4">&times;</div>
    <div><h1>Result Confirmation</h1></div>
      <div class="Result-Button1">
          <c:forEach var="b" items="${batches}">
          <button onclick="window.location.href ='../admin/ApproveResult?batch=${b.batchId}'">${b.startingYear}-${b.endingYear}</button>
          </c:forEach>   
    </div>                      
  </div> 
</div>

<!-- view result  --> 
<div id="ViewResult">
  <div id="Resultpopup-content">              
    <div onclick="closePopup('ViewResult')" id="closeresult" class="close4">&times;</div>
    <div><h1>View Results</h1></div>
      <div class="Result-Button1">
          <c:forEach var="b" items="${batches}">
          <button onclick="window.location.href ='../admin/getResult?batch=${b.batchId}&course=<%=course %>'">${b.startingYear}-${b.endingYear}</button>
          </c:forEach>   
    </div>                      
  </div> 
</div>

<!-- view profile  -->
<div id="profilePopup">
  <div id="profilePopup-content">
      <div  onclick="closePopup('profilePopup')" id="closeProfilePopup"><span class="close1">&times;</span></div>
      <h2>User Details</h2><br>
      <div>
          <table id="profile-table">
              <tr>    
                  <th>Name:</th>
                  <td><%=admin.getName() %></td>
              </tr>
              <tr>    
                  <th>Role</th>
                  <td>Head of the Department</td>
              </tr>
              <tr>    
                  <th>Email:</th>
                  <td><%=admin.getEmail() %></td>
              </tr>
              <tr>    
                  <th>Phone:</th>
                  <td><%=admin.getPhone() %></td>
              </tr>
              <tr>    
                  <th>Id:</th>
                  <td><%=admin.getId()%></td>
              </tr>
              <tr>    
                  <th>Address:</th>
                  <td><%=admin.getAddress()%></td>
              </tr>
          </table>
      </div>
  </div>
</div>
<!-- batch popup -->
<div id="CoursePopup">
  <div id="CoursePopup-content">              
    <div onclick="closePopup('CoursePopup')" id="closecoursePopup"><span class="close2">&times;</span></div>
    <div><h1>Batches</h1></div>
      <div class="Courses-Button1">
          <c:forEach var="b" items="${batches}">
          <button onclick="window.location.href ='../admin/GetStudents?batch=${b.batchId}&output=../admin/ViewBatchStudents'">${b.startingYear}-${b.endingYear}</button>
          </c:forEach>  
          <a style="color:blue;" href="../admin/addBatch?course=<%=course%>&courseId=<%=courseId%>&duration=<%=duration%>">+Add New Batch</a>  
    </div>                      
  </div> 
</div>      
<script src="../js/Dashboard.js"></script>
    
<footer><div id="foot"><p>Copyrights  &copy; 2024-25 Technova Institute.</p></div></footer>

</body>
</html>