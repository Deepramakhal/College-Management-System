package Pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbConnection;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/faculty/GetStudents", "/admin/GetStudents"})
public class GetStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Student> students = new ArrayList<Student>();
		String batch = request.getParameter("batch");
		String output = request.getParameter("output");
		try(Connection con= DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("select * from student where batch_id = ? ");
			ps.setString(1, batch);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone"), rs.getString("guardian_name"), rs.getString("gender"), 
						rs.getString("dob"), rs.getString("batch_id"), 
						rs.getInt("current_sem"), rs.getString("profile_image"), rs.getString("address")));
			}
			if (students.isEmpty()) {
                request.setAttribute("message", "No students found for the given batch.");
            } else {
				request.setAttribute("students", students);
            }
            request.getRequestDispatcher(output+".jsp?batch="+batch).forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
