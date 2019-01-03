<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Entête d'ajout de librairie de balises Spring pour les formulaires. -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
</head>
<body>
	<!-- Utilisation du booléen isEdit ajouté dans le model depuis le controller. -->
	<h1>${ isEdit ? "Modifier un article" : "Créer un article" }:</h1>
	<!-- Utilisation de l'attribut de session 'author' définir dans le controller. -->
	<span>Auteur -> ${author}</span>
	<!-- Utilisation d'un tag Spring pour lier le formulaire à un objet Java de la classe Article. -->
	<form:form modelAttribute="article" method="post" action="form.html"
		onsubmit="validateForm(event)">
		<c:if test="${isEdit}">
			<!-- Prise en compte de l'attribut id seulement pour la mise à jour. -->
			<form:hidden path="id" />
		</c:if>
		<div class="form-group">
			<label for="title">Titre :</label>
			<!-- Utilisation d'un tag Spring pour lier un champ de saisie avec un 
				attribut de l'objet (classe Article) défini dans le modelAttribute du form:form. -->
			<form:input id="title" path="title" class="form-control" />
		</div>
		<div class="form-group">
			<label for="content">Contenu :</label>
			<!-- Utilisation d'un tag Spring pour lier un champ de saisie avec un 
				attribut de l'objet (classe Article) défini dans le modelAttribute du form:form. -->
			<form:textarea id="content" path="content" class="form-control"></form:textarea>
		</div>
		<button>Valider</button>
		<a href="index.html">Retour à l'accueil</a>
	</form:form>
	<script src="js/form.js"></script>
</body>
</html>