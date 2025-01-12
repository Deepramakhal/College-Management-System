package controller;
import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
@WebServlet("/admin/GenerateAdmit")
public class GenerateAdmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String student_id = request.getParameter("student_id");
		String semester = request.getParameter("sem");
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("update exam_form set isAdmitGenerated='true' where student_id=? and semester=?");
			ps.setString(1, student_id);
			ps.setString(2, semester);
			int row = ps.executeUpdate();
			if(row>0) {
				response.sendRedirect("../admin/HodDashboard.jsp?msg=Admit Card Generated");
			}
			else {
				response.getWriter().write("Error");
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
