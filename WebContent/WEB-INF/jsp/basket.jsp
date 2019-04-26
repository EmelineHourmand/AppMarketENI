<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Basket</title>
</head>
<body>
	<%@ include file="../html/header.html"%>
	<h2>Votre panier</h2>
	<div class="main-content">Liste des articles</div>
	<footer>
		<h2>Footer</h2>
		<form action="${pageContext.request.contextPath}" method="POST">
			<input type="submit" value="Home" />
		</form>
	</footer>
</body>
</html>