<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="header.jsp" />
</head>
<body>
	<h1>${ isEdit ? "Modifier un article" : "Créer un article" } :</h1>
	<span>Auteur -> ${author}</span>
	<form:form modelAttribute="article" method="post" action="form.html" onsubmit="validateForm(event)">
		<c:if test="${isEdit}">
			<form:hidden path="id" />
		</c:if>
		<div class="form-group">
			<label for="title">Titre :</label>
			<form:input id="title" path="title" class="form-control" />
		</div>
		<div class="form-group">
			<label for="content">Contenu :</label>
			<form:textarea id="content" path="content"
				class="form-control"></form:textarea>
		</div>
		<button>Valider</button>
		<a href="index.html">Retour à l'accueil</a>
	</form:form>
	<script src="js/form.js"></script>
</body>
</html>