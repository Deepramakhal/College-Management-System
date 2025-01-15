package Pages;

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
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbConnection;

@WebServlet("/getsubjects")
public class GetCurrentSubjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		Student student = (Student) s.getAttribute("student");
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("select * from subject where semester=?");
			ps.setInt(1, student.getCurrentSem());
			ResultSet rs = ps.executeQuery();
			ArrayList<Subject> subjects = new ArrayList<>();
			while(rs.next()) {
				subjects.add(new Subject(rs.getString("name"),rs.getString("code"),rs.getInt("semester"),rs.getString("course_id")));		
			}
			request.setAttribute("subjects", subjects);
			request.getRequestDispatcher("testing.jsp").forward(request, response);
			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public static class Subject{
		String name;
		String subject_code;
		int semester;
		String course_id;
		public Subject(String name,String subject_code, int semester,String course_id) {
			this.name = name;
			this.subject_code = subject_code;
			this.semester = semester;
			this.course_id = course_id;
		}
		public String getName() {
			return name;
		}
		public String getSubject_code() {
			return subject_code;
		}
		public int getSemester() {
			return semester;
		}
		public String getCourse_id() {
			return course_id;
		}
	}
}
