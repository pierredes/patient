const email = document.getElementById("email");
const envoyer_add_edit_btn = document.getElementById("envoyer_add_edit");

const doAjax = () => {
    	
    	let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                email.style.borderColor="red";
				envoyer_add_edit_btn.removeAttribute("enabled", "");
    			envoyer_add_edit_btn.setAttribute("disabled", "");
            } else {
				email.style.borderColor="blue";
				envoyer_add_edit_btn.removeAttribute("disabled", "");
    			envoyer_add_edit_btn.setAttribute("enabled", "");
			}
        };

        let emailValue = email.value;
        xhr.open("GET", "/patient/check?email="+emailValue, true);
        xhr.send();
    }
    
    email.onchange=doAjax;