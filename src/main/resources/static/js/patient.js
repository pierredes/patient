const email = document.getElementById("email");

const doAjax = () => {
    	
    	let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                email.style.borderColor="red";
            } else {
				email.style.borderColor="blue";
			}
        };

        let emailValue = email.value;
        xhr.open("GET", "/patient/check?email="+emailValue, true);
        xhr.send();
    }
    
    email.onchange=doAjax;