package Authentication;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (Auth.isAdminLoggedIn(req, res) instanceof models.Admin) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/Login.jsp?error=Admin login required.");
        }
    }
}