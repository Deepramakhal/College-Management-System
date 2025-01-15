package Pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Batch;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import db.DbConnection;
@WebServlet(urlPatterns = {"/admin/GetBatches", "/faculty/GetBatches"})
public class GetBatches extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String course = request.getParameter("course");
		String output = request.getParameter("output");
		ArrayList<Batch> batches = new ArrayList<>();
		try(Connection con = DbConnection.getConnection()) {
			String sql = "SELECT * FROM batch where name like ? order by name desc;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, course+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				batches.add(new Batch(rs.getString("name"), rs.getString("starting_year"), rs.getString("ending_year")));
			}
			request.setAttribute("batches", batches);
			request.getRequestDispatcher(output+".jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
