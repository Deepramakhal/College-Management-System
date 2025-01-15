<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="models.Notice" %>
<%@ page import="db.DbConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
      <link rel="icon" type="image/x-icon" href="upload/logo.png">
    <link rel="stylesheet" href="css/login.css">
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
</head>
<body>
<%ArrayList<Notice> notice = new ArrayList<>();
try (Connection con = DbConnection.getConnection()) {
    String sql = "SELECT title, content, file, DATE(timestamp) as uploadTime from notice order by timestamp desc";
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        notice.add(new Notice(rs.getString("title"),rs.getString("content"),rs.getString("file"),rs.getDate("uploadTime")));}} 
	catch (SQLException e) {e.printStackTrace();}
	request.setAttribute("notices", notice);
 String msg = request.getParameter("msg");
String err = request.getParameter("error"); 
if (err!=null){%><script>alert("<%=err%>"); window.location.href = "Login.jsp"</script><%}
if (msg!=null){%><script>alert("<%=msg%>"); window.location.href = "Login.jsp"</script><%}%>
<div>
	<header>
	   <div id="top-img">
		 <img src="upload/logo.png" alt="college Logo">
			<h1>TECHNOVA INSTITUTE</h1>
                <div id="contact">
                    <h4><svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
                       <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z"/>
                      </svg>in.technovainstitute@gmail.com</h4>  
                      <h4><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-telephone-fill" viewBox="0 0 16 16">
                       <path fill-rule="evenodd" d="M1.885.511a1.745 1.745 0 0 1 2.61.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z"/>
                      </svg>+91 123456789</h4>
                      <button><a href="index.jsp"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-door" viewBox="0 0 16 16">
                        <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4z"/>
                      </svg>Home</a></button>
			</div>
		</div>
        </header>
        <hr class="line">
        <main>
            <div class="login-sec1">
                <button onclick="openPopup('STUDENT')">Student</button>
                <button onclick="openPopup('FACULTY')">Faculty</button>
                <button onclick="openPopup('HOD')">HOD</button>
                <button onclick="openPopup('PRINCIPAL')">Admin/Principal</button>
            </div>
            <div id="login-sec2">
            <div id="notice" class="sub-sec">   
			    <h1>Current Notice</h1>
			    <table style="width:100%; border-collapse: collapse;">
			        <c:forEach var="n" items="${notices}">
			            <tr>
			                <td style=" width:80%;padding:10px; border-bottom:1px solid #ddd;">
			                    <c:choose>
			                        <c:when test="${n.file == null}">
			                            ${n.title}: ${n.content}
			                        </c:when>
			                        <c:otherwise>
			                            <a href="${n.file}" target="_blank" style="color:blue; cursor:pointer; text-decoration:none;">
			                                ${n.title}: ${n.content}</a>
			                        </c:otherwise>
			                    </c:choose>
			                </td>

			                <td style="text-align:right; padding:10px; border-bottom:1px solid #ddd;">
			                    Dated: ${n.timestamp}
			                </td>
			            </tr>
			        </c:forEach>
			    </table> 
			</div>

                <div class="help">
                    <button id="help" onclick="openHelpdeskPopup('Helpdesk') ">Helpdesk</button>
                </div>
                   
            </div>  
        </main>
            <div class="overlay">
                <div class="popup">
                    <div class="popup-content">
                        <span class="close" onclick="closePopup()">&times;</span>
                    </div>
                    <div class="popup-content1">
                        <h2>login</h2>
                        <hr id="hr">
                        <form action="Login" method="post">
                           <input  class="input" type="text" name="id" placeholder="enter user id">
                            <input  class="input" type="email" name="email" placeholder="enter email id">
                            <input  class="input" type="password" name="password" placeholder="enter password">
                            <input id="role" name="role" value="ADMIN" type="hidden">
                            <div id="p"><p><a href="ForgetPasssword.jsp">Forget password?</a></p></div>
                            <div id="button"><button type="submit">login</button></div>
                        </form>
                    </div>
                </div>
            </div>
	<footer>
            <div><p class="copy">Copyrights  &copy; 2024-25 Technova Institute.</p></div> 
        </footer>
    </div>
    <div id="Helpdesk">
                <div id="Helpdesk-content">
                    <div id="closeHelpdesk" onclick="closeHelpdeskPopup('Helpdesk')"><span class="close3">&times;</span></div>
                    <h2>Helpdesk</h2>
                    <div>
                        <p> 24X7 customer care service : +91-22-2087 6123</p>
                        <p>For Technical issues:
                            Technical Helpdesk - 9836319994, 9830602203
                          
                            CA4 Exam Related Issues : 03340585123</p>
                        <p>Other:
                            Dept. of Controller Of Examinations / Transcript / Duplicate Grade Card /
                          
                            Duplicate Certificate : 9163095323
                          
                            Queries pertaining to registration and Migration : 8420033960,
                          
                            03329991526 Extn. - 1526
                          
                            </p>
                    </div>
                </div>
            </div>
    <script src="js/popup.js"></script>
</body>
</html>
