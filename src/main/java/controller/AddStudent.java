package controller;
import util.DeleteFileUtil;
import util.EmailUtil;
import util.RandomStringGenerator;
import java.time.LocalDate;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.mail.MessagingException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import db.DbConnection;
@WebServlet("/admin/addStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String current_year = Integer.toString(LocalDate.now().getYear()%100);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action"); // Get the action parameter
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String address = request.getParameter("address");
	    String gender = request.getParameter("gender");
	    String dob = request.getParameter("dob");
	    String course = request.getParameter("course");
	    String guardian_name = request.getParameter("guardian_name");
	    String filePath1 = request.getParameter("file1");
	    String filePath2 = request.getParameter("file2");
	    String filePath3 = request.getParameter("file3");
	    String batch_id = course + current_year;
	    if ("approve".equalsIgnoreCase(action)) {
	        approveStudent(request, response, name, email, phone, address, gender, dob, course, guardian_name, batch_id,filePath1,filePath2,filePath3);
	    } else if ("reject".equalsIgnoreCase(action)) {
	        rejectStudent(request, response, email, name, filePath1, filePath2, filePath3);
	        String subject = "Admission Rejected";
	        String content = "Your application for admission in Technova Institute has been rejected. Please recheck your documents, eligibility and try again before admission session ends.";
	        try {
				EmailUtil.sendEmail(email, subject, content);
			} catch (MessagingException e) {e.printStackTrace();}
	    }
	}
	private void approveStudent(HttpServletRequest request, HttpServletResponse response, String name, String email, String phone,
	                            String address, String gender, String dob, String course, String guardian_name, String batch_id,String filePath1,String filePath2,String filePath3) throws IOException {
	    String password = RandomStringGenerator.generateRandomString(10);
	    String profileImage = "upload/profile.jpg";
	    int current_sem = 1;
	    int id = generateId(course);

	    try (Connection conn = DbConnection.getConnection()) {
	        String query = "INSERT INTO student(id, name, email, phone, guardian_name, gender, dob, batch_id, current_sem, password, profile_image, address) " +
	                       "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, id);
	        ps.setString(2, name);
	        ps.setString(3, email);
	        ps.setString(4, phone);
	        ps.setString(5, guardian_name);
	        ps.setString(6, gender);
	        ps.setString(7, dob);
	        ps.setString(8, batch_id);
	        ps.setInt(9, current_sem);
	        ps.setString(10, BCrypt.hashpw(password, BCrypt.gensalt(10)));
	        ps.setString(11, profileImage);
	        ps.setString(12, address);
	        int rs = ps.executeUpdate();
	        if (rs > 0) {
	            ps = conn.prepareStatement("DELETE FROM admission WHERE email=? AND name=?");
	            ps.setString(1, email);
	            ps.setString(2, name);
	            ps.executeUpdate();
	            String subject = "Registration done!";
	            String content = "Your registration in Technova Institute is successfully done. You can now access the portal with your ID " + id +
	                             ", email " + email + ", and password " + password;
	            EmailUtil.sendEmail(email, subject, content);
	            if (filePath1 != null) DeleteFileUtil.deleteFile(filePath1);
	            if (filePath2 != null) DeleteFileUtil.deleteFile(filePath2);
	            if (filePath3 != null) DeleteFileUtil.deleteFile(filePath3);
	            response.sendRedirect("../admin/PrincipalDashboard.jsp?msg=Student added");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private void rejectStudent(HttpServletRequest request, HttpServletResponse response, String email, String name,
	                           String filePath1, String filePath2, String filePath3) throws IOException {
	    try (Connection conn = DbConnection.getConnection()) {
	        PreparedStatement ps = conn.prepareStatement("DELETE FROM admission WHERE email=? AND name=?");
	        ps.setString(1, email);
	        ps.setString(2, name);
	        int rs = ps.executeUpdate();

	        if (rs > 0) {
	            // Delete uploaded files if they exist
	            if (filePath1 != null) DeleteFileUtil.deleteFile(filePath1);
	            if (filePath2 != null) DeleteFileUtil.deleteFile(filePath2);
	            if (filePath3 != null) DeleteFileUtil.deleteFile(filePath3);

	            response.sendRedirect("../admin/PrincipalDashboard.jsp?action=reject");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private int generateId(String course) {
	    try (Connection conn = DbConnection.getConnection()) {
	        PreparedStatement ps = conn.prepareStatement("SELECT max(id) FROM student WHERE batch_id=?");
	        String batchId = course + current_year;
	        ps.setString(1, batchId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int maxId = rs.getInt(1);
	            if (!rs.wasNull()) {
	                return maxId + 1;
	            }
	        }
	        ps = conn.prepareStatement("SELECT id FROM course WHERE name=?");
	        ps.setString(1, course);
	        rs = ps.executeQuery();

	        if (rs.next()) { // Ensure the result set contains data
	            String courseId = rs.getString("id");
	            return Integer.parseInt(courseId + current_year + "001");
	        } else {
	            throw new Exception("Course not found for name: " + course);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}


}
