<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="header.jsp" />
</head>
<body>
	<h1>Créer un article :</h1>
	<span>Auteur -> ${author}</span>
	<form method="post" action="" onsubmit="validateForm(event)">
		<div class="form-group">
			<label for="title">Titre :</label>
			<input id="title" name="title" class="form-control">
		</div>
		<div class="form-group">
			<label for="content">Contenu :</label>
			<textarea id="content" name="content"
				class="form-control"></textarea>
		</div>
		<button>Valider</button>
		<a href="index.html">Retour à l'accueil</a>
	</form>
	<script src="js/form.js"></script>
</body>
</html>