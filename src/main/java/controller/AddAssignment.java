package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DbConnection;

@WebServlet("/addassignment")
public class AddAssignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String assignment = request.getParameter("content");
		String subject_code = request.getParameter("subject_code");
		String deadline = request.getParameter("deadline");
		String batch_id = request.getParameter("batch_id");
		
		try(Connection con=DbConnection.getConnection())
		{
			String sql="insert into uploaded_assignment values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, batch_id);
			ps.setString(2, subject_code);
			ps.setString(4, assignment);
			ps.setString(3, deadline);
			int i = ps.executeUpdate();
			if(i>0) {
				response.getWriter().write("Assignment added successfully");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
