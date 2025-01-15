package Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Admin;
import models.Faculty;
import models.Student;

public class Auth {

    public static Object isAdminLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            return (Admin) session.getAttribute("admin"); 
        }
        return false; 
    }
    public static Object isFacultyLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("faculty") != null) {
            return (Faculty) session.getAttribute("faculty");
        }
        return false;
    }
    public static Object isStudentLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("student") != null) {
            return (Student) session.getAttribute("student"); 
        }
        return false;
    }
}
