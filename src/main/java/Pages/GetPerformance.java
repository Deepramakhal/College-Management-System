package Pages;

import jakarta.servlet.RequestDispatcher;
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
import java.util.List;

import db.DbConnection;

@WebServlet("/admin/getPerformance")
public class GetPerformance extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String batch = request.getParameter("batch");
        String course = request.getParameter("course");
        String batchId = course + batch;
        List<StudentPerformance> studentPerformances = new ArrayList<>();
        try (Connection con = DbConnection.getConnection()) {
            String sql = "SELECT s.id AS student_id, s.name AS student_name, AVG(r.sgpa) AS cgpa " +
                         "FROM student s " +
                         "JOIN result r ON s.id = r.student_id " +
                         "WHERE r.batch_id = ? " +
                         "GROUP BY s.id, s.name";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, batchId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String studentId = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                String cgpa = String.format("%.2f", rs.getDouble("cgpa"));

                studentPerformances.add(new StudentPerformance(studentId, studentName, cgpa));
            }
            request.setAttribute("studentPerformances", studentPerformances);
            request.setAttribute("batch", batchId);
			request.setAttribute("course", course);
            RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/Performance.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp?msg=Error fetching student performance");
        }
    }

    public static class StudentPerformance {
        private String studentId;
        private String studentName;
        private String cgpa;

        public StudentPerformance(String studentId, String studentName, String cgpa) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.cgpa = cgpa;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getCgpa() {
            return cgpa;
        }
    }
}
