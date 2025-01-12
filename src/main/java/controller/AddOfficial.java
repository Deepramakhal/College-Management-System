package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.EmailUtil;
import util.RandomStringGenerator;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/admin/addOfficial")
public class AddOfficial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost ( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String post=request.getParameter("post");
		String department=request.getParameter("department");
		String phone=request.getParameter("phone");
		String password = RandomStringGenerator.generateRandomString(12);
		String profileImage = "upload/profile.jpg";	
		try(Connection con = DbConnection.getConnection()){
			if(post.equals("HOD")) {
				PreparedStatement ps = con.prepareStatement("delete from admin where admin_type=? and id=?");
				ps.setString(1, post);
				ps.setString(2, generateID(department, post));
				ps.executeUpdate();
				String sql = "insert into admin(id,name,email,phone,admin_type,password,profile_image,address) values(?,?,?,?,?,?,?,?)";
			    ps = con.prepareStatement(sql);
				ps.setString(1, generateID(department, post));
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, phone);
				ps.setString(5, post);
				ps.setString(6, BCrypt.hashpw(password, BCrypt.gensalt(10)));
				ps.setString(7, profileImage);
				ps.setString(8,address);
				int rs = ps.executeUpdate();
				if(rs>0){
					PreparedStatement ps1 = con.prepareStatement("Update application set status = ? where email = ?");
					ps1.setString(1, "Approved");
					ps1.setString(2, email); ps1.executeUpdate();
					String sub = "Technova Institute";
					String content = "Welcom to Technova Institute. We are glad to have you as " + post +" of " + department + ". \n"
							+ "Your password to access the portal  is " + password;
					EmailUtil.sendEmail(email,sub , content);
					response.sendRedirect("../admin/PrincipalDashboard.jsp?msg=Successfully added HOD");
				}
			}else {String  sql = "insert into faculty(id,name,email,phone,course_id,password,profile_image,address) values(?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, generateID(department, post));
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, phone);
				ps.setString(5, getCourseId(department));
				ps.setString(6, BCrypt.hashpw(password, BCrypt.gensalt(10)));
				ps.setString(7, profileImage);
				ps.setString(8,address);
				int rs = ps.executeUpdate();
				if(rs>0){
					PreparedStatement ps1 = con.prepareStatement("Update application set status = ? where email = ?");
					ps1.setString(1, "Approved");
					ps1.setString(2, email); ps1.executeUpdate();
					String sub = "Technova Institute";
					String content = "Welcom to Technova Institute. We are glad to have you as " + post +"of " + department + ". \n"
							+ "Your password to access the portal  is " + password;
					EmailUtil.sendEmail(email,sub , content);
					response.sendRedirect("../admin/PrincipalDashboard.jsp?msg=Successfully added Faculty");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String generateID(String department, String post) {
		try(Connection con = DbConnection.getConnection()){
			if(post.equals("HOD")){
				return department+"HOD";
			}
			else {
				String courseId = getCourseId(department);
				PreparedStatement ps = con.prepareStatement("select count(id) as count from faculty where course_id=?");
				ps.setString(1, courseId);
				ResultSet rs = ps.executeQuery();
				String id = "001";
				if(rs.next()){
					int count = rs.getInt("count");
					count=count+1;
					id = String.format("%03d", count);
				}
				return courseId+id;       
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private String getCourseId(String department) {
		try(Connection con = DbConnection.getConnection()){
			String sql = "select id from course where name=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, department);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("id");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}