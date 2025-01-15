package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import db.DbConnection;
import java.sql.*;
import java.time.*;
@WebServlet("/admin/addBatch")
public class AddBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String course = request.getParameter("course");
		int duration = Integer.parseInt(request.getParameter("duration"));
		String courseId = request.getParameter("courseId");
		String currentYear = Integer.toString(LocalDate.now().getYear()%100);
		int startingYear = LocalDate.now().getYear();
		int endingYear = startingYear + duration;
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("insert into batch values(?,?,?,?) ");
			ps.setString(1,course+currentYear);
			ps.setInt(2, startingYear);
			ps.setInt(3, endingYear);
			ps.setString(4, courseId);
			int i = ps.executeUpdate();
			if(i>0) {
				response.sendRedirect("../admin/HodDashboard.jsp?msg=Batch "+course+currentYear+" added successfully");
			}else {response.getWriter().write("Error adding batch");};
		}catch(Exception e) {response.getWriter().print("Batch already exist or internal server error");}
	}
}
