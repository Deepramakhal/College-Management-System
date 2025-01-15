package Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import db.DbConnection;
import java.sql.*;
import java.util.ArrayList;
@WebServlet("/student/getResource")
public class GetResources extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String batch_id = request.getParameter("batch");
		int current_sem = Integer.parseInt(request.getParameter("current_sem"));
		int student_id = Integer.parseInt(request.getParameter("stdId"));
		ArrayList<String> subjects = getSubjects(current_sem,batch_id,student_id);
		ArrayList<Resource> resource = new ArrayList<>();
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("select * from resource where batch_id = ?");
			ps.setString(1, batch_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resource.add(new Resource(rs.getString("faculty_name"),rs.getString("title"),rs.getString("content"),rs.getString("subject_code")));
			}
			request.setAttribute("resource", resource);
			request.setAttribute("crrSubjects", subjects);
			request.getRequestDispatcher("../student/StudentDashboard.jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<String> getSubjects(int current_sem, String batch_id,int student_id){
		ArrayList<String> subjects = new ArrayList<>();
		String course_id = Integer.toString(student_id/100000);
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("Select code from subject where semester = ? and course_id=?");
			ps.setInt(1, current_sem);
			ps.setString(2, course_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				subjects.add(rs.getString("code"));
			}
			return subjects;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static class Resource{
		String faculty_name;
		String title;
		String content;
		String subject_code;
		public Resource(String faculty_name,String title,String content,String subject_code) {
			this.subject_code = subject_code;
			this.title = title;
			this.content = content;
			this.faculty_name = faculty_name;
		}
		public String getFaculty_name() {return faculty_name;}
		public String getTitle() {return title;}
		public String getContent() {return content;}
		public String getSubject_code() {return subject_code;}
	}

}
