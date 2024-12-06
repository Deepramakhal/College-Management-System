package cms;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Admin;
import models.Faculty;
import models.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String id=request.getParameter("id");
		String role=request.getParameter("role");
		
		try(Connection con=dbConnection.getConnection())
		{
			if(role.equals("HOD")||role.equals("PRINCIPAL"))
			{
				PreparedStatement ps=con.prepareStatement("select * from admin where email=? and id=?");
                ps.setString(1, email);
				ps.setString(2, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					String pwd = rs.getString("password"); 
					 if (BCrypt.checkpw(password, pwd))
					{
						Admin admin=new Admin(rs.getString("id"),rs.getString("name"),rs.getString("email"),rs.getString("phone"),rs.getString("adminType"),rs.getString("password"),rs.getString("profileImage"),rs.getString("address"));
						HttpSession s=request.getSession();
						s.setAttribute("admin", admin);
						response.sendRedirect("adminDash.jsp");
					}
					else
					{
						response.sendRedirect("login.jsp?msg=invalid password");
					}
				}
				
				else
				{
					response.sendRedirect("login.jsp?msg=invalid id or email");
				}
			}
			else if(role.equals("STUDENT"))
			{
				PreparedStatement ps=con.prepareStatement("select password from student where email=? and id=?");
				ps.setString(1, email);
				ps.setInt(2, Integer.parseInt(id));
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					String pwd = rs.getString(1);
					 if (BCrypt.checkpw(password, pwd))
					{
						Student student = new Student(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("phone"),rs.getString("guardian_name"),rs.getString("gender"),rs.getString("dob"),rs.getString("batch_id"),rs.getInt("current_sem"),rs.getString("password"),rs.getString("profileImage"),rs.getString("address"));
						HttpSession s=request.getSession();
						s.setAttribute("student",student);
						response.sendRedirect("index.jsp");
					}
					else
					{
						response.sendRedirect("login.jsp");
					}
				}
				else
				{
					response.sendRedirect("login.jsp");
				}
			}
			else if(role.equals("FACULTY"))
			{
				PreparedStatement ps=con.prepareStatement("select password from student where email=? and id=?");
				ps.setString(1, email);
				ps.setString(2, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					String pwd = rs.getString("password");
					 if (BCrypt.checkpw(password, pwd))
					{
						Faculty faculty = new Faculty(rs.getString("id"),rs.getString("name"),rs.getString("email"),rs.getString("phone"),rs.getString("course_id"),rs.getString("password"),rs.getString("profile_image"),rs.getString("address"));
						HttpSession s=request.getSession();
						s.setAttribute("faculty", faculty);
						response.sendRedirect("index.jsp");
					}
					else
					{
						response.sendRedirect("login.jsp");
					}
				}
				else
				{
					response.sendRedirect("login.jsp");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	}