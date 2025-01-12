package Pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbConnection;

@WebServlet("/admin/examForms")
public class ExamFormsForApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String course = request.getParameter("course");
	    try (Connection con = DbConnection.getConnection()){
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM exam_form WHERE batch_name like ?");
	        ps.setString(1, course+"%");
	        ResultSet rs = ps.executeQuery();
	        ArrayList<ExamForms> forms = new ArrayList<>();
	        while (rs.next()) {
	            forms.add(new ExamForms(
	                rs.getString("student_id"),
	                rs.getString("name"),
	                rs.getInt("semester"),
	                rs.getString("batch_name"),
	                rs.getString("isAdmitGenerated")
	            ));
	        }
	        if (forms.isEmpty()) {
	            response.getWriter().write("No exam forms found for the given course.");
	            return;
	        }
	        request.setAttribute("forms", forms);
	        request.getRequestDispatcher("../admin/HodDashboard.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().write("Error processing the request.");
	    }
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public static class ExamForms {
	    String student_id;
	    String name;
	    int semester;
	    String batch_id;
	    String isAdmitGenerated;

	    public ExamForms(String student_id, String name, int semester, String batch_id, String isAdmitGenerated) {
	        this.student_id = student_id;
	        this.name = name; // Correctly assign the name
	        this.semester = semester;
	        this.batch_id = batch_id;
	        this.isAdmitGenerated = isAdmitGenerated;
	    }

	    public String getStudent_id() {
	        return student_id;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getSemester() {
	        return semester;
	    }

	    public String getBatch_id() {
	        return batch_id;
	    }

	    public String getIsAdmitGenerated() {
	        return isAdmitGenerated;
	    }
	}


}
