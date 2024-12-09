package cms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class AddExamForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter(request.getParameter("id"));
		int semester=Integer.parseInt(request.getParameter("semester"));
		String batch_id=request.getParameter("batch_id");
		
		try(Connection con=dbConnection.getConnection())
		{
			String sql="insert into exam values(?,?,?,?)";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(2, semester);
			ps.setString(3, batch_id);
			ps.setString(1, id);
			ps.setString(4, "false");
			int i=ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("student.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
