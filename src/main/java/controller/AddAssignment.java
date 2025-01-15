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

@WebServlet("/faculty/addassignment")
@MultipartConfig
public class AddAssignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part assignment = request.getPart("content");
		String subject_code = request.getParameter("subject_code");
		String deadline = request.getParameter("deadline");
		String batch_id = request.getParameter("batch_id");
		String topic = request.getParameter("topic");
		String assignmentPath = FileUploadUtil.saveFile(assignment);
		try(Connection con=DbConnection.getConnection())
		{   String sql="insert into uploaded_assignment(batch_id,subject_code,deadline,content,topic) values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, batch_id);
			ps.setString(2, subject_code);
			ps.setString(3, deadline);
			ps.setString(4, assignmentPath);
			ps.setString(5, topic);
			int i = ps.executeUpdate();
			if(i>0) {
				request.setAttribute("msg", "Assignment Uploaded Successfully");
				response.sendRedirect("../faculty/FacultyDashboard.jsp?msg=Assignment Uploaded Successfully");}}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
