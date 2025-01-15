package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.FileUploadUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DbConnection;

@WebServlet("/application")
@MultipartConfig
public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String name= request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String qualification=request.getParameter("qualification");
		String previousWorkingPlace=request.getParameter("previousWorkingPlace");
		int yearsOfexperience=Integer.parseInt(request.getParameter("experience"));
		String post=request.getParameter("post");
		String department=request.getParameter("department");
		String phone=request.getParameter("phone");
		Part filePart=request.getPart("file");
		String filePath=FileUploadUtil.saveFile(filePart); 
		String experience  = previousWorkingPlace+" "+yearsOfexperience;
		try(Connection con=DbConnection.getConnection())
		{
			String sql="insert into application(name,email,phone,qualification,experience,department,post,address,resume) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, qualification);
			ps.setString(5, experience);
			ps.setString(6, department);
			ps.setString(7, post);
			ps.setString(8, address);
			ps.setString(9, filePath);
			int i=ps.executeUpdate();
			if(i>0) {
				response.sendRedirect("index.jsp?msg=Application submitted successfully");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}