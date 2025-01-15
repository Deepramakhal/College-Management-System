package Pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.DbConnection;
@WebServlet("/faculty/checkAssignment")
public class CheckUploadedAssignments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String batch_id = request.getParameter("batch_id");
		String subject_code = request.getParameter("subject_code");
		ArrayList<UploadedAssignment> assignments = new ArrayList<>();
		try(Connection con = DbConnection.getConnection()){
			String sql = "select * from student_assignment where batch_id=? and subject_code=? and isChecked= ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, batch_id);
			ps.setString(2, subject_code);
			ps.setString(3, "false");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				assignments.add(new UploadedAssignment(rs.getInt("id"), rs.getString("student_id"), rs.getString("content"), rs.getString("subject_code"), rs.getString("batch_id")));
			}
			request.setAttribute("assignments", assignments);
			request.getRequestDispatcher("../faculty/CheckAssignment.jsp?batch="+batch_id).forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static class UploadedAssignment{
		int id;
		String student_id;
		String content;
		String subject_code;
		String batch_id;
		public UploadedAssignment(int id, String student_id, String content, String subject_code, String batch_id) {
			this.id = id;
			this.student_id = student_id;
			this.content = content;
			this.subject_code = subject_code;
			this.batch_id = batch_id;
		}
		public int getId() {
			return id;
		}
		public String getStudent_id() {
			return student_id;
		}
		public String getContent() {
			return content;
		}
		public String getSubject_code() {
			return subject_code;
		}
		public String getBatch_id() {
			return batch_id;
		}
	
	}

}
