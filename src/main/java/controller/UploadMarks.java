package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import db.DbConnection;

@WebServlet("/faculty/UploadMarks")
public class UploadMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject_code = request.getParameter("subject_code");
		String batch_id = request.getParameter("batch_id");
		int semester = Integer.parseInt(request.getParameter("semester"));
		String exam_type = request.getParameter("exam_type");
		String[] studentIds = request.getParameterValues("student_id");
		String[] marks = request.getParameterValues("marks");
		int rs;
		PreparedStatement ps;
		try(Connection con = DbConnection.getConnection()){
			if(exam_type.equals("a1")||exam_type.equals("a2")||exam_type.equals("a3")) {
				ps = con.prepareStatement("insert into internal_marks(student_id,subject_code,semester,exam_name,marks,batch_id)  values(?,?,?,?,?,?)");
				for(int i=0;i<studentIds.length;i++) {
					ps.setString(1, studentIds[i]);
					ps.setString(2, subject_code);
					ps.setInt(3,semester);
					ps.setString(4, exam_type);
					ps.setString(5, marks[i]);
					ps.setString(6, batch_id);
					rs = ps.executeUpdate();
					if(rs>0) {
						response.sendRedirect("../faculty/FacultyDashboard.jsp?msg=Marks added successfully");
					}
					else {
						response.getWriter().write("Error");
					}
				}
			}
			else if(exam_type.equals("final")) {
				ps = con.prepareStatement("insert into marks(student_id,subject_code,marks,semester,batch_id)  values(?,?,?,?,?)");
				for(int i=0;i<studentIds.length;i++) {
					ps.setString(1, studentIds[i]);
					ps.setString(2, subject_code);
					ps.setString(3, marks[i]);
					ps.setInt(4,semester);
					ps.setString(5, batch_id);
					rs = ps.executeUpdate();
					if(rs>0) {
						response.sendRedirect("../faculty/FacultyDashboard.jsp?msg=Marks added successfully");
					}
					else {
						response.getWriter().write("Error");
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
