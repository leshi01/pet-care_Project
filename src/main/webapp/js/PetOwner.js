var LoggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));
var OwnerPet;

window.onload = function () {
    getPets(LoggedInUser.owner_id);
    setTimeout(getAllKeepers, 500);
};

document.getElementById('edit-user').addEventListener('click', function () {
    window.location.href = 'EditUser.html';
});

document.getElementById('add-pet').addEventListener('click', function () {
    window.location.href = 'AddPet.html';
});


function getPets(ownerId) {
    $.ajax({
        url: 'GetOwnerPet?',
        type: 'GET',
        dataType: 'json',
        data: { ownerId: LoggedInUser.owner_id },
        success: function (data) {
            displayPets(data);
        },
        error: function (error) {
            console.error('Error fetching pets:', error);
        }
    });
}

function displayPets(pets) {

    if (Array.isArray(pets)) {
        pets.forEach(pet => {
            OwnerPet = pet;
        });
    } else {
        console.error('Invalid data format:', pets);
    }

    console.log(OwnerPet);
}

function getAllKeepers() {

    var type;
    if (OwnerPet.type === "cat") {
        type = "catKeepers";
    } else {
        type = "dogKeepers";
    }

    $.ajax({
        url: 'GetAllPetKeepers?', 
        type: 'GET',
        dataType: 'json',
        data: { type: type},
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
