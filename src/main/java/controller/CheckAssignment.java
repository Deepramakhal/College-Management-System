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

@WebServlet("/faculty/chk")
public class CheckAssignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String batch_id = request.getParameter("batch_id");
		String assignment_id = request.getParameter("id");
		String remarks = request.getParameter("remarks");
		String student_id = request.getParameter("student_id");
		if (remarks == null || remarks.trim().isEmpty()) {
		    remarks = "Checked";
		}
		String subject_code = request.getParameter("subject_code");
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("update student_assignment set remarks=?, isChecked=? where batch_id=? and id=? and student_id = ?");
			ps.setString(1, remarks);
			ps.setString(2, "true");
			ps.setString(3, batch_id);
			ps.setString(4, assignment_id);
			ps.setString(5, student_id);
			int i = ps.executeUpdate();
			if(i>0) {
				response.sendRedirect("../faculty/checkAssignment?batch_id=" + batch_id + "&subject_code=" + subject_code);
			}
			else {
				response.getWriter().write("Something went wrong");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
