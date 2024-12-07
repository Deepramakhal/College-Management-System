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
import util.RandomStringGenerator;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/addOfficial")
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
		String profileImage = null;
		
		try(Connection con = DbConnection.getConnection()){
			if(post.equals("HOD")) {
				String sql = "insert into admin values(?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
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
					System.out.println("Successfully added HOD");
				}
			}else {String  sql = "insert into faculty values(?,?,?,?,?,?,?,?)";
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
					System.out.println("Successfully added Faculty");
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
				PreparedStatement ps = con.prepareStatement("select count(id) from faculty where course_id=?");
				ps.setString(1, courseId);
				ResultSet rs = ps.executeQuery();
				String id = "001";
				if(rs.next()){
					int count = rs.getInt(1);
					if(count==0) count++;
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