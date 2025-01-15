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

@WebServlet("/admin/addnotice")
@MultipartConfig
public class AddNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content=request.getParameter("content");
		String title=request.getParameter("title");
		Part file = request.getPart("file");
		String filePath = null;
		if(file!=null && file.getSize() > 0) {
			filePath = FileUploadUtil.saveFile(file);
		}
		try(Connection con=DbConnection.getConnection())
		{
			String sql="insert into notice(title,content,file) values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, filePath);
			int i=ps.executeUpdate();
			{
				if(i>0)
				{
					response.sendRedirect("../admin/PrincipalDashboard.jsp?msg=Notice posted successfully");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}