package util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

import db.DbConnection;
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String user = request.getParameter("user");
		String oldPassword = request.getParameter("oldpw");
		String newPassword = request.getParameter("newpw");
		String otp = request.getParameter("otp");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try(Connection con = DbConnection.getConnection()){
			if("ADMIN".equals(user)) {
				boolean verified = VerifyOtp.verifyOtp(user, id, otp);
				if(verified) {
					ps = con.prepareStatement("Select password from admin where id = ?");
					ps.setString(1, id);
					rs = ps.executeQuery();
					if(rs.next()) {
						String prevPassword = rs.getString("password");
						if(BCrypt.checkpw(oldPassword, prevPassword)) {
							ps = con.prepareStatement("update admin set password = ? where id = ?");
							ps.setString(1, BCrypt.hashpw(newPassword,BCrypt.gensalt(10)));
							ps.setString(2, id);
							int i = ps.executeUpdate();
							if(i>0) {
								response.sendRedirect("../Logout");
							}
						}else {response.getWriter().write("Login.jsp?msg=Invalid old password");}
					}else {response.getWriter().write("Login.jsp?msg=Invalid id");}
				}else {response.getWriter().write("Login.jsp?msg=Invalid otp");}
			}
			else if("FACULTY".equals(user)) {
				boolean verified = VerifyOtp.verifyOtp(user, id, otp);
				if(verified) {
					ps = con.prepareStatement("Select password from faculty where id = ?");
					ps.setString(1, id);
					rs = ps.executeQuery();
					if(rs.next()) {
						String prevPassword = rs.getString("password");
						if(BCrypt.checkpw(oldPassword, prevPassword)) {
							ps = con.prepareStatement("update faculty set password = ? where id = ?");
							ps.setString(1, BCrypt.hashpw(newPassword,BCrypt.gensalt(10)));
							ps.setString(2, id);
							int i = ps.executeUpdate();
							if(i>0) {
								response.sendRedirect("../Logout");
							}
						}else {response.getWriter().write("Login.jsp?msg=Invalid old password");}
					}else {response.getWriter().write("Login.jsp?msg=Invalid id");}
				}else {response.getWriter().write("Login.jsp?msg=Invalid otp");}
			}
			else if("STUDENT".equals(user)) {
				boolean verified = VerifyOtp.verifyOtp(user, id, otp);
				if(verified) {
					ps = con.prepareStatement("Select password from student where id = ?");
					ps.setString(1, id);
					rs = ps.executeQuery();
					if(rs.next()) {
						String prevPassword = rs.getString("password");
						if(BCrypt.checkpw(oldPassword, prevPassword)) {
							ps = con.prepareStatement("update student set password = ? where id = ?");
							ps.setString(1, BCrypt.hashpw(newPassword,BCrypt.gensalt(10)));
							ps.setInt(2, Integer.parseInt(id));
							int i = ps.executeUpdate();
							if(i>0) {
								response.sendRedirect("../Logout");
							}
						}else {response.getWriter().write("Login.jsp?msg=Invalid old password");}
					}else {response.getWriter().write("Login.jsp?msg=Invalid id");}
				}else {response.getWriter().write("Login.jsp?msg=Invalid otp");}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
