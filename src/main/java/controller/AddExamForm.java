package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DbConnection;
@WebServlet("/student/examFormSubmit")
public class AddExamForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		Student s=(Student)session.getAttribute("student");
		String id = Integer.toString(s.getId());
		int semester= s.getCurrentSem();
		String batch_id= s.getBatchId();
		try(Connection con=DbConnection.getConnection())
		{
			String sql="insert into exam_form values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(2, s.getName());
			ps.setInt(3, semester);
			ps.setString(1, id);
			ps.setString(5, "false");
			ps.setString(4, batch_id);
			int i=ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("../student/StudentDashboard.jsp?msg=Exam form submitted");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}