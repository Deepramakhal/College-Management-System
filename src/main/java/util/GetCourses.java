package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import db.DbConnection;

@WebServlet("/admin/getCourses")
public class GetCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection con = DbConnection.getConnection()) {
            String sql = "SELECT * FROM course";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Course> courses = new ArrayList<>();
            while (rs.next()) {
                courses.add(new Course(rs.getString("name"), rs.getString("id")));
            }
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("../admin/PrincipalDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to fetch courses. Please try again later.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);}
    public static class Course {
        private String name;
        private String id;

        public Course(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }
}
