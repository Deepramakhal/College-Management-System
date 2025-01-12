<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Result Confirm</title>
    <link rel="stylesheet" href="../css/ApproveResult.css">
</head>
<body>
    <header>
        <div class="header">
            <div class="header-center">
                <h1 class="batch-name"><%=request.getParameter("batch") %>-SEM<%=request.getParameter("semester") %></h1>
            </div>
            <div class="header-button">
                <form action="../Logout" method="post" id="Log-div"> 
                    <button type="submit" class="logout-button">Logout</button>
                </form>
                <button class="dashboard-btn" onclick="window.location.href='HodDashboard.jsp'">Dashboard</button>
            </div>
        </div>
    </header>
    <main>
       <form action="../admin/GenerateResult" method="post" class="student-card">
            <div id="whole">
                <div class="header-row">
                    <span>Student ID</span>
                    <c:forEach var="sub" items="${subjects}">
                        <span>${sub.subject}</span>
                    </c:forEach>
                </div>
                <c:choose>
                	<c:when test="${not empty marks}">
                		<c:forEach var="entry" items="${marks}">
		                    <div class="data-row">
		                        <p>${entry.key}</p> <!-- Student ID -->
		                        <c:forEach var="mark" items="${entry.value}">
		                            <p>${mark.marks}</p> <!-- Marks for Subjects -->
		                        </c:forEach>
		                        <c:set var="sum" value="0" />
		                        <c:set var="count" value="0" />
		                        <c:forEach var="mark" items="${entry.value}">
		                            <c:set var="sum" value="${sum + mark.marks}" />
		                            <c:set var="count" value="${count + 1}" />
		                        </c:forEach>
		                        <c:set var="SGPA" value="${sum / (count*10)}" />
		                        <input type="hidden" name="student_id" value="${entry.key}">
		                        <input type="hidden" name="sgpa" value="${SGPA}">
		                    </div>
		                </c:forEach>
		                <input type="hidden" name="semester" value="<%=request.getParameter("semester")%>">
		                <input type="hidden" name="batch_name" value="<%=request.getParameter("batch")%>">
		                <input type="submit" value="Generate Result">
                	</c:when>
                	<c:otherwise>
                		<p><i>No marks for approval</i><p>
                	</c:otherwise>
                </c:choose>
                
            </div>
        </form>
    </main>
</body>
</html>
