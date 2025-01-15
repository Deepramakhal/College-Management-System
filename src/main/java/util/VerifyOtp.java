package util;
import db.DbConnection;
import java.sql.*;
public class VerifyOtp {
    public static boolean verifyOtp(String userType, String id, String enteredOtp) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbConnection.getConnection();
            String query = "";
            if ("ADMIN".equals(userType)) {
                query = "SELECT otp, otp_expiry FROM admin WHERE id = ? or email =?";
            } else if ("FACULTY".equals(userType)) {
                query = "SELECT otp, otp_expiry FROM faculty WHERE id = ? or email = ?";
            } else if ("STUDENT".equals(userType)) {
                query = "SELECT otp, otp_expiry FROM student WHERE id = ? or email = ?";
            } else 
            {return false;}
            ps = con.prepareStatement(query);
            ps.setString(1, id);ps.setString(2, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dbOtp = rs.getString("otp");
                Timestamp otpExpiry = rs.getTimestamp("otp_expiry");
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                if (otpExpiry != null && otpExpiry.before(currentTime)) {
                    deleteOtp(userType, id, con);
                    return false; 
                }
                if (enteredOtp != null && enteredOtp.equals(dbOtp)) {
                    deleteOtp(userType, id, con);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close(); 
                    } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false; 
    }
    private static void deleteOtp(String userType, String id, Connection con) {
        PreparedStatement ps = null;
        try {
            String deleteQuery = "";
            if ("ADMIN".equals(userType)) {
                deleteQuery = "UPDATE admin SET otp = NULL, otp_expiry = NULL WHERE id = ?";
                ps = con.prepareStatement(deleteQuery);
                ps.setString(1, id);
            } else if ("FACULTY".equals(userType)) {
                deleteQuery = "UPDATE faculty SET otp = NULL, otp_expiry = NULL WHERE id = ?";
                ps = con.prepareStatement(deleteQuery);
                ps.setString(1, id);
            } else if ("STUDENT".equals(userType)) {
                deleteQuery = "UPDATE student SET otp = NULL, otp_expiry = NULL WHERE id = ?";
                ps = con.prepareStatement(deleteQuery);
                ps.setInt(1, Integer.parseInt(id));
            }
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
