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

@WebServlet("/admin/getApplications")
public class GetApplications extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(Connection con = DbConnection.getConnection())
		{
			ArrayList<Application> applications = new ArrayList<>();
			String sql = "select * from application";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				applications.add(new Application( rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("qualification"), rs.getString("experience"), rs.getString("department"), rs.getString("post"), rs.getString("address"), rs.getString("resume"),rs.getString("status")));
			}
			request.setAttribute("applications", applications);
			request.getRequestDispatcher("../admin/PrincipalDashboard.jsp").forward(request, response);
		}
		catch (Exception e) {e.printStackTrace();}
	}
	public class Application{
	    private String name;
	    private String email;
	    private String phone;
	    private String qualification;
	    private String experience;
	    private String department;
	    private String post;
	    private String address;
	    private String resume;
	    private String status;

	    public Application(String name, String email, String phone, String qualification,String experience, String department, 
	                       String post, String address, String resume,String status) {
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	        this.qualification = qualification;
	        this.experience = experience;
	        this.department = department;
	        this.post = post;
	        this.address = address;
	        this.resume = resume;
	        this.status = status;
	    }
	    public String getName() {
	        return name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public String getQualification() {
	        return qualification;
	    }

	    public String getExperience() {
	        return experience;
	    }

	    public String getDepartment() {
	        return department;
	    }

	    public String getPost() {
	        return post;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public String getResume() {
	        return resume;
	    } public String getStatus() {
	        return status;
	    }
	}
}
