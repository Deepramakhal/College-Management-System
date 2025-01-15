package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import db.DbConnection;
@WebServlet("/admin/SearchStudent")
public class SearchStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String batch = request.getParameter("batch");
		String searchQuery = request.getParameter("search");
		String output = request.getParameter("output");
		ArrayList<Student> student = new ArrayList<>();
		try(Connection con = DbConnection.getConnection()){
			 String sql = "SELECT * FROM student WHERE batch_id = ? AND (id LIKE ? OR email LIKE ? OR name LIKE ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(2, "%"+searchQuery+"%");
			ps.setString(3, "%"+searchQuery+"%");
			ps.setString(4, "%"+searchQuery+"%");
			ps.setString(1, batch);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				student.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone"), rs.getString("guardian_name"), rs.getString("gender"), 
						rs.getString("dob"), rs.getString("batch_id"), 
						rs.getInt("current_sem"), rs.getString("profile_image"), rs.getString("address"))); 
				}
			request.setAttribute("students", student);
			request.getRequestDispatcher(output+".jsp?batch="+batch).forward(request, response);
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
