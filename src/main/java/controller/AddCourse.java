package controller;
import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
@WebServlet("/admin/addCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String courseName = request.getParameter("courseName");
	   int duration = Integer.parseInt(request.getParameter("duration"));
	   String courseId = request.getParameter("courseId");
	   String courseFullName = request.getParameter("courseFullName");
	   try(Connection con = DbConnection.getConnection()) {
		   String sql = "insert into course(id,name,full_name,hod_id,duration) values(?,?,?,?,?)";
		   PreparedStatement ps = con.prepareStatement(sql);
		   ps.setString(1, courseId);
		   ps.setString(2, courseName);
		   ps.setString(3, courseFullName);
		   ps.setString(4, courseName+"HOD");
		   ps.setInt(5, duration);
		   int i = ps.executeUpdate();
		   if(i>0) {response.sendRedirect("../admin/PrincipalDashboard.jsp?msg=Course "+courseName+" added successfully");}
		   else {response.getWriter().write("Internal server error");}
	   }catch(Exception e) {e.printStackTrace();}}
   }