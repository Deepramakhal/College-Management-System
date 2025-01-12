<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload Marks</title>
    <link rel="stylesheet" href="../css/UploadMarks.css">
</head>
<body>
<header>
    <div class="header">
        <div class="header-center">
            <h1 class="course-name"><%=request.getParameter("batch") %></h1>
        </div>
        <div class="header-button">
            <form action="../Logout" method="post" id="Log-div">
                <button type="submit" class="logout-button">Logout</button>
            </form>
            <div style="display:flex; align-items:center"><button class="dashboard-btn" onclick="window.location.href='FacultyDashboard.jsp'">Dashboard</button></div>
        </div>
    </div>
</header>

    <h1>Upload Marks</h1> 
    <form action="../faculty/UploadMarks" method="post">
    <div class="form-row">
        <label>Subject Code:</label>
        <input type="text" name="subject_code" required>
        
        <label>Semester:</label>
        <input type="number" name="semester" required>
         <label>Exam Type:</label>
        <select name="exam_type" id="exm">
        	<option disabled selected>Select Exam type</option>
            <option value="final">Final(out of 100)</option>
            <option value="a1">A1(out of 30)</option>
            <option value="a2">A2(out of 30)</option>
            <option value="a3">A3(out of 30)</option>
        </select>
    </div>
    <table border="1">
        <thead>
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Marks</th>
            </tr>
        </thead>
        <tbody>
            <!-- Check if students list is available -->
            <c:choose>
                <c:when test="${not empty students}">
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>
                                <input type="hidden" name="student_id" value="${student.id}" />
                                ${student.id}
                            </td>
                            <td>${student.name}</td>
                            <td>
                                <input type="number" step="0.01" name="marks" required>
                                <c:set var="batch" value="${batch_id}"/>
                                <input type="hidden" name="batch_id" value="<%=request.getParameter("batch")%>">
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="3">No students found for the given batch.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    <br>
    <button type="submit">Upload Marks</button>
</form>

</body>
</html>
