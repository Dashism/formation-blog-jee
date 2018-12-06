<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="header.jsp" />
	
	<script src="js/form.js"></script>
</head>
<body>
	<h1>Créer un article :</h1>
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
	</form>
</body>
</html>