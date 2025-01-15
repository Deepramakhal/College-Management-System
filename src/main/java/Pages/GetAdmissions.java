package Pages;

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

import db.DbConnection;

@WebServlet("/admin/getAdmissions")
public class GetAdmissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(Connection con = DbConnection.getConnection())
		{
			ArrayList<Admissions> admissions = new ArrayList<>();
			String sql = "select * from admission";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				admissions.add(new Admissions(rs.getString("name"),rs.getString("guardian_info"),rs.getString("dob"),rs.getString("gender"),
						rs.getString("address"),rs.getString("phone"),rs.getString("email"),rs.getString("previous_school"),rs.getString("last_exam"),
						rs.getInt("secondary"),rs.getInt("higher_secondary"),rs.getString("last_exam_percentage"),rs.getString("course"),
						rs.getString("file1"),rs.getString("file2"),rs.getString("file3")));
			}
			request.setAttribute("admissions", admissions);
			request.getRequestDispatcher("../admin/PrincipalDashboard.jsp").forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public class Admissions {
	    private String name;
	    private String guardian_info;
	    private String dob;
	    private String gender;
	    private String address;
	    private String phone;
	    private String email;
	    private String previous_school;
	    private String last_exam;
	    private int secondary;
	    private int higher_secondary;
	    private String last_exam_percentage;
	    private String course;
	    private String file1;
	    private String file2;
	    private String file3;

	    // Constructor with all parameters
	    public Admissions(String name, String guardian_info, String dob, String gender, String address,
	                     String phone, String email, String previous_school, String last_exam,
	                     int secondary, int higher_secondary, String last_exam_percentage, String course,
	                     String file1, String file2, String file3) {
	        this.name = name;
	        this.guardian_info = guardian_info;
	        this.dob = dob;
	        this.gender = gender;
	        this.address = address;
	        this.phone = phone;
	        this.email = email;
	        this.previous_school = previous_school;
	        this.last_exam = last_exam;
	        this.secondary = secondary;
	        this.higher_secondary = higher_secondary;
	        this.last_exam_percentage = last_exam_percentage;
	        this.course = course;
	        this.file1 = file1;
	        this.file2 = file2;
	        this.file3 = file3;
	    }

	    // Getters only
	    public String getName() {
	        return name;
	    }

	    public String getGuardian_info() {
	        return guardian_info;
	    }

	    public String getDob() {
	        return dob;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getPrevious_school() {
	        return previous_school;
	    }

	    public String getLast_exam() {
	        return last_exam;
	    }

	    public int getSecondary() {
	        return secondary;
	    }

	    public int getHigher_secondary() {
	        return higher_secondary;
	    }

	    public String getLast_exam_percentage() {
	        return last_exam_percentage;
	    }

	    public String getCourse() {
	        return course;
	    }

	    public String getFile1() {
	        return file1;
	    }

	    public String getFile2() {
	        return file2;
	    }

	    public String getFile3() {
	        return file3;
	    }
	}

}
