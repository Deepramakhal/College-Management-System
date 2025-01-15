package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.Student;
import util.FileUploadUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DbConnection;
@WebServlet("/student/uploadAssignment")
@MultipartConfig
public class UploadAssignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Student student=(Student)session.getAttribute("student");
		Part filePart = request.getPart("file");
		String filepath = FileUploadUtil.saveFile(filePart);
		String subjectCode=request.getParameter("subject_code");
		String id=request.getParameter("id");
		try(Connection con=DbConnection.getConnection())
		{
			String sql="insert into student_assignment(student_id,content,batch_id,subject_code,id) values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,Integer.toString(student.getId()));
			ps.setString(3, student.getBatchId());
			ps.setString(4, subjectCode);
			ps.setString(2, filepath);
			ps.setString(5, id);
			int i=ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("../student/StudentDashboard.jsp?msg=Assignment submitted");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}