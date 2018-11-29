<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Créer un article :</h1>
	<form method="post" action="">
		<div>
			<label for="title">Titre :</label>
			<input id="title" name="title">
		</div>
		<div>
			<label for="content">Contenu :</label>
			<textarea id="content" name="content"></textarea>
		</div>
		<button>Valider</button>
	</form>
</body>
</html>