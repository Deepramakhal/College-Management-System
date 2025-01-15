package Pages;

import java.sql.*;
import java.util.*;
import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/internalMarks")
public class GetInternalMarks extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String student_id = request.getParameter("student_id");
        int semester = Integer.parseInt(request.getParameter("semester"));

        try (Connection con = DbConnection.getConnection()) {
            String sql = "SELECT subject_code, exam_name, marks FROM internal_marks WHERE student_id=? AND semester=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student_id);
            ps.setInt(2, semester);
            ResultSet rs = ps.executeQuery();
            Map<String, Internal_marks> marksMap = new LinkedHashMap<>();

            while (rs.next()) {
                String subject_code = rs.getString("subject_code");
                String exam_name = rs.getString("exam_name");
                int marks = rs.getInt("marks");

                marksMap.putIfAbsent(subject_code, new Internal_marks(subject_code, 0, 0, 0));
                Internal_marks internalMarks = marksMap.get(subject_code);
                switch (exam_name) {
                    case "a1":
                        internalMarks.setA1(marks);
                        break;
                    case "a2":
                        internalMarks.setA2(marks);
                        break;
                    case "a3":
                        internalMarks.setA3(marks);
                        break;}
            }
            request.setAttribute("marksList", marksMap.values());
            request.getRequestDispatcher("../student/StudentDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching marks");
        }
    }

    public static class Internal_marks {
        private String subject_code;
        private int a1, a2, a3;

        public Internal_marks(String subject_code, int a1, int a2, int a3) {
            this.subject_code = subject_code;
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
        }

        public String getSubject_code() { return subject_code; }
        public int getA1() { return a1; }
        public int getA2() { return a2; }
        public int getA3() { return a3; }
        public void setA1(int a1) { this.a1 = a1; }
        public void setA2(int a2) { this.a2 = a2; }
        public void setA3(int a3) { this.a3 = a3; }
    }
}
