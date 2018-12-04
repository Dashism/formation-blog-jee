/*
 * Fonction déclenchée par l'événement onmouseenter sur chaque div représentant
 * un article.
 */
function enterDiv(event) {
	var target = event.target || event.currentTarget;
	target.classList.add("withmouse");
}

function leaveDiv(event) {
	var target = event.target || event.currentTarget;
	target.classList.remove("withmouse");
}