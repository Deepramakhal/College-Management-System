package Pages;
import util.GetSemesterFromBatch;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Subjects;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbConnection;

@WebServlet("/admin/ApproveResult")
public class ResultsForApproval extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String batch = request.getParameter("batch");
        try (Connection con = DbConnection.getConnection()) {
            int semester = GetSemesterFromBatch.getCurrentSemester(batch);
            PreparedStatement ps = con.prepareStatement("SELECT student_id, subject_code, marks, semester FROM marks WHERE batch_id =? and semester=?");
            ps.setInt(2, semester);
            ps.setString(1,batch);
            ResultSet rs = ps.executeQuery();
            Map<String, List<Marks>> groupedMarks = new HashMap<>();
            while (rs.next()) {
                String studentId = rs.getString("student_id");
                Marks mark = new Marks(studentId, rs.getString("subject_code"), rs.getFloat("marks"), semester);
                groupedMarks.computeIfAbsent(studentId, k -> new ArrayList<>()).add(mark);
            }
            ps = con.prepareStatement("SELECT name,code FROM subject WHERE semester = ?");
            ps.setInt(1, semester);
            rs = ps.executeQuery();

            List<Subjects> subjects = new ArrayList<>();
            while (rs.next()) {
                subjects.add(new Subjects(rs.getString("name"),rs.getString("code")));
            }
            request.setAttribute("marks", groupedMarks);
            request.setAttribute("subjects", subjects);
            request.getRequestDispatcher("../admin/ApproveResult.jsp?batch="+batch+"&semester="+semester).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Marks {
        String student_id;
        String subject_code;
        float marks;
        int semester;

        public Marks(String student_id, String subject_code, float marks, int semester) {
            this.student_id = student_id;
            this.subject_code = subject_code;
            this.marks = marks;
            this.semester = semester;
        }

        public String getStudent_id() {
            return student_id;
        }

        public String getSubject_code() {
            return subject_code;
        }

        public float getMarks() {
            return marks;
        }

        public int getSemester() {
            return semester;
        }
    }
}
