var LoggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));

// Function to set default values for the form inputs
function setDefaultValues() {

    console.log(LoggedInUser);
    // Example default values
    const defaultValues = {
        firstname: LoggedInUser.firstname,
        lastName: LoggedInUser.lastname,
        username: LoggedInUser.username,
        email: LoggedInUser.email
    };

    // Set default values
    document.getElementsByName('firstname')[0].value = defaultValues.firstname;
    document.getElementsByName('lastName')[0].value = defaultValues.lastName;
    document.getElementsByName('username')[0].value = defaultValues.username;
    document.getElementById('email').value = defaultValues.email;
}

// Call setDefaultValues on page load
document.addEventListener('DOMContentLoaded', setDefaultValues);
// Function to handle form submission and AJAX request
function editUser() {

    console.log(LoggedInUser);

    const myForm = document.getElementById('formForEdit');
    const formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });

    data.user = LoggedInUser.username;
    if (LoggedInUser.owner_id != null) {
        data.type = "owner";
    }
    const xhr = new XMLHttpRequest();

    // Simplified event handler for the response
    xhr.onload = function () {

        if (xhr.status === 200) {

            console.log(xhr.responseText);
        } else {

            try {
                const responseData = JSON.parse(xhr.responseText);
                for (const key in responseData) {
                    if (responseData.hasOwnProperty(key)) {

                    }
                }
            } catch (e) {

            }
        }
    };
    // Set up and send the request
    xhr.open('POST', 'EditUser?');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(data));
}