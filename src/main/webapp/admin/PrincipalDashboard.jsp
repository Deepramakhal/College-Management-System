<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import ="Authentication.Auth" %>
  <%@ page import="models.Admin" %>
  <%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Principal Dashboard</title>
      <link rel="icon" type="image/x-icon" href="../upload/logo.png">
    <link rel="stylesheet" href="../css/principalDashboard.css">
</head>
<body>
<% HttpSession s = request.getSession(false);
Admin admin = (Admin) s.getAttribute("admin");
%>
<% String msg = request.getParameter("msg");
String err = request.getParameter("error"); 
if (err!=null){%><script>alert("<%=err%>"); window.location.href = "PrincipalDashboard.jsp"</script><%}
if (msg!=null){%><script>alert("<%=msg%>"); window.location.href = "PrincipalDashboard.jsp"</script><%}%>
    <header>
            <div class="logo">
                <img src="../upload/logo.png" alt="College Logo" id="College-Logo">
            </div>
            <h1><b>TECHNOVA INSTITUTE</b></h1>

		<form action="../Logout" method="post" id="Log-div">
			<button type="submit" class="logout-button">Logout</button>
		</form>
		<button id="openSidebar" class="open-sidebar-btn">☰</button>
    </header>

    <main>
        <!-- <div id="maindiv"> -->
    <div class="sidebar" id="sidebar">
    <div><div><button id="toggleSidebar" class="toggle-btn">&times;</button></div>
    <div class="profile">       
        <% if(admin.getProfileImage() == null) { %>
            <img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" alt="ProfileImage" class="img">
        <% } else { %>
            <img src="../<%=admin.getProfileImage() %>" alt="ProfileImage" class="img">
        <% } %>
        <p class="name"><%=admin.getName() %></p> 
        <p class="type"><%=admin.getAdminType() %></p>
        <p class="email"><%=admin.getEmail() %></p>
        <button class="btn btn--primary" onclick="window.location.href = '../SendOtp?user=ADMIN&action=Edit profile&output=editProfile.jsp&mail=<%=admin.getEmail()%>'">Edit Profile</button>  
        <button class="btn btn--primary" onclick="window.location.href = '../SendOtp?user=ADMIN&action=Change password&output=editProfile.jsp&mail=<%=admin.getEmail()%>'">Change Password</button>  
    </div></div>
	</div>

    <div class="main-content">
        <h2><U><i><big>Operations</big></i></U></h2>
        <div class="operations">
            <div class="operations-Button">
            <button onclick="storePopupState('Reg-Popup')" id="open-popup">Action</button> 
            <form action="../admin/getApplications" method="post"><button onclick="storePopupState('hod-popup')">Recruit HOD</button></form>    
            <button onclick="storePopupState('Add-Notice')">Add Notice </button>    
            <form action="../admin/getCourses" method="post"><button type="submit"  onclick="storePopupState('Result')"  id="popupBtn">Performances</button></form>       
            <form action="../admin/getApplications" method="post"><button onclick="storePopupState('faculty-popup')">Recruit Faculty</button></form>
            <button onclick="storePopupState('profilePopup')" id="profileButton">Profile</button>
            <form action="../admin/getCourses" method="post"><button type="submit"  onclick="storePopupState('CoursePopup')"  id="popupBtn">Courses</button></form> 
            <form action="../admin/getAdmissions" method="post">
            <button type="submit" onclick="storePopupState('admission-popup')">Admission requests</button></form>       
            <button onclick="window.open('https://www.antiragging.in/affidavit_registration_disclaimer.html', '_blank')">Anti Ragging</button>
            </div>
        </div>
    </div>
</main>

<!-- popup  -->
<!-- Controll  -->
<div id="Reg-Popup">
  <div class="Reg-Enrol_popup">
      <button onclick="closePopup('Reg-Popup')" id="close-regpopup">&times;</button>
      <div><h1>Admission/ Enrollment/Exam Form</h1></div>
      <hr color="black" height="2px">
      <table class="table">
          <thead>
            <tr>
              <th>Operation</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Enable admission ? </td>
              <td><a href="../admin/Controll?action=Admission&controll=true"  class="start-btn">Start</a>
                  <a href="../admin/Controll?action=Admission&controll=false"  class="stop-btn">Stop</a></td>
            </tr>
            <tr>
              <td>Enable  enrollment?</td>
              <td><a href="../admin/Controll?action=Enrollment&controll=true" class="start-btn">Start</a>
                  <a href="../admin/Controll?action=Enrollment&controll=false" class="stop-btn">Stop</a></td>
            </tr>
            <tr>
              <td>Enable exam form?    </td>
              <td><a href="../admin/Controll?action=Exam&controll=true"  class="start-btn">Start</a>
                  <a href="../admin/Controll?action=Exam&controll=false"  class="stop-btn">Stop</a></td>
            </tr>
			<tr>
              <td>Enable Admit download?    </td>
              <td><a href="../admin/Controll?action=Admit&controll=true"  class="start-btn">Start</a>
                  <a href="../admin/Controll?action=Admit&controll=false"  class="stop-btn">Stop</a></td>
            </tr>
          </tbody>
        </table>
  </div>
</div>

<!-- add hod  -->
<div id="hod-popup">
  <div class="hod-request">
      <button onclick="closePopup('hod-popup')" id="close-hod">&times;</button>
      <div><h1>Approve HOD</h1></div>
      <hr color="black" height="2px">
      <table class="table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Phone</th>
              <th>Address</th>
              <th>Qualification</th>
              <th>Experience</th>
              <th>Department</th>
              <th>Resume</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="hod" items="${applications}">
            	<c:if test="${hod.post == 'HOD' and hod.status != 'Approved'}">
				<tr>
					<td>${hod.name}</td>
					<td><a href="mailto:${hod.email}" style="text-decoration: none;color: blue">${hod.email}</a></td>
					<td>${hod.phone}</td>
					<td>${hod.address}</td>
					<td>${hod.qualification}</td>
					<td>${hod.experience}</td>
					<td>${hod.department}</td>
					<td><a href="../${hod.resume}" target="_blank" style="text-decoration: none;color: blue">Resume</a></td>
					<td><form action="../admin/addOfficial" method="post">
						<input type="hidden" name="name" value="${hod.name}">
						<input type="hidden" name="email" value="${hod.email}">
						<input type="hidden" name="post" value="${hod.post}">
						<input type="hidden" name="department" value="${hod.department}">
						<input type="hidden" name="phone" value="${hod.phone}">
						<input type="hidden" name="address" value="${hod.address}">
						<button type="submit" class="approve-btn">Approve</button></form>
					</td>
				</tr>
            	</c:if>
            </c:forEach>
          </tbody>
        </table>
  </div>
</div>
<!-- add notice  -->
<div id="Add-Notice">
  <div class="Add_noticepop">
      <button onclick="closePopup('Add-Notice')" id="close-addpopup">&times;</button>
      <div><h2>Add Notice</h2 ></div>
      <hr color="black" height="2px">
      <div>
          <form action="../admin/addnotice" method="post" enctype="multipart/form-data"> 
            Title: <input type="text" id="title" name="title" required>
              <br> <br>
              Description:<textarea id="link" name="content"></textarea>
                <br><br>
              Select File : <input type="file" id="file" name="file" >
              <br> <br>
              <div id="upload-btn"><button type="submit" id="Upload">Upload</button></div>
          </form>
      </div>
  </div>
</div>

<!-- view Result  -->
<div id="Result">
  <div class="Res_popup">
      <button onclick="closePopup('Result')" id="close-respopup">&times;</button>
      <div><h2>View Result</h2 ></div>
      <hr color="black" height="2px">
      <div>
          <form action="../admin/getPerformance" method="post"> 
              Select Course: <select name="course" id="course">
                  <option value="" disabled selected>Select course</option>
                  <c:forEach var = "c" items = "${courses}">
                  	<option value="${c.name}">${c.name}</option>
                  </c:forEach>
              </select>
              <br> <br>
              Select Batch: <select name="batch" id="batch">
                  <option disabled selected>Select batch</option>
                  <option value="22">2022</option>
                  <option value="23">2023</option>
                  <option value="24">2024</option>
                  <option value="25">2025</option>
              </select>
              <br><br>
              <div id="submitbtn"><button type="submit" id="viewbtn">View</button></div>
          </form>
      
      </div>
  </div>
</div>

<!-- add faculty  -->
<div id="faculty-popup">
  <div class="faculty-request">
      <button onclick="closePopup('faculty-popup')" id="close-faculty">&times;</button>
      <div><h1>Approve Faculty</h1></div>
      <hr color="black" height="2px">
      <table class="table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Phone</th>
			  <th>Address</th>
              <th>Qualification</th>
              <th>Experience</th>
              <th>Department</th>
              <th>Resume</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="faculty" items="${applications}">
            	<c:if test="${faculty.post =='FACULTY' and faculty.status != 'Approved'}">
				<tr>
					<td>${faculty.name}</td>
					<td><a href="mailto:${faculty.email}" style="text-decoration: none;color: blue">${faculty.email}</a></td>
					<td>${faculty.phone}</td>
					<td>${faculty.address}</td>
					<td>${faculty.qualification}</td>
					<td>${faculty.experience}</td>
					<td>${faculty.department}</td>
					<td><a href="../${faculty.resume}" target="_blank" style="text-decoration: none;color: blue">Resume</a></td>
					<td><form action="../admin/addOfficial" method="post">
						<input type="hidden" name="name" value="${faculty.name}">
						<input type="hidden" name="email" value="${faculty.email}">
						<input type="hidden" name="post" value="${faculty.post}">
						<input type="hidden" name="department" value="${faculty.department}">
						<input type="hidden" name="phone" value="${faculty.phone}">
						<input type="hidden" name="address" value="${faculty.address}">
						<button type="submit" class="approve-btn">Approve</button></form>
					</td>
				</tr>
            	</c:if>
            </c:forEach>
          </tbody>
        </table>
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
                  <td>Principal</td>
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

 <!-- course popup  -->
<div id="CoursePopup">
  <div id="CoursePopup-content">              
    <div  onclick="closePopup('CoursePopup')" id="closecoursePopup"><span class="close2">&times;</span></div>
    <div><h1>Enrolled Courses</h1></div>
      <div class="Courses-Button1">
      <c:forEach var="c" items="${courses}">
      <button onclick="window.location.href ='../admin/GetCourseDetails?course=${c.name}&fn=${c.fullName}'">${c.name}</button>
	  </c:forEach>
      <button onclick="storePopupState('AddCourse')">+ Add Course</button>   
    </div>                      
  </div> 
</div>

<!-- add student -->
<div id="admission-popup">
    <div class="admission-request">
        <button onclick="closePopup('admission-popup')" id="close-addmission">&times;</button>
        <div><h1>Admission request</h1></div>
        <hr color="black" height="2px">
        <table class="table">
            <thead>
              <tr>
                <th>Name</th>
                <th>Guardian</th>
                <th>Phone</th>
                <th>Email</th>
                <th>DOB</th>
                <th>Gender</th>
                <th>Course</th>
                <th>Previous School</th>
				<th>Secondary,Higher Secondary(%)</th>
                <th>Last Exam(Percentage if any)</th>
				<th>Documents</th>
				<th>Action</th>				
              </tr>
            </thead>
            <tbody>
             <c:forEach var="a" items="${admissions}">
            <tr>
                <td>${a.name}</td>
                <td>${a.guardian_info}</td>
                <td>${a.phone}</td>
                <td>${a.email}</td>
                <td>${a.dob}</td>
                <td>${a.gender}</td>
				<td>${a.course}</td>
                <td>${a.previous_school}</td>
                <td>${a.secondary}%, ${a.higher_secondary}%</td>
                <td>${a.last_exam_percentage}%</td>
				<td><button id="approveStudent" onclick="openPdfs(['${a.file1}', '${a.file2}', 
				<c:if test="${not empty a.file3}">,'${a.file3}'</c:if>])">View Documents</button></td>
                <td>
    			<form method="post" action="addStudent">
                      <input type="hidden" name="email" value="${a.email}" />
                      <input type="hidden" name="name" value="${a.name}" />
                      <input type="hidden" name="phone" value="${a.phone}" />
                      <input type="hidden" name="address" value="${a.address}" />
                      <input type="hidden" name="gender" value="${a.gender}" />
                      <input type="hidden" name="dob" value="${a.dob}" />
                      <input type="hidden" name="course" value="${a.course}" />
                      <input type="hidden" name="guardian_name" value="${a.guardian_info}" />
                      <input type="hidden" name="file1" value="${a.file1}" />
                      <input type="hidden" name="file2" value="${a.file2}" />
                      <c:if test="${not empty a.file3}">
                          <input type="hidden" name="file3" value="${a.file3}" />
                      </c:if>
                      <button type="submit" id="approveStudent" name="action" value="approve">Approve</button>
                      <button type="submit" id="rejectStudent" name="action" value="reject">Reject</button>
    				</form>
				</td>
            </tr>
        </c:forEach>
            </tbody>
          </table>
    </div>
</div>
<!--  Add course form -->
<div id="AddCourse">
  <div id="AddCourseContent">              
    <div  onclick="closePopup('AddCourse')" id="closecoursePopup"><span class="close2">&times;</span></div>
    <div><h1>Add New Course</h1></div>
      <div class="Courses-Button1">
      <form action="../admin/addCourse" method="post">
  		  <input type="text" name="courseFullName" placeholder="Enter full name of the course">
 		 <input type="text" name="courseName" placeholder="Enter course name abbreviated">
  		<div class="submit-container">
         <input class="short-field" type="text" name="courseId" placeholder="Enter course Id">
         <input class="short-field" type="text" name="duration" placeholder="Enter course duration">
 	   <input type="submit" value="Add course">
 		 </div>
	</form>
    </div>                      
  </div> 
</div>
<footer><div id="foot"><p>Copyrights  &copy; 2024-25 Technova Institute.</p></div></footer>
<script src="../js/Dashboard.js"></script>
</body>
</html>