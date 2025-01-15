package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbConnection;

public class GetCourseId {
	public static String courseIds(String course) {
		try(Connection con = DbConnection.getConnection()){
			String sql = "select id from course where name=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, course);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("id");
			}
			rs.close();
			ps.close();
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}