package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.*;
import db.DbConnection;
import java.sql.*;
import util.EmailUtil;
import util.FileUploadUtil;
import util.VerifyOtp;
import java.io.IOException;	
@WebServlet("/EditProfile")
@MultipartConfig
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String address= request.getParameter("address");
	    Part image = request.getPart("profileImage");
	    String previousImage = request.getParameter("oldImage");
	    String otp = request.getParameter("otp");
		PreparedStatement ps = null;
		int row = 0;
		String imagePath = null;
		HttpSession s = request.getSession(false);
		if(image!=null && image.getSize()>0) {
			imagePath = FileUploadUtil.saveFile(image);
		}
		else {imagePath = previousImage;}
		try (Connection con= DbConnection.getConnection()){
			if("ADMIN".equals(user)) {
				Admin admin = (Admin) s.getAttribute("admin");
				if(admin!=null) {
					boolean verified = VerifyOtp.verifyOtp(user, id, otp);
					if(verified) {
						ps = con.prepareStatement("update admin set email =?,phone=?,address=?,profile_image=? where id =?");
						ps.setString(1, email);
						ps.setString(2, phone);
						ps.setString(3, address);
						ps.setString(4, imagePath);
						ps.setString(5, id);
						row = ps.executeUpdate();
							if(row>0) {
                               EmailUtil.sendEmail(email,"Changed details", "Your details edited successfully");
                               response.sendRedirect("Logout");
                            }
                            else {
                                response.getWriter().write("Failed to update profile!");
                            }
					}
					else {response.getWriter().write("<html><body>Invalid OTP!</body></html>");}
				}
				else {response.getWriter().write("<html><body>Session expired <a href='Login.jsp'>Login again</a></body></html>");}
				
			}
			else if("FACULTY".equals(user)) {
				Faculty faculty = (Faculty) s.getAttribute("faculty");
				if(faculty!=null) {
					boolean verified = VerifyOtp.verifyOtp(user, id, otp);
					if(verified) {
						ps = con.prepareStatement("update faculty set email =?,phone=?,address=?,profile_image=? where id =?");
						ps.setString(1, email);
						ps.setString(2, phone);
						ps.setString(3, address);
						ps.setString(4, imagePath);
						ps.setString(5, id);
						row = ps.executeUpdate();
							if(row>0) {
                               EmailUtil.sendEmail(email,"Changed details", "Your details edited successfully");
                               response.sendRedirect("Logout");
                            }
                            else {response.getWriter().write("Failed to update profile!");}
					}
					else {response.getWriter().write("<html><body>Invalid OTP!</body></html>");}
				}
				else {response.getWriter().write("<html><body>Session expired <a href='Login.jsp'>Login again</a></body></html>");}
			}
			else if("STUDENT".equals(user)) {
				Student student= (Student) s.getAttribute("student");
				if(student!=null) {
					boolean verified = VerifyOtp.verifyOtp(user, id, otp);
					if(verified) {
						ps = con.prepareStatement("update student set email =?,phone=?,address=?,profile_image=? where id =?");
						ps.setString(1, email);
						ps.setString(2, phone);
						ps.setString(3, address);
						ps.setString(4, imagePath);
						ps.setString(5, id);
						row = ps.executeUpdate();
							if(row>0) {
                               EmailUtil.sendEmail(email,"Changed details", "Your details edited successfully");
                               response.sendRedirect("Logout");
                            }
                            else {
                                response.getWriter().write("Failed to update profile!");
                            }
					}
					else {response.getWriter().write("<html><body>Invalid OTP!</body></html>");}
				}
				else {response.getWriter().write("<html><body>Session expired <a href='Login.jsp'>Login again</a></body></html>");}
				
			}
			else {response.getWriter().write("Invalid request!");};
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
