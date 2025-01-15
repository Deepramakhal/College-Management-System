package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbConnection;

public class GetSemesterFromBatch {
	public static int getCurrentSemester(String batch_name) {
	    try (Connection con = DbConnection.getConnection()) {
	        String sql = "SELECT CASE WHEN CURRENT_DATE >= DATE(CONCAT(ending_year, '-07-01')) THEN 'Batch Ended' "
	                + "ELSE CEIL(TIMESTAMPDIFF(MONTH, DATE(CONCAT(starting_year, '-07-01')), CURRENT_DATE) / 6) END AS current_semester "
	                + "FROM batch WHERE name = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, batch_name);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) - 1;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
}