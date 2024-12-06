package cms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				PreparedStatement ps=con.prepareStatement("select password from admin where email=? and id=?");
                ps.setString(1, email);
				ps.setString(2, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					if(rs.getString(1).equals(password))
					{
						HttpSession s=request.getSession();
						s.setAttribute("uid", id);
						s.setAttribute("role", role);
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
			else if(role.equals("STUDENT"))
			{
				PreparedStatement ps=con.prepareStatement("select password from student where email=? and id=?");
				ps.setString(1, email);
				ps.setInt(2, Integer.parseInt(id));
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					if(rs.getString(1).equals(password))
					{
						HttpSession s=request.getSession();
						s.setAttribute("uid", id);
						s.setAttribute("role", role);
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
					if(rs.getString(1).equals(password))
					{
						HttpSession s=request.getSession();
						s.setAttribute("uid", id);
						s.setAttribute("role", role);
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
