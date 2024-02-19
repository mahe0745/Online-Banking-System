<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
   <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
             background-image: url('https://www.transparenttextures.com/patterns/45-degree-fabric-dark.png');
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            max-width: 90%;
            overflow: auto;
        }

        h2 {
            font-size: 20px;
            margin-bottom: 20px;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            font-size: 14px;
        }

        input, select {
            width: calc(100% - 16px);
            padding: 10px;
            box-sizing: border-box;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            cursor: pointer;
            border: none;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <form action="../controller/newuser" method="post">
        <h2>Account Creation</h2>
        <% Object msg=request.getAttribute("msg") ;
        	if(msg!=null){
        %>
        <h4><%= request.getAttribute("msg")  %></h4>
        <%} %>
        <label for="accountHolderName">Account Holder Name:</label>
        <input type="text" id="accountHolderName" name="accountHolderName" required>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password (Minimum 8 characters, one lowercase, one uppercase, one digit):</label>
        <input type="password" id="password" name="password" required>
        <% Object password_msg=request.getAttribute("password_msg");
        	if(password_msg!=null){
        %>
        <font size="2" color="red"> <%= request.getAttribute("password_msg") %></font>	<br>
        <% } %>		
        
        <% Object upper_msg=request.getAttribute("upper_msg");
        	if(upper_msg!=null){
        %>	
        <font size="2" color="red"> <%= request.getAttribute("upper_msg") %></font>	<br>
        <% }%>
        
        <%
            Object lower_msg=request.getAttribute("lower_msg");
        	if(lower_msg!=null){
        %>
        <font size="2" color="red"> <%= request.getAttribute("lower_msg") %></font>	<br>
        <%} %>
        
        <%
        	Object digit_msg=request.getAttribute("digit_msg");
        	if(digit_msg!=null){
        %>
        <font size="2" color="red"> <%= request.getAttribute("digit_msg") %></font>	<br>
		<%} %>
		
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <% Object email_msg= request.getAttribute("email_msg");
        	if(email_msg!=null){
        %>
        <font size="2" color="red"> <%= request.getAttribute("email_msg") %></font>	<br>
		<%} %>
        <label for="mobileNumber">Mobile Number:</label>
        <input type="text" id="mobileNumber" name="mobileNumber" required>
        <% Object mob_msg=request.getAttribute("mob_msg");
        	if(mob_msg!=null){
        %>
        
        <font size="2" color="red"> <%= request.getAttribute("mob_msg") %></font>	<br>
		<%} %>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required>

        <label for="accountType">Account Type:</label>
        <select id="accountType" name="accountType">
            <option value="current">Current</option>
            <option value="savings">Savings</option>
        </select>

        <input type="submit" value="Register">
    </form>

</body>
</html>
	