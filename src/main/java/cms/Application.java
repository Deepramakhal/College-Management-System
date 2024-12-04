package cms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.FileUpload;

import java.io.IOException;
import java.sql.Connection;


public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name= request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String qualification=request.getParameter("qualification");
		String post=request.getParameter("post");
		String department=request.getParameter("department");
		String phone=request.getParameter("phone");
		Part filePart=request.getPart("file");
		String filePath=FileUpload.saveFile(filePart); 
	
		try(Connection con=dbConnection.getConnection())
		{
			String sql="insert into application values(?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(7, address);
			ps.setString(4, qualification);
			ps.setString(6, post);
			ps.setString(5, department);
			ps.setString(3, phone);
			ps.setString(8, filePath);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
