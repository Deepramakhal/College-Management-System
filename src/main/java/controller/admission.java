package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.FileUploadUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DbConnection;

@WebServlet("/admission")
@MultipartConfig
public class admission extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName=request.getParameter("firstName");
		String middleName=request.getParameter("middleName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String guardianName=request.getParameter("guardianName");
		String guardianPhone = request.getParameter("guardianPhone");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String pincode=request.getParameter("pincode");
        String schoolName=request.getParameter("schoolname");
        String lastexam = request.getParameter("lastexam");
        String boardName=request.getParameter("boardName");
        String courseName=request.getParameter("course");
        String spercentage=request.getParameter("spercentage");
        String hspercentage=request.getParameter("hspercentage");
        String lpercentage=request.getParameter("lpercentage");
        Part file1=request.getPart("marksheet1");
        Part file2=request.getPart("marksheet2");
        Part file3 = request.getPart("marksheet3");
        String filepath1=null,filepath2=null,filepath3=null;
        if(file1!=null) {
			 filepath1 = FileUploadUtil.saveFile(file1);
        }
		if(file2!=null) {
			filepath2 = FileUploadUtil.saveFile(file2);
		}
		if (file3 != null && file3.getSize() > 0) {
		    filepath3 = FileUploadUtil.saveFile(file3);
		} else {
		    filepath3 = null;
		}
		String name =null;
		if(middleName==null) {
			 name=firstName+" "+lastName;
		} else {
			 name = firstName+" "+middleName+" "+lastName;
		}
        address=address+" "+city+" "+state+" "+pincode;
		schoolName=schoolName+" "+"("+boardName+")";
		try (Connection con=DbConnection.getConnection())
		{
			String sql="insert into admission values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(7, email);
			ps.setString(6, phone);
			ps.setString(2, guardianName+"("+guardianPhone+")");
			ps.setString(4, gender);
			ps.setString(3, dob);
			ps.setString(5, address);
			ps.setString(8, schoolName);
			ps.setString(9, lastexam);
			ps.setString(13, courseName);
			ps.setString(10, spercentage);
			ps.setString(11, hspercentage);
			ps.setString(12, lpercentage);
			ps.setString(14, filepath1);
			ps.setString(15, filepath2);
			ps.setString(16, filepath3);
			int i = ps.executeUpdate();
			if(i>0) {
				response.getWriter().write("success");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}