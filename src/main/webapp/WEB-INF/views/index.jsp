<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="header.jsp" />
	
	<link rel="stylesheet" href="css/index.css">
	<script src="js/index.js"></script>
</head>
<body>
	<div>
		<a href="form.html">Créer un nouvel article</a>
		<a href="logout.html">Déconnexion</a>
	</div>
	<h1>Liste des articles :</h1>
	<div>
		<c:forEach var="article" items="${articles}">
			<div class="article" onmouseenter="enterDiv(event)"
				onmouseleave="leaveDiv(event)">
				<h2>
					${article.title}
					<a href="delete.html?id=${article.id}">X</a>	
				</h2>
				<p>${article.content}</p>
			</div>
		</c:forEach>
	</div>
</body>
</html>