package cms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


public class AddNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String link = request.getParameter("link");
		
		try(Connection con=dbConnection.getConnection())
		{
			String sql="insert into notice values(?,?)";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, link);
			int i=ps.executeUpdate();
			{
				if(i>0)
				{
					response.sendRedirect("principal.jsp");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
