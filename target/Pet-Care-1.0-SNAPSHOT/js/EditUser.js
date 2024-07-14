

// Function to handle form submission and AJAX request
function PostUser() {
    const myForm = document.getElementById('formForEdit');

    const formData = new FormData(myForm);

   formData.forEach((value, key) => {
       formData.append(key, value);
   });


    const xhr = new XMLHttpRequest();

    // Event handler for the response
    xhr.onload = function () {
        const ajaxContent = $('#ajaxContent');
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                const responseData = JSON.parse(xhr.responseText);
                login();
            } else {
                ajaxContent.html('Request failed. Returned status of ' + xhr.status + "<br>");
                try {
                    const responseData = JSON.parse(xhr.responseText);
                    for (const key in responseData) {
                        if (responseData.hasOwnProperty(key)) {
                            ajaxContent.append(`<p style='color:red'>${key} = ${responseData[key]}</p>`);
                        }
                    }
                } catch (e) {
                    ajaxContent.append(`<p style='color:red'>Error parsing response: ${xhr.responseText}</p>`);
                }
            }
        }
    };

    // Prepare data for sending
    const data = {};
    formData.forEach((value, key) => (data[key] = value));


    // Set up and send the request
    xhr.open('POST', 'Register?');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(data));
}