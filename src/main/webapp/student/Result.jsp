<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="db.DbConnection" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="models.Student" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Result</title>
    <link rel="stylesheet" href="../css/Result.css">
</head>
<body>
<%
    HttpSession s = request.getSession(false);
    models.Student student;

    // Check if 'studentId' is provided in the query string (admin access)
    String studentIdParam = request.getParameter("studentId");
    if (studentIdParam != null) {
        int studentId = Integer.parseInt(studentIdParam);

        // Load the student object from the database using the ID
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM student WHERE id = ?");
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setProfileImage(rs.getString("profile_image"));
                // Add additional fields if necessary
            } else {
                throw new Exception("Student not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error loading student data: " + e.getMessage() + "</p>");
            return;
        }
    } else {
        // Default to current student session for regular student access
        student = (models.Student) session.getAttribute("student");
    }

    int semester = Integer.parseInt(request.getParameter("sem"));
    double sgpa = Double.parseDouble(request.getParameter("sgpa"));
    String course = request.getParameter("course");
    String dated = request.getParameter("dated");
%>
<header class="headercontainer">
    <div class="header">
        <img src="../upload/logo.png" 
             alt="Logo" class="logo">
        <div class="header-center">
            <h1 class="college-name">TECHNOVA INSTITUTE</h1>
            <p><%= dated %></p>
        </div>
    </div>
</header>
<main class="maincontainer">
    <div class="result-form">
        <h2>Result</h2>
        <div class="result-card">
            <div class="result-info">
                <div class="result-head">
                    <span class="head-info">Name</span>
                    <span class="head-info">Roll</span>
                    <span class="head-info">Course</span>
                    <span class="head-info">Semester</span>
                </div>
                <div class="result-details">
                    <span class=""><%= student.getName() %></span>
                    <span class=""><%= student.getId() %></span>
                    <span class=""><%= course %></span>
                    <span class=""><%= semester %></span>
                </div>
                <img src="../<%= student.getProfileImage() %>" alt="image" class="image">
            </div>
            <div class="subject-info">
                <div class="result-header">
                    <span class="cell subject-code">Subject Code</span>
                    <span class="cell subject-name">Subject Name</span>
                    <span class="cell subject-marks">Marks</span>
                </div>
                <% 
                try (Connection con = DbConnection.getConnection()) {
                    PreparedStatement ps = con.prepareStatement(
                        "SELECT m.subject_code, s.name, m.marks " +
                        "FROM marks m, subject s " +
                        "WHERE m.subject_code = s.code AND m.student_id = ? AND m.semester = ?"
                    );
                    ps.setInt(1, student.getId());
                    ps.setInt(2, semester);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                %>
                    <div class="subject-details">
                        <span class="cell subject-code"><%= rs.getString("subject_code") %></span>
                        <span class="cell subject-name"><%= rs.getString("name") %></span>
                        <span class="cell subject-marks"><%= rs.getInt("marks") %></span>
                    </div>
                <% 
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<p>Error loading subject data: " + e.getMessage() + "</p>");
                } 
                %>
            </div>
            <div class="result-footer">
                <div class="total-marks">
                    <span class="marks">SGPA:</span>
                    <span class="marks"><%= sgpa %></span>
                </div>
                <div class="signature">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Nguy%E1%BB%85n_V%C4%83n_B%C3%ACnh_%2C_Nguyen_Van_Binh_signature.png/800px-Nguy%E1%BB%85n_V%C4%83n_B%C3%ACnh_%2C_Nguyen_Van_Binh_signature.png?20150602110015" 
                         alt="signature">
                    <h3>Signature of Principal</h3>
                </div>
            </div>
            <div class="result-actions">
                <button class="print-btn" onclick="window.print()">Print</button>
            </div>
        </div>
    </div>
</main>
</body>
</html>
