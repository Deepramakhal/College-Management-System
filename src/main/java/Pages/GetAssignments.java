package Pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Student;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import db.DbConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/student/GetAssignments")
public class GetAssignments extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Ensure session and student object exist
        if (session == null || session.getAttribute("student") == null) {
            response.sendRedirect("../login.jsp"); // Redirect to login if session expired or invalid
            return;
        }

        Student student = (Student) session.getAttribute("student");
        ArrayList<AssignmentDetails> assignments = new ArrayList<>();

        try (Connection con = DbConnection.getConnection()) {
            String sql = "SELECT ua.batch_id, ua.subject_code, ua.deadline, ua.content, ua.topic, " +
                         "ua.id AS uploaded_id, sa.remarks AS submission_remarks, sa.isChecked, sa.id AS student_assignment_id " +
                         "FROM uploaded_assignment ua " +
                         "LEFT JOIN student_assignment sa ON ua.id = sa.id AND sa.student_id = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Integer.toString(student.getId()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String deadlineStr = rs.getString("deadline");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate deadline = LocalDate.parse(deadlineStr, formatter);
                LocalDate currentDate = LocalDate.now();

                // Get IDs from the database
                int uploadedId = rs.getInt("uploaded_id");
                int studentAssignmentId = rs.getInt("student_assignment_id");

                // Check if the student assignment matches the uploaded assignment
                boolean isSubmitted = (studentAssignmentId == uploadedId);
                boolean isDisabled = isSubmitted || deadline.isBefore(currentDate);

                // Add the assignment details to the list
                assignments.add(new AssignmentDetails(
                    rs.getString("batch_id"),
                    rs.getString("subject_code"),
                    rs.getString("deadline"),
                    rs.getString("content"),
                    rs.getString("topic"),
                    uploadedId,
                    isSubmitted ? "Submitted"+rs.getString("submission_remarks") : null,  // Submission status is derived here
                    isDisabled,
                    rs.getBoolean("isChecked")
                ));
            }


            // Set the attribute to forward to JSP
            request.setAttribute("todo", assignments);
            request.getRequestDispatcher("../student/StudentDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class AssignmentDetails {
        String batch_id;
        String subject_code;
        String deadline;
        String content;
        String topic;
        int uploaded_id;
        String submission_remarks;
        boolean isDisabled; // Flag for disabling submit button
        boolean isChecked;  // Whether the faculty has checked the assignment

        public AssignmentDetails(String batch_id, String subject_code, String deadline, String content,
                                 String topic, int uploaded_id, String submission_remarks, boolean isDisabled, boolean isChecked) {
            this.batch_id = batch_id;
            this.subject_code = subject_code;
            this.deadline = deadline;
            this.content = content;
            this.topic = topic;
            this.uploaded_id = uploaded_id;
            this.submission_remarks = submission_remarks;
            this.isDisabled = isDisabled;
            this.isChecked = isChecked;
        }

        // Getter methods for the properties
        public String getBatch_id() { return batch_id; }
        public String getSubject_code() { return subject_code; }
        public String getDeadline() { return deadline; }
        public String getContent() { return content; }
        public String getTopic() { return topic; }
        public int getUploaded_id() { return uploaded_id; }
        public String getSubmission_remarks() { return submission_remarks; }
        public boolean getIsDisabled() { return isDisabled; }
        public boolean getIsChecked() { return isChecked; } // Getter for isChecked flag
    }
}
