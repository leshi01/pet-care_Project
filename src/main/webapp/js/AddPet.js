var LoggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));

function AddPet() {

    const myForm = document.getElementById('AddPetForm');
    const formData = new FormData(myForm);

    // Correctly convert FormData to a JSON object
    const data = {};

    data.owner_id = LoggedInUser.owner_id;

    formData.forEach((value, key) => {
        data[key] = value;
    });



    const xhr = new XMLHttpRequest();

    // Simplified event handler for the response
    xhr.onload = function () {

        if (xhr.status === 200) {

            console.log(xhr.responseText);
        } else {


        }
    };
    // Set up and send the request
    xhr.open('POST', 'RegisterPet?');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(data));
}