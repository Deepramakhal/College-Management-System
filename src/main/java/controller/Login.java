package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;
import db.DbConnection;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        String role = request.getParameter("role");

        if (email == null || password == null || id == null || role == null) {
            response.sendRedirect("Login.jsp?msg=Invalid input parameters");
            return;
        }

        try (Connection con = DbConnection.getConnection()) {
            String table = getTableName(role);
            String redirectPage = getDashboardPage(role);

            if (table == null || redirectPage == null) {
                response.sendRedirect("Login.jsp?msg=Invalid role specified");
                return;
            }
            String query = (role.equals("STUDENT")||role.equals("FACULTY"))
                    ? "SELECT * FROM " + table + " WHERE email=? AND id=BINARY ?": "SELECT * FROM " + table + " WHERE email=? AND id=BINARY ? AND admin_type=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, id);
            if (!role.equals("STUDENT") && !role.equals("FACULTY")) {
                ps.setString(3, role); 
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pwd = rs.getString("password");
                if (BCrypt.checkpw(password, pwd)) {
                    HttpSession session = request.getSession();
                    switch (role) {
                        case "HOD":
                        case "PRINCIPAL":
                            Admin admin = new Admin(rs.getString("id"), rs.getString("name"), rs.getString("email"),
                                    rs.getString("phone"), rs.getString("admin_type"), rs.getString("profile_image"), rs.getString("address"));
                            session.setAttribute("admin", admin);
                            break;
                        case "STUDENT":
                            Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                                    rs.getString("phone"), rs.getString("guardian_name"), rs.getString("gender"),
                                    rs.getString("dob"), rs.getString("batch_id"), rs.getInt("current_sem"), rs.getString("profile_image"), rs.getString("address"));
                            session.setAttribute("student", student);
                            break;
                        case "FACULTY":
                            Faculty faculty = new Faculty(rs.getString("id"), rs.getString("name"), rs.getString("email"),
                                    rs.getString("phone"), rs.getString("course_id"),rs.getString("profile_image"), rs.getString("address"));
                            session.setAttribute("faculty", faculty);
                            break;
                        default:
                            throw new Exception("Unexpected role");
                    }
                    response.sendRedirect(request.getContextPath() + redirectPage);
                } else {
                    response.sendRedirect("Login.jsp?msg=Invalid password");
                }
            } else {
                response.sendRedirect("Login.jsp?msg=Invalid id or email");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Login.jsp?msg=Internal server error, please try again later");
        }
    }
    private String getTableName(String role) {
        switch (role) {
            case "HOD":
            case "PRINCIPAL":
                return "admin";
            case "STUDENT":
                return "student";
            case "FACULTY":
                return "faculty";
            default:
                return null;
        }
    }
    private String getDashboardPage(String role) {
        switch (role) {
            case "HOD":
                return "/admin/HodDashboard.jsp";
            case "PRINCIPAL":
                return "/admin/PrincipalDashboard.jsp";
            case "STUDENT":
                return "/student/StudentDashboard.jsp";
            case "FACULTY":
                return "/faculty/FacultyDashboard.jsp";
            default:
                return null;
        }
    }
}