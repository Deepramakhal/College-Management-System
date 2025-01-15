package controller;
import java.sql.*;
import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/DeRegister")
public class RemoveStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String user = request.getParameter("user");
		try(Connection con = DbConnection.getConnection()){
			String sql = getQuery(user);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			int row = ps.executeUpdate();
			if(row>0) {
				response.sendRedirect("../admin/PrincipalDashboard.jsp?msg="+user+"with the id= "+id+ " removed Successfully");}
			else {
				response.getWriter().write("Error");
			}
		}catch(Exception e) {e.printStackTrace();}
	}
	public String getQuery(String user) {
		if("HOD".equals(user)) {return "Delete from admin where id = ?";}
		else if("FACULTY".equals(user)) {return "Delete from faculty where id = ?";}
		if("STUDENT".equals(user)) {return "Delete from student where id = ?";}
		return null;
	}
}
