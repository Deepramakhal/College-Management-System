package cms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Controll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String sql="update control set isActive= ? where action= ?";
		try(Connection con = dbConnection.getConnection()) 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "true");
			ps.setString(2, action);
			int i=ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("principal.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String sql="update control set isActive= ? where action= ?";
		try(Connection con = dbConnection.getConnection()) 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "false");
			ps.setString(2, action);
			int i=ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("principal.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
