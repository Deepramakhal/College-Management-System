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
import db.DbConnection;
import java.sql.*;

@WebServlet("/faculty/AddResource")
@MultipartConfig
public class AddResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part file = request.getPart("file");
		String title = request.getParameter("title");
		String batch_id = request.getParameter("batch");
		String subject_code = request.getParameter("subject_code");
		String faculty = request.getParameter("faculty");
		String filePath = FileUploadUtil.saveFile(file);
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("insert into resource(faculty_name,title,content,subject_code,batch_id) values(?,?,?,?,?)");
			ps.setString(1, faculty);
			ps.setString(2,title);
			ps.setString(3, filePath);
			ps.setString(4, subject_code);
			ps.setString(5,batch_id);
			int i = ps.executeUpdate();
			if(i>0) {
				response.sendRedirect("../faculty/FacultyDashboard.jsp?msg=File uploaded successfully");
			}
			else {
				response.sendRedirect("../faculty/FacultyDashboard.jsp?msg=File uplaod failed");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
