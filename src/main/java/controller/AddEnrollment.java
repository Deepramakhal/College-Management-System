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
@WebServlet("/student/enrollment")
public class AddEnrollment extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try (Connection con = DbConnection.getConnection()){
			String sql = "update student set current_sem = current_sem+1 where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i > 0) {
				response.sendRedirect("../Logout");
			}
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}