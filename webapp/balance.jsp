<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <title>Available Balance</title>
</head>
<body>
    <% 
        long balance=(long)request.getAttribute("balance");
    %>
    <p>Available Balance is <%= balance %></p>
    <br><br>
    <a href="../DummyMain.html" style="text-align:center;">Go to Main Page</a>
</body>
</html>
