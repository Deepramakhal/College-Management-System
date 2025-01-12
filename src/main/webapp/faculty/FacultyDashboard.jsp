<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page import="Authentication.Auth"%>
<%@ page import="models.Faculty"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="java.sql.*"%>
<%@ page import="db.DbConnection"%>
<%String course = null;
	HttpSession s = request.getSession(false);
	Faculty faculty = (Faculty) session.getAttribute("faculty");
	try (Connection con = DbConnection.getConnection()) {
		String sql = "Select name from course where id = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, faculty.getCourseId());
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="../upload/logo.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Faculty Dashboard</title>
    <link rel="stylesheet" href="../css/facultyDashboard.css">
    <% String msg = request.getParameter("msg");
String err = request.getParameter("error"); 
if (err!=null){%><script>alert("<%=err%>"); window.location.href = "FacultyDashboard.jsp"</script><%}
if (msg!=null){%><script>alert("<%=msg%>"); window.location.href = "FacultyDashboard.jsp"</script><%}%> 
</head>
<body>
    <header>
        <div class="logo">
            <img src="https://img.freepik.com/premium-photo/university-college-graduate-campus-logo-design-inspiration-template-design-emblem-school_1029473-590941.jpg?w=996" alt="College Logo" id="College-Logo">
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
        <% if(faculty.getProfileImage() == null) { %>
            <img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" alt="ProfileImage" class="img">
        <% } else { %>
            <img src="../<%=faculty.getProfileImage() %>" alt="ProfileImage" class="img">
        <% } %>
        <p class="name"><%=faculty.getName() %></p> 
        <p class="type">Faculty of</p>
        <p class="id"><%=course %> Department</p>
        <p class="email"><%=faculty.getEmail() %></p>
        <button class="btn btn--primary" onclick="window.location.href = '../SendOtp?user=FACULTY&action=Edit profile&output=editProfile.jsp&mail=<%=faculty.getEmail()%>'">Edit Profile</button>  
        <button class="btn btn--primary" onclick="window.location.href = '../SendOtp?user=FACULTY&action=Change password&output=editProfile.jsp&mail=<%=faculty.getEmail()%>'">Change Password</button>		  
    </div></div>
	</div>

    <div class="main-content">
        <h2><U><i><big>Operations</big></i></U></h2>
        <div class="operations">
            <div class="operations-Button">
                <form action="../faculty/GetBatches" method="post"><input type="hidden" name="course" value="<%=course %>">
                <input type="hidden" name="output" value="../faculty/FacultyDashboard">
				<button type="submit" onclick="storePopupState('CoursePopup')" class="popupBtn">View Batch</button></form>  
                <form action="../faculty/GetBatches" method="post"><input type="hidden" name="course" value="<%=course %>">
                <input type="hidden" name="output" value="../faculty/FacultyDashboard">
				<button type="submit" onclick="storePopupState('Assignment')">Upload Assignment</button> </form> 
				<form action="../faculty/GetBatches" method="post"><input type="hidden" name="course" value="<%=course %>">
                <input type="hidden" name="output" value="../faculty/FacultyDashboard">
                <button onclick="storePopupState('Marks')">Upload Marks</button></form>
                <form action="../faculty/GetBatches" method="post"><input type="hidden" name="course" value="<%=course %>">
                <input type="hidden" name="output" value="../faculty/FacultyDashboard">
                <button onclick="storePopupState('Resources')">Upload Resources</button>  </form> 
                <form action="../faculty/GetBatches" method="post"><input type="hidden" name="course" value="<%=course %>">
                <input type="hidden" name="output" value="../faculty/FacultyDashboard">        
                <button onclick="storePopupState('CheckAss')">Check Assignment</button></form>
                <button onclick="storePopupState('profilePopup')" id="profileButton">View Profile</button>    
            </div>
        </div>
        </div>
    </div>
 </main>

 <!-- batch popup -->
 <div id="CoursePopup">
    <div id="CoursePopup-content">              
      <div onclick="closePopup('CoursePopup')" id="closecoursePopup"><span class="close2">&times;</span></div>
      <div><h1>Batches</h1></div>
        <div class="Courses-Button1">
           <c:forEach var="b" items="${batches}">
          <button onclick="window.location.href ='../faculty/GetStudents?batch=${b.batchId}&output=../faculty/ViewBatchStudents'">${b.startingYear}-${b.endingYear}</button>
          </c:forEach> 
      </div>                      
    </div> 
</div>

<!-- upload Assignment -->
<div id="Assignment">
    <div class="Assignment-Content">
        <button onclick="closePopup('Assignment')" id="close-asspopup">&times;</button>
        <div><h1>Assignment</h1></div>
        <hr>
        <form action="../faculty/addassignment" method="post" enctype="multipart/form-data">
            <table class="table">
                <tbody>
                    <tr>
                        <td>TOPIC</td>
                        <td><input type="text" name="topic" required></td>
                    </tr>
                    <tr>
                        <td>Batch Id</td>
                        <td><select name="batch_id">
                        <option disabled selected>Select Batch</option>
                        <c:forEach var="b" items="${batches}">
				          <option value="${b.batchId}">${b.startingYear}-${b.endingYear}</option>
				          </c:forEach> 
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Subject Code</td>
                        <td><input type="text" name="subject_code" required></td>
                    </tr>
                    <tr>
                        <td>Material</td>
                        <td>
                            <label for="file-upload" class="custom-file-label" style="cursor:pointer;">Choose a file</label>
                            <input type="file" id="file-upload" name="content" style="display: none;" onchange="showFileName()">
                        </td>
                    </tr>
                </tbody>
	            </table><br>
                <div><label for="deadline" style="font-size:larger; margine-left:10px;">Last date of submission</label>
                <input type="date" name="deadline" required style="padding:10px;margin-right:20px;">
                <button type="submit" class="Ass-btn" style="">Upload</button></div>
        </form>
    </div>
</div>

    <!-- upload Marks -->
<div id="Marks">
    <div id="CoursePopup-content">              
      <div onclick="closePopup('Marks')" id="closecoursePopup"><span class="close2">&times;</span></div>
      <div><h1>Batches</h1></div>
        <div class="Courses-Button1">
           <c:forEach var="b" items="${batches}">
         	<button onclick="window.location.href ='../faculty/GetStudents?batch=${b.batchId}&output=../faculty/UploadMarks'">${b.startingYear}-${b.endingYear}</button>
          </c:forEach> 
      </div>                      
    </div> 
</div>

<!-- upload Resources -->
<div id="Resources">
    <div class="Resources-Content">
        <button onclick="closePopup('Resources')" id="close-resourcespopup">&times;</button>
        <div><h1>Upload Resources</h1></div>
        <hr color="black" height="2px">
        <form action="../faculty/AddResource" method="post" enctype="multipart/form-data">
        <table class="table">
            <tbody>
                <tr>
                <td>Title </td>
                <td><input type="text" name="title"></td>
                </td>
            </tr>
            <tr>
                <td>Select Batch </td>
                <td><select name="batch" id="batch" required>
                <c:forEach var="b" items="${batches}">
         		 <option value="${b.batchId }">${b.startingYear}-${b.endingYear}</option>
          		</c:forEach> 
              </select></td>
            </tr>
            <tr>
                  <td>Papercode </td>
                  <td>
                    <input type = "text" name="subject_code" required>
                    </td>      
            </tr>
            <tr>
                <td>Metirial </td>
                <td><input type="file" name="file" required></td>
              </tr>
            </tbody>         
        </table> <input type="hidden" name="faculty" value="<%=faculty.getName()%>">      
        <br>
        <button class="Ass-btn" type="submit">Upload</button></form>
        </div>
    </div>

    <!-- check assignment -->
    <div id="CheckAss">
        <div class="CheckAss-Content">
            <button onclick="closePopup('CheckAss')" id="close-CheckAsspopup">&times;</button>
            <div><h2>Check Assignment</h2 ></div>
            <hr color="black" height="2px">
            <div>
                <form action="../faculty/checkAssignment" method="get"> 
                    Select Batch: <select name="batch_id" id="batch">
                        <c:forEach var="b" items="${batches}">
				          <option value="${b.batchId}">${b.startingYear}-${b.endingYear}</option>
				         </c:forEach> 
                    </select>
                    <br> <br>
                    <label for="subject_code">Enter subject code:</label>
                    <input type="text" name="subject_code" id="subject_code">
                    <br> <br>
                    <div id="submitbtn"><button type="submit" id="viewbtn">Check</button></div>
                </form>           
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
                    <td><%=faculty.getName() %></td>
                </tr>
                <tr>    
                    <th>Role</th>
                    <td>Faculty</td>
                </tr>
                <tr>    
                  <th>Department:</th> 
                  <td><%=course %></td>
                </tr>
                <tr>    
                    <th>Email:</th>
                    <td><%=faculty.getEmail() %></td>
                </tr>
                <tr>    
                    <th>Id:</th>
                    <td><%=faculty.getId() %></td>
                </tr>
                <tr>    
                    <th>Address:</th>
                    <td><%=faculty.getAddress() %></td>
                </tr>
            </table>
        </div>
    </div>
  </div>
<script src="../js/Dashboard.js" ></script>  
<footer>
    <div id="foot"><p>&copy; 2024 TECHNOVA INSTITUTE. All rights reserved.</p></div>
  </footer>
</body>
</html>