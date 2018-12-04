/*
 * Fonction déclenchée par l'événement onmouseenter sur chaque div représentant
 * un article.
 */
function enterDiv(event) {
	var target = event.target || event.currentTarget;
	target.style.backgroundColor = '#7D70BA';
}

function leaveDiv(event) {
	var target = event.target || event.currentTarget;
	target.style.backgroundColor = '#DEC1FF';
}