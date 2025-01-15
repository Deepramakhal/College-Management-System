package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.VerifyOtp;

import java.io.IOException;
import db.DbConnection;
import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;
@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newpw = request.getParameter("newpw");
		String confirmpw = request.getParameter("confirmpw");
		String user = request.getParameter("user");
		String mail = request.getParameter("mail");
		String otp = request.getParameter("otp");
		response.setContentType("text/html");
		try(Connection con = DbConnection.getConnection()){
			String sql = getQuery(user);
			PreparedStatement ps = con.prepareStatement(sql);
			if(newpw.equals(confirmpw)) {
				if(VerifyOtp.verifyOtp(user, mail, otp)) {
					ps.setString(1, BCrypt.hashpw(newpw, BCrypt.gensalt(10)));
					ps.setString(2, mail);
					int i = ps.executeUpdate();
					if(i>0) {
						response.getWriter().print("<htm><body><center><h1>New Password set successfully<a href='Login.jsp'>Login</a></h1><center></body></html>");
					}else response.getWriter().print("<htm><body>Invalid request<a href='Login.jsp'>Login</a></body></html>");
				}else response.getWriter().write("Invalid OTP");
			}else {response.getWriter().print("<htm><body>New password and confirm password should be same!<a href='ForgetPassword.jsp'>Return</a></body></html>");}
		}catch(Exception e) {e.printStackTrace();}
	}
	private String getQuery(String user) {
		if("HOD".equals(user)||"PRINCIPAL".equals(user)||"ADMIN".equals(user)){
			return "update admin set password = ? where email = ?";
		}
		else if("FACULTY".equals(user)) {
			return "update faculty set password = ? where email = ?";
		}
		else if("STUDENT".equals(user)) {
			return "update student set password = ? where email = ?";
		}else
		return null;
	}
}
