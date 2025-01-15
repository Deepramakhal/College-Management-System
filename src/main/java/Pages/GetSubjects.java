package Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Subjects;

import java.io.IOException;
import db.DbConnection;
import java.sql.*;
import java.util.ArrayList;
@WebServlet("/student/getSubjects")
public class GetSubjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int semester = Integer.parseInt(request.getParameter("semester"));
		String output = request.getParameter("output");
		String course = request.getParameter("course");
		ArrayList<Subjects> subjects = new ArrayList<>();
		try(Connection con = DbConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement("Select name,code from subject where semester = ? and course_id = ?");
			ps.setInt(1, semester);
			ps.setString(2, course);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				subjects.add(new Subjects(rs.getString("name"),rs.getString("code")));
			}
			request.setAttribute("course", course);
			request.setAttribute("subjects", subjects);
			request.getRequestDispatcher(output+".jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
