<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Timestamp" %>



<%
    ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");
	int accountNumber=(Integer)request.getAttribute("accountNumber");
    if (resultSet != null) {
%>
<!DOCTYPE html>
<html>
<head>
    <title>Transaction History</title>
    <style>
        table {border-collapse: collapse; width: 50%; margin: auto;}
        th, td {border: 1px solid black; padding: 10px; text-align: center;}
    </style>
</head>
<body>
    <h2 style='text-align:center;'>Transaction History</h2>
    <table>
        <tr>
            <th>Sender Account</th>
            <th>Receiver Account</th>
            <th>Amount</th>
            <th>Credit/Debit</th>
            <th>Date</th>
        </tr>
        <% while (resultSet.next()) { %>
            <tr>
                <td><%= resultSet.getInt("SenderAccountNumber") %></td>
                <td><%= resultSet.getInt("ReceiverAccountNumber") %></td>
                <td><%= resultSet.getInt("amount") %></td>
                <% if(accountNumber==resultSet.getInt("SenderAccountNumber")) { %>
                    <td>Debit</td>
                <% } else if(accountNumber==resultSet.getInt("ReceiverAccountNumber")) { %>
                    <td>Credit</td>
                <% } %>
                <td><%= resultSet.getTimestamp("dateOfTransaction") %></td>
            </tr>
        <% } %>
    </table>
    <br><br>
    <a href="../DummyMain.html" style='text-align:center;'>Go to Main Page</a>
</body>
</html>
<%
    }
%>
