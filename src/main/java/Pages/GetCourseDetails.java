package Pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.GetCourseId;
import db.DbConnection;

@WebServlet("/admin/GetCourseDetails")
public class GetCourseDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String course = request.getParameter("course");
		String fn = request.getParameter("fn");
		ArrayList<Faculty> faculty = new ArrayList<>();
		Admin hod = null;
		ArrayList<Batch> batches = new ArrayList<>();
		try(Connection con = DbConnection.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from admin where admin_type='HOD' and id like ?");
			ps.setString(1, course+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			    hod = new Admin();
				hod.setId(rs.getString("id"));
				hod.setName(rs.getString("name"));
				hod.setEmail(rs.getString("email"));
				hod.setPhone(rs.getString("phone"));
				hod.setProfileImage(rs.getString("profile_image"));
			}
			request.setAttribute("hod", hod);
			ps = con.prepareStatement("Select * from batch where name like ?");
			ps.setString(1, course+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				batches.add(new Batch(rs.getString("name"), rs.getString("starting_year"), rs.getString("ending_year")));
			}
			request.setAttribute("batches", batches);
			ps = con.prepareStatement("select * from faculty where course_id=?");
			ps.setString(1, GetCourseId.courseIds(course));
			rs = ps.executeQuery();
			while(rs.next()) {
				faculty.add( new Faculty(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"),rs.getString("course_id"), rs.getString("profile_image"), rs.getString("address")));
			}
			request.setAttribute("faculty", faculty);
			request.getRequestDispatcher("../admin/ViewCourseDetails.jsp?fn="+fn).forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
