<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>  
<%@ page import="db.DbConnection" %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add HOD</title>  <link rel="icon" type="image/x-icon" href="upload/logo.png">
    <link rel="stylesheet" href="css/Application.css">
</head>
<body>
<% String post = request.getParameter("post");
try(Connection con=DbConnection.getConnection()){
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
    <div>
        <h1>Registration Form</h1>
        <form action="application" method="post" enctype="multipart/form-data" class="form-container">
        <table>
            
            <tr>
                 <td>Name: <span class="required">*</span></td>
                 <td><input type="text" name="name" placeholder="Enter your name"></td>
            </tr>
            <tr>
                <td>Address: <span class="required">*</span></td>
                <td><input type="text" name="address" placeholder="Enter your address"></td>
           </tr>
            <tr>
                <td>Qualification: <span class="required">*</span></td>
                <td><input type="text" name="qualification"  placeholder="Enter last qualification"></td>
            </tr>
            <tr>
                <td>Post:<span class="required">*</span></td>
                <td><select name="post" id="" margin="10px">
                    <option value="<%=post%>" selected><%=post %></option>
                </select></td>
            </tr>
            <tr>
                <td>Experience(in years):<span class="required">*</span></td>
                <td><input type="number" name="experience" placeholder="Enter your experience"></td>
            </tr>
            <tr>
                <td>Previous Workplace:<span class="required">*</span></td> 
                <td><input type="text" name="previousWorkingPlace" placeholder="Enter your previous company"></td>
            </tr>
      
            <tr>
                <td>Department: <span class="required">*</span></td>
                <td><select name="department" id="" required>
                	<option value="" disabled selected>Select department</option>
                    <% while(rs.next()) { %><option value="<%=rs.getString("name")%>"><%=rs.getString("name")%></option><% }
							rs.close();} catch(Exception e) {e.printStackTrace();}%>
                </select>
              </td>
            </tr>
          
            <tr>
                <td>Email: <span class="required">*</span></td>
                <td><input type="email" name="email" placeholder="Enter email" required></td>
            </tr>
          
            <tr>
                <td>Contact Number: <span class="required">*</span></td>
                <td><input type="tel" name="phone" placeholder="Enter contact number" required></td>
            </tr>
            
            <tr>
                <td>Upload resume: <span class="required">*</span></td>
                <td><input type="file" name="file" required></td>
            </tr>
        </table>
      
        <div id="btn"><button type="submit">Submit</button></div>
        </form>
    </div>
    <%}} %>
</body>
</html>