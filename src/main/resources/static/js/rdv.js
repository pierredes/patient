const btn_suppression = document.getElementsByClassName("suppression");
const btn_edit = document.getElementsByClassName("update");
const input_date = document.getElementById("date");
const input_type = document.getElementById("type");
const input_duree = document.getElementById("duree");
const input_note = document.getElementById("note");
const input_patient = document.getElementById("patient");
const modal = document.getElementById("modal-content");

for(let i=0; i<btn_suppression.length; i++) {
	let id = btn_suppression[i].getAttribute('data-id');
	btn_suppression[i].addEventListener('click', () => {
		if(confirm("êtes vous sur de vouloir supprimer ce rendez-vous ?")) {
			fetch("http://localhost:8080/rdv/delete/" + id).then(res => {
				if(res.ok) {
					window.location = "/rdv/list?success";
				}
			}) 
		} else {
			return false;
		}
	})
}

for(let i=0; i<btn_edit.length; i++) {
	let id = btn_suppression[i].getAttribute('data-id');
	btn_edit[i].addEventListener('click', () => {
			fetch("/rdv/edit/" + id).then(res => {
				if(res.ok) {
					res.text().then((data) => {
						modal.innerHTML = data;
					})
				} else {
					console.log("pas ok")
				}
			}) 
	})
}