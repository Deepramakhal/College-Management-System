package cms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class Admition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String guardianName=request.getParameter("guardianName");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String pincode=request.getParameter("pincode");
        String schoolName=request.getParameter("schoolName");
        String boardName=request.getParameter("boardName");
        String courseName=request.getParameter("courseName");
        String percentage=request.getParameter("percentage");
		
        address=address+" "+city+" "+state+" "+pincode;
		schoolName=schoolName+" "+"("+boardName+")";
		String name=firstName+" "+lastName;
		try (Connection con=dbConnection.getConnection())
		{
			String sql="insert into admission values(?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(7, email);
			ps.setString(6, phone);
			ps.setString(2, guardianName);
			ps.setString(4, gender);
			ps.setString(3, dob);
			ps.setString(5, address);
			ps.setString(8, schoolName);
			ps.setString(10, courseName);
			ps.setString(9, percentage);
			int i=ps.executeUpdate();
			if(i>0) {
				response.sendRedirect("index.html");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
