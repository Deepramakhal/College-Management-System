package controller;

import util.EmailUtil;
import util.RandomStringGenerator;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbConnection;


@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String current_year = Integer.toString(LocalDate.now().getYear()%100);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String course = request.getParameter("course");
		String guardian_name = request.getParameter("guardian_name");
		String batch_id = course+current_year;
		String password = RandomStringGenerator.generateRandomString(10);
		String profileImage = null;
		int current_sem = 1;
		int id = generateId(course);
		
		try(Connection conn = DbConnection.getConnection()){
			String query = "insert into student values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
			ps.setString(10, password);
			ps.setString(11, profileImage);
			ps.setString(12, address);
			int rs = ps.executeUpdate();
			if(rs==1){
				ps = conn.prepareStatement("delete from admission where email=? and name=?");
				ps.setString(1, email);
				ps.setString(2, name);
				ps.executeUpdate();
				String subject = "Registration done!";
				String content = "Your registration in ABC college is successfully done. You can now access the portal with your id "+id+" email "+email+" and password "+password;
				EmailUtil.sendEmail(email, subject, content);
				response.sendRedirect("addStudent.jsp");
			}
			
			
		}
		catch(Exception e){e.printStackTrace();}
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
