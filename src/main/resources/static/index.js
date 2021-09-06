const AddPatientModal = document.getElementById("AddPatientModal");
const updatePatient = document.getElementById("updatePatientModal");

AddPatientModal.addEventListener("click", () => {
    fetch("http://localhost:8080/patient/addPatientModal").then(res => {
        console.log(res);
        if (res.ok) {
            res.json().then(data => {
                console.log(data)
            })
        }
    })
})

console.log(updatePatient)