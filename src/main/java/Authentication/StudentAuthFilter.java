package Authentication;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/student/*"})
public class StudentAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        boolean isResultPage = uri.endsWith("/student/Result.jsp");

        if (isResultPage) {
            if (Auth.isAdminLoggedIn(req, res) instanceof models.Admin ||
                Auth.isStudentLoggedIn(req, res) instanceof models.Student) {
                chain.doFilter(request, response);
                return;
            }
        } else {
            if (Auth.isStudentLoggedIn(req, res) instanceof models.Student) {
                chain.doFilter(request, response);
                return;
            }
        }
        res.sendRedirect(req.getContextPath() + "/Login.jsp?error=Student login required.");
    }
}
