<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>  
<%@ page import="db.DbConnection" %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  <link rel="icon" type="image/x-icon" href="upload/logo.png">
    <title>Admission form</title>
    <link rel="stylesheet" href="css/Admission.css">
</head>
<body>
<% try(Connection con=DbConnection.getConnection()){
	PreparedStatement stmt = con.prepareStatement("select isActive from control where action =?");
	stmt.setString(1,"Admission");
	ResultSet rs1 = stmt.executeQuery();
	if (rs1.next() && "false".equals(rs1.getString("isActive"))) {
		%><h1>Admission closed! please wait for next admission season</h1><% 
	}
	else{
		 try(Connection conn  = DbConnection.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select name from course");
			ResultSet rs = ps.executeQuery();
		 %>
		   <div class="form-container">
		    <form action="admission" method="post" enctype="multipart/form-data">
		        <h1>Admission form</h1>
		        <fieldset>
		            <legend>Personal Details</legend>
		             <table>
		              <tr>
		                <td>First Name:*</td>
		                <td><input type="text" id="fname" name="firstName" required></td>
		              </tr>
		              <tr>
		                <td>Middle Name:</td>
		                <td><input type="text" id="mname" name="middlename"></td>
		              </tr>
		              <tr>
		                <td>Last Name:*</td>
		                <td><input type="text" id="lname" name="lastName" required></td>
		              </tr>
		              <tr>
		                <td>Guardian Name:*</td>
		                <td><input type="text" id="fname" name="guardianName" required></td>
		              </tr>
		              <tr>
		                <td>Guardian Contact Number:*</td>
		                <td><input type="number" id="mname" name="guardianPhone" required></td>
		              </tr>
		              <tr>
		                <td>Date of Birth:*</td>
		                <td><input type="date" id="lname" name="dob" required></td>
		              </tr>
		              <tr>
		                <td>Gender:*</td>
		                <td><input type="radio" id="gender" name="gender" value="male" required>Male
		                    <input type="radio" id="gender" name="gender" value="female" required>Female
		                    <input type="radio" id="gender" name="gender" value="other" required>Other</td>
		              </tr>
		              <tr>
		                <td>Address:*</td>
		                <td><input type="text" id="address"  name="address" required></td>
		              </tr>
		              <tr>
		                <td>City:*</td>
		                <td><input type="text" id="city" name="city" required></td>
		              </tr>
		              <tr>
		                <td>State:*</td>
		                <td><input type="text" name="state" required></td>
		              </tr>
		              <tr>
		                <td>Pincode:*</td>
		                <td><input type="number" name="pincode" required></td>
		              </tr>
		              <tr>
		                <td>Email ID:*</td>
		                <td><input type="email" name="email" required></td>
		              </tr>
		              <tr>
		                <td>Mobile Number:*</td>
		                <td><input type="number" name="phone" required></td>
		              </tr>
		             </table>  
		          </fieldset>
		          <fieldset>
		            <legend>Academic Details</legend>
		            <table>
		              <tr>
		                <td>School Name:*</td>
		                <td><input type="text" id="schoolName" name="schoolname" required></td>
		              </tr>
		              <tr>
		                <td>Last qualificaation exam:*</td>
		                <td><input type="text" id="lastexam" name="lastexam" required></td>
		              </tr>
		              <tr>
		                <td>Board Name:</td>
		                <td><input type="text" id="boardName" name="boardName" required></td>
		              </tr>
		              <tr>
		                <td>Course Applying For:</td>
		                <td>
		                  <select id="course" name="course" required>
		                    <option value="" disabled selected>Select Course</option>
		                    <% while(rs.next()) { %><option value="<%=rs.getString("name")%>"><%=rs.getString("name")%></option><% }
							rs.close();} catch(Exception e) {e.printStackTrace();}%>
		                  </select>
		                </td>
		              </tr>
							<tr>
								<td>Percentage in secondary Exam:</td>
								<td><input type="text" class="marks" name="spercentage" required placeholder="Minimum 50% required"></td>
								<td><label for="file-upload1" class="custom-file-label">Upload marksheet*</label> <input type="file" id="file-upload1"
									name="marksheet1" required style="display: none;"></td>
							</tr>
							<tr>
								<td>Percentage in higher secondary Exam:</td>
								<td><input type="text" class="marks" name="hspercentage" required placeholder="Minimum 50% required"></td>
								<td><label for="file-upload2" class="custom-file-label">Upload marksheet*</label> <input type="file" id="file-upload2"
									name="marksheet2" required style="display: none;"></td>
							</tr>
							<tr>
								<td>Percentage in last qualified Exam (if any):</td>
								<td><input type="text" class="marks" name="lpercentage"></td>
								<td><label for="file-upload3" class="custom-file-label">Upload  marksheet</label> <input type="file" id="file-upload3"
									name="marksheet3" style="display: none;"></td>
							</tr>
							<tr>
							</tr>
						</table>
		        	<p style="color: blue; font-weight: bold">(* fields are mandatory. If any last qualified exam is applicable, please upload marksheet.)</p>
		          </fieldset>
		           <div id="button"><button type="submit">Submit</button></div>
		    </form>
		   </div>
	<%}} %>
</body>
<script>
    document.querySelectorAll('input[type="file"]').forEach((fileInput) => {
        fileInput.addEventListener('change', (event) => {
            const input = event.target; 
            const label = input.previousElementSibling; 
            const fileName = input.files[0]?.name || 'No file chosen'; 
            label.textContent = fileName; 
        });
    });
</script>

</html>