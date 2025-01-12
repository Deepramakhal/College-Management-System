<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <link rel="stylesheet" href="css/ForgetPassword.css">
</head>
<body>
<%String otpSent= request.getParameter("action");  %>
    <div class="container">
        <h2>Forget Password</h2>
        <%if("null".equals(otpSent)||otpSent == null){ %>
        	<form action="SendOtp" method="get">
        	<div class="form-group">
                <select id="position" name="user" required style="width:100%;padding:10px;">
                <option value="" disabled selected>You are</option>
                <option value="ADMIN">PRINCIPAL</option>
                <option value="ADMIN">HOD</option>
                <option value="FACULTY">FACULTY</option>
                <option value="STUDENT">STUDENT</option>
 				</select>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" placeholder="Enter registered email" name="mail" required>
            </div>
            <input type="hidden" name="action" value="Forget password"><input type="hidden" name="output" value="ForgetPasssword.jsp">
            <button type="submit" id="sendOtpBtn" class="btn">Send OTP</button></form>
        <%} %>
            <%if("Forget password".equals(otpSent)){ %><script>timer()</script>
            <fieldset style="padding:10px;border-radius:10px;"><legend>Generate new password</legend>
            	<form action="ForgetPassword" method="post">
	            <div class="otpSection" id="otpSection">
	            	<div class="form-group">
	                <label for="new-password">New Password</label>
	                <input type="password" id="new-password" placeholder="new-password" name="newpw" required>
	            </div>
	            <div class="form-group">
	                <label for="confirm-password">Confirm Password</label>
	                <input type="password" id="confirm-password" placeholder="Re-type password" name="confirmpw" required>
	            </div>
	                <label for="otp">Enter Valid OTP</label>
	                <input type="text" id="otp" placeholder="Enter the secret code sent to registered email" name="otp" required>
	                <div class="timer" id="timer">(*OTP valid for 5 minutes)</div>
	            </div>
	            <input type="hidden" name="mail" value="<%=request.getParameter("mail")%>">
	            <input type="hidden" name="user" value="<%=request.getParameter("user")%>">
	            <button type="submit" name="action" value="changePassword" class="btn" id="changePasswordBtn">Change Password</button>
	        	</form>
            </fieldset>
            <%} %>
    </div>
    <script>
    </script>
</body>
</html>
