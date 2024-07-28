var LoggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));

function AddPet() {

    const myForm = document.getElementById('AddPetForm');
    const formData = new FormData(myForm);

    const data = {};

    data.owner_id = LoggedInUser.owner_id;

    formData.forEach((value, key) => {
        data[key] = value;
    });

    const xhr = new XMLHttpRequest();

    xhr.onload = function () {

        if (xhr.status === 200) {
            window.location.href = 'PetOwner.html';
        } else {
            console.log("ERROR: No pet added" + xhr.status);
        }

    };

    xhr.open('POST', 'RegisterPet?');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(data));
}