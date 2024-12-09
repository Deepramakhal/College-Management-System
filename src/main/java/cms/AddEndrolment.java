package cms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class AddEndrolment extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try (Connection con = dbConnection.getConnection()){
			String sql = "update student set semester= semester+1 where id=?";
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i > 0) {
				response.sendRedirect("student.jsp");
			}
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
