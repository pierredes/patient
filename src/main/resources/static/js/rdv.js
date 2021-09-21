const btn_suppression = document.getElementsByClassName("suppression");

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