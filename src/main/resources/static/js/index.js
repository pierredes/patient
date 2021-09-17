const password_aleatoire_btn = document.getElementById("password");
const nom_input = document.getElementById('nom');
const username_input = document.getElementById("username")
const btn_generer_mdp = document.getElementById("mdpAlea");
const progress_bar_input = document.getElementById("progress");
const inputs = document.getElementsByTagName("input");
const envoyer_add_edit_btn = document.getElementById("envoyer_add_edit");

const password_aleatoire = (() => {
    let resultat = "";
    const lettres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    const taille_chaine = Math.round(Math.random() * 10 + 10);
    for(let i = 0; i < taille_chaine; i++) {
        const lettre_random = lettres.charAt(Math.floor(Math.random() * lettres.length));
        resultat += lettre_random; 
    }
    return resultat;
});

const username_alea = (() => {
    const chiffre_alea = Math.round(Math.random() * 900 + 100);
    const username = nom_input.value.concat('.' + chiffre_alea);
    return username; 
})

const progress_bar = (() => {
    let style = 0;
    for(let i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener('blur', (() => {
            if (inputs[i].value != "") {
                style += 25;
                progress_bar_input.style.width=(style + "%");
                progress_bar_input.setAttribute("aria-valuenow", style);
                progress_bar_input.innerText=style.toString().concat("%");
            }
        }))
    }
});

const valider_formulaire = ((texte) => {
    const regex_lettre = /[A-Z]/g;
    const regex_chiffre = /[0-9]/g;
    if (texte.match(regex_lettre) && texte.match(regex_chiffre)) {
        console.log("ok");
    } else {
        console.log("pas ok");
        return false;
    }
})

envoyer_add_edit_btn.addEventListener("click", (e => {
    e.preventDefault();
    valider_formulaire(password_aleatoire_btn.value);
}))

btn_generer_mdp.addEventListener('click', (e) => {
    e.preventDefault();
    password_aleatoire_btn.value = password_aleatoire();
});

nom_input.addEventListener('blur', () => {
    console.log("haha");
    username_input.value = username_alea()
});

progress_bar();



