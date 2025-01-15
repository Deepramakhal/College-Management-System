package Pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.GetSemesterFromBatch;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

import db.DbConnection;


@WebServlet("/admin/getResult")
public class GetResults extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try (Connection con = DbConnection.getConnection()) {
	    	String course = request.getParameter("course");
	        String batch = request.getParameter("batch");
	        int semester = GetSemesterFromBatch.getCurrentSemester(batch)+1;
	        String query = "SELECT r.student_id, r.semester, r.sgpa, r.batch_id,r.timestamp, s.name AS student_name " +
	                       "FROM result r " +
	                       "JOIN student s ON r.student_id = s.id " +
	                       "WHERE r.batch_id = ? AND r.semester = ? order by r.student_id asc";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, batch);
	        ps.setInt(2, semester);

	        ResultSet rs = ps.executeQuery();
	        ArrayList<Results> results = new ArrayList<>();
	        while (rs.next()) {
	            results.add(new Results(rs.getString("student_id"),rs.getString("student_name"),rs.getInt("semester"),rs.getFloat("sgpa"),
	                rs.getString("batch_id"),rs.getDate("timestamp")));
	        }

	        request.setAttribute("results", results);
	        request.getRequestDispatcher("../admin/HodViewResult.jsp?course="+course+"&batch="+batch).forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public static class Results {
	    String student_id;
	    String student_name; // New field
	    int semester;
	    float sgpa;
	    String batch_id;
	    Date timestamp;

	    public Results(String student_id, String student_name, int semester, float sgpa, String batch_id,Date timestamp) {
	        this.student_id = student_id;
	        this.student_name = student_name; // Initialize new field
	        this.semester = semester;
	        this.sgpa = sgpa;
	        this.batch_id = batch_id;
	        this.timestamp = timestamp;
	    }
	    public Date getTimestamp() {
	    	return timestamp;
	    }
	    // Getters
	    public String getStudent_id() {
	        return student_id;
	    }

	    public String getStudent_name() {
	        return student_name; // Getter for student name
	    }

	    public int getSemester() {
	        return semester;
	    }

	    public float getSgpa() {
	        return sgpa;
	    }

	    public String getBatch_id() {
	        return batch_id;
	    }
	}


}
