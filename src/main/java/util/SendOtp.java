package util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import db.DbConnection;

@WebServlet("/SendOtp")
public class SendOtp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req = request.getParameter("action");
		String user = request.getParameter("user");
		String mail = request.getParameter("mail");
		String returnPage = request.getParameter("output");
		Timestamp expiry = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);
		String otp = RandomStringGenerator.generateRandomString(6);
		String subject = req+" request.";
		String message = "Your one-time-password(OTP) for "+req+" is "+otp;
		PreparedStatement ps = null;
		int r = 0;
		try(Connection con = DbConnection.getConnection()){
			if("ADMIN".equals(user)) {
				ps = con.prepareStatement("update admin set otp = ?, otp_expiry= ? where email = ?");
				ps.setString(1, otp);
				ps.setTimestamp(2, expiry);
				ps.setString(3, mail);
				r = ps.executeUpdate();
				if(r>0) {
					EmailUtil.sendEmail(mail, subject, message);
					response.sendRedirect(returnPage+"?action="+req+"&user="+user+"&mail="+mail);
				}else {response.getWriter().write("<html><body><h1>Invalid request</h1></body></html>");}
			}
			else if("FACULTY".equals(user)) {
				ps = con.prepareStatement("update faculty set otp = ?, otp_expiry= ? where email = ?");
				ps.setString(1, otp);
				ps.setTimestamp(2, expiry);
				ps.setString(3, mail);
				r = ps.executeUpdate();
				if(r>0) {
					EmailUtil.sendEmail(mail, subject, message);
					response.sendRedirect(returnPage+"?action="+req+"&user="+user+"&mail="+mail);
				}else {response.getWriter().write("<html><body><h1>Invalid request</h1></body></html>");}
			}
			else if("STUDENT".equals(user)) {
				ps = con.prepareStatement("update student set otp = ?, otp_expiry= ? where email = ?");
				ps.setString(1, otp);
				ps.setTimestamp(2,expiry);
				ps.setString(3, mail);
				r = ps.executeUpdate();
				if(r>0) {
					EmailUtil.sendEmail(mail, subject, message);
					response.sendRedirect(returnPage+"?action="+req+"&user="+user+"&mail="+mail);	
					}else {response.getWriter().write("<html><body><h1>Invalid request </h1></body></html>"); }
			} else {response.getWriter().write("<html><body><h1>Invalid </h1></body></html>");}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
