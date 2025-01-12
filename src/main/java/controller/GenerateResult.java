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

@WebServlet("/admin/GenerateResult")
public class GenerateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int semester = Integer.parseInt(request.getParameter("semester"));
		String batch_name = request.getParameter("batch_name");
		String[] sgpa = request.getParameterValues("sgpa");
		String[] studentIds = request.getParameterValues("student_id");
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("Insert into result(student_id,semester,sgpa,batch_id,timestamp) values(?,?,?,?,CURRENT_TIMESTAMP)");
			for(int i=0;i<studentIds.length;i++) {
				ps.setString(1, studentIds[i]);
				ps.setFloat(3, Float.parseFloat(sgpa[i]));
				ps.setInt(2, semester);
				ps.setString(4, batch_name);
				int row = ps.executeUpdate();
				if(row>0) {
					 PreparedStatement ps1 = con.prepareStatement("Update marks set batch_id=? where batch_id=? and semester=?");
					 ps1.setString(1, batch_name+"(APPROVED)");
					 ps1.setString(2, batch_name);
					 ps1.setInt(3, semester);
					 ps1.executeUpdate();
					 response.sendRedirect("../admin/HodDashboard.jsp?msg=Result Generated");
					}
				else {
					response.getWriter().write("Error");
				}
			}
			response.sendRedirect("../admin/HodDashboard.jsp");
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
