window.onload = function () {
    getAllKeepers();
};

document.getElementById('edit-user').addEventListener('click', function () {
    window.location.href = 'EditUser.html';
});

document.getElementById('add-pet').addEventListener('click', function () {
    window.location.href = 'AddPet.html';
});

function createTableFromJSON(data){
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
    return html;

}

function getAllKeepers() {
    // Make an AJAX request to fetch pet keepers
    $.ajax({
        url: 'GetAllPetKeepers?', 
        type: 'GET',
        dataType: 'json',
        success: function (data) {

            displayPetKeepers(data);

        },
        error: function (error) {
            console.error('Error fetching pet keepers:', error);
        }
    });
}

// Function to display pet keepers
function displayPetKeepers(petKeepers) {
    var petKeepersList = $('#context');
    petKeepersList.empty();

    if (petKeepers && petKeepers.length > 0) {
        let listHTML = '<ul class="pet-keepers-list">';

        petKeepers.forEach(function (petKeeper) {
            // Assuming petKeeper.id is available and can be used as a unique identifier
            listHTML += `<li><button>${petKeeper.firstname} ${petKeeper.lastname}</button></li>`;
        });

        listHTML += '</ul>';
        petKeepersList.append(listHTML);
    } else {
        petKeepersList.append('<p>No pet keepers found.</p>');
    }
}
