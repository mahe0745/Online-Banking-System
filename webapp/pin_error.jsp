<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PIN Change</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
	background-image:
		url('https://www.transparenttextures.com/patterns/45-degree-fabric-dark.png');
}

.container {
	max-width: 400px;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2, label, input, input[type="submit"], p {
	display: block;
	margin: 10px 0;
	font-size: 14px; /* Adjust the font size as needed */
}

h2, p {
	text-align: center;
	color: #333;
}

input {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: #4CAF50;
	color: white;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #45a049;
}
</style>
</head>
<body>

	<div class="container">
		<form action="../controller/pin" method="post">
			<h2>PIN Change</h2>

			<label for="currentPin">Current PIN:</label> <input type="password"
				id="currentPin" name="currentPin" required>
			<%= request.getAttribute("pin") %>
			
			<label for="newPin">New PIN:</label> <input type="password"
				id="newPin" name="newPin" required>
			<%= request.getAttribute("newpin") %>
			
			<label for="confirmPin">Confirm New PIN:</label> <input
				type="password" id="confirmPin" name="confirmPin" required>
			<%= request.getAttribute("confirmpin") %>
			<%= request.getAttribute("pinandConfirmPin") %>
			
			<input type="submit" value="Change PIN">
		</form>
	</div>

</body>
</html>
