const email = document.getElementById("email");
const envoyer_add_edit_btn = document.getElementById("envoyer_add_edit");
const btn_suppression = document.getElementsByClassName("suppression");

const doAjax = () => {
    	
    	let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                email.style.borderColor="red";
				envoyer_add_edit_btn.disabled=true;
            } else {
				email.style.borderColor="blue";
				envoyer_add_edit_btn.disabled=false;
			}
        };

        let emailValue = email.value;
        xhr.open("GET", "/patient/check?email="+emailValue, true);
        xhr.send();
    }

    
    email.onchange=doAjax;

for(let i = 0; i<btn_suppression.length; i++) {
let id = btn_suppression[i].getAttribute('data-id');
	btn_suppression[i].addEventListener('click', (e) => {
		if(confirm("êtes-vous sur de vouloir supprimer ce patient ?")) {
			fetch("http://localhost:8080/patient/delete/" + id).then(res => {				
				if(res.ok) {
					window.location = "/patient/list?success";
				}
				
			})
		} else {
			return false;
		}
	})
}