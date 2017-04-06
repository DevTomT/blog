
function removeQuestion() {
	document.getElementById("deleteQuestionButton").style.display = "none";
	document.getElementById("delete").style.display = "inline-block";
	document.getElementById("deleteInfo").style.display = "block";
	document.getElementById("notDeleteButton").style.display = "inline-block";
}

function removePostInfo() {
	document.getElementById("deleteInfo").innerHTML = "Na pewno usunąć ten wpis?";
}

function removeCategoryInfo() {
	document.getElementById("deleteInfo").innerHTML = "Usunięcie tej kategorii spowoduje usunięcie WSZYSTKICH wpisów należących do niej. Na pewno usunąć?";
}

function notRemove() {
	document.getElementById("deleteQuestionButton").style.display = "inline-block";
	document.getElementById("delete").style.display = "none";
	document.getElementById("deleteInfo").style.display = "none";
	document.getElementById("notDeleteButton").style.display = "none";
}

