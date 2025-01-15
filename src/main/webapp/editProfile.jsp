<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dynamic Login Form</title>
    <link rel="stylesheet" href="css/editProfile.css">
    <script src="js/Dashboard.js"></script>
</head>
<body>
<%
    HttpSession s = request.getSession(false);
    String userType = request.getParameter("user");
    String action = request.getParameter("action");
    Admin admin = null;
    Faculty faculty = null;
    Student student = null;
    String profileImage = "upload/profile.jpg"; 
    if (userType.equals("ADMIN")) {
        admin = (Admin) session.getAttribute("admin");
        if (admin != null && admin.getProfileImage() != null) {
            profileImage = admin.getProfileImage();
        }
    } else if (userType.equals("FACULTY")) {
        faculty = (Faculty) session.getAttribute("faculty");
        if (faculty != null && faculty.getProfileImage() != null) {
            profileImage = faculty.getProfileImage();
        }
    } else if (userType.equals("STUDENT")) {
        student = (Student) session.getAttribute("student");
        if (student != null && student.getProfileImage() != null) {
            profileImage = student.getProfileImage();
        }
    } else {response.getWriter().write("Invalid request!");}
  
    if ("Edit profile".equals(action)){
    	%>
    	<div class="login-container">
	    <div class="profile-image">
	        <label for="file-input">
	            <img id="profilePreview" src="<%= profileImage %>" alt="Profile Image">
	        </label>
	        
	    </div>
	
	    <form action="EditProfile" method="post" class="login-form" enctype="multipart/form-data">
	        <% if (admin != null) { %>
	            <h2>Edit Profile</h2>
	            <input type="file" id="file-input" name="profileImage" accept="image/*" style="display: none;">
	            <input type="text" name="id" disabled value="<%= admin.getId() %>" placeholder="Admin ID">
	            <input type="text" name="name" disabled value="<%= admin.getName() %>" placeholder="Name">
	            <input type="email" name="email" value="<%= admin.getEmail() %>" placeholder="Email">
	            <input type="text" name="phone" value="<%= admin.getPhone() %>" placeholder="Phone">
	            <input type="text" name="adminType" disabled value="<%= admin.getAdminType() %>" placeholder="Admin Type">
	            <input type="text" name="address" value="<%= admin.getAddress() %>" placeholder="Address">
	            <input type="hidden" name="id" value="<%=admin.getId() %>">
	            <input type="hidden" name="user" value="ADMIN">
	            <input type="hidden" name="oldImage" value="<%=profileImage%>">
	            <input type="text" name="otp" placeholder="Enter the otp sent to your registered mail">
	        <% } else if (faculty != null) { %>
	            <h2>Edit Profile</h2>
	            <input type="file" id="file-input" name="profileImage" accept="image/*" style="display: none;">
	            <input type="text" name="id" disabled value="<%= faculty.getId() %>" placeholder="Faculty ID">
	            <input type="text" name="name" disabled value="<%= faculty.getName() %>" placeholder="Name">
	            <input type="email" name="email" value="<%= faculty.getEmail() %>" placeholder="Email">
	            <input type="text" name="phone" value="<%= faculty.getPhone() %>" placeholder="Phone">
	            <input type="text" name="courseId" disabled value="<%= faculty.getCourseId() %>" placeholder="Course ID">
	            <input type="text" name="address" value="<%= faculty.getAddress() %>" placeholder="Address">
	            <input type="hidden" name="id" value="<%=faculty.getId() %>">
	            <input type="hidden" name="user" value="FACULTY">
	            <input type="hidden" name="oldImage" value="<%=profileImage%>">
	            <input type="text" name="otp" placeholder="Enter the otp sent to your registered mail">
	        <% } else if (student != null) { %>
	            <h2>Edit Profile</h2>
	            <input type="file" id="file-input" name="profileImage" accept="image/*" style="display: none;">
	            <input type="number" disabled name="id" value="<%= student.getId() %>" placeholder="Student ID">
	            <input type="text" disabled name="name" value="<%= student.getName() %>" placeholder="Name">
	            <input type="email" name="email" value="<%= student.getEmail() %>" placeholder="Email">
	            <input type="text" name="phone" value="<%= student.getPhone() %>" placeholder="Phone">
	            <input type="text" name="guardianName" disabled value="<%= student.getGuardianName() %>" placeholder="Guardian Name">
	            <input type="text" disabled name="gender" value="<%= student.getGender() %>" placeholder="Gender">
	            <input type="date" disabled name="dob" value="<%= student.getDob() %>" placeholder="Date of Birth">
	            <input type="text" disabled name="batchId" value="<%= student.getBatchId() %>" placeholder="Batch ID">
	            <input type="number" disabled name="currentSem" value="<%= student.getCurrentSem() %>" placeholder="Current Semester">
	            <input type="text" name="address" value="<%= student.getAddress() %>" placeholder="Address">
	            <input type="hidden" name="id" value="<%=student.getId() %>">
	            <input type="hidden" name="user" value="STUDENT">
	            <input type="hidden" name="oldImage" value="<%=profileImage%>">
	            <input type="text" name="otp" placeholder="Enter the otp sent to your registered mail">
	        <% } else { %>
	            <p>No user data available!</p>
	        <% } %>
	        <p style="color: red">(* OTP valid for 5 minutes)</p>
	        <button type="submit">Submit</button>
	    </form>
	</div>
    	<% 
    }else if("Change password".equals(action)){
    	%>
    	<fieldset id="changePasswordContainer">
    	<legend>Change Password</legend>
		    <form action="ChangePassword" method="post" id="changePassword">
		        <input type="password" name="oldpw" placeholder="Enter your old password">
		        <input type="password" name="newpw" placeholder="Enter your new password">
		        <input type="text" name="otp" placeholder="Enter the otp sent to your registered mail">
		        <% if (admin != null) { %> 
		            <input type="hidden" name="id" value="<%=admin.getId() %>">
		            <input type="hidden" name="user" value="ADMIN">
		        <% } else if (faculty != null) { %>
		            <input type="hidden" name="id" value="<%=faculty.getId() %>">
		            <input type="hidden" name="user" value="FACULTY">
		        <% } else if (student != null) { %>
		            <input type="hidden" name="id" value="<%=student.getId() %>">
		            <input type="hidden" name="user" value="STUDENT">
		        <% } %>
		        <button id="change" type="submit">Change password</button>
		    </form>
		</fieldset>

    	<% 
    }
%>


<script>
    const fileInput = document.getElementById('file-input');
    const profilePreview = document.getElementById('profilePreview');

    fileInput.addEventListener('change', function () {
        const file = fileInput.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                profilePreview.src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    });
</script>
</body>
</html>
