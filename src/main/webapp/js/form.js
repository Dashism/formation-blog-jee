// var title = document.getElementById("title")
var title = $("#title")[0];
var content = $("#content")[0];

function validateForm(event) {
	console.log("Evenement de soumission du form : ", event);
	if (!title.value || !content.value) {
		event.preventDefault();
		alert("Veuillez remplir les champs avant de valider");
	}
}