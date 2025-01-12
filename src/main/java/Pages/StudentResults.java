package Pages;

import jakarta.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Student;

import java.io.IOException;
import db.DbConnection;
@WebServlet("/student/getResults")
public class StudentResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		Student student = (Student) s.getAttribute("student");
		ArrayList<StudentResult> stdResult = new ArrayList<>();
		try(Connection con = DbConnection.getConnection() ){
			PreparedStatement ps = con.prepareStatement("Select semester,round(sgpa,2) as sgpa,DATE(timestamp) as timestamp from result where student_id =?");
			ps.setString(1, Integer.toString(student.getId()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String formattedSgpa = String.format("%.2f", rs.getFloat("sgpa"));
				stdResult.add(new StudentResult(rs.getInt("semester"),Double.parseDouble(formattedSgpa),rs.getString("timestamp")));
			}
			System.out.print(stdResult.toString());
			request.setAttribute("result", stdResult);
			request.getRequestDispatcher("../student/StudentDashboard.jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static class StudentResult{
		int semester;
		double sgpa;
		String timestamp;
		public StudentResult(int semester,double sgpa,String timestamp) {
			this.sgpa = sgpa;
			this.semester = semester;
			this.timestamp = timestamp;
		}
		public int getSemester() {return semester;}
		public double getSgpa () {return sgpa;}
		public String getTimestamp() {return timestamp;}
	}
}
