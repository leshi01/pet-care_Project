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
        data: { ownerId: ownerId },
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
            sessionStorage.setItem('PetOfLoggedInOwner', JSON.stringify(pet));
            OwnerPet = JSON.parse(sessionStorage.getItem('PetOfLoggedInOwner'));
        });
    } else {
        console.error('Invalid data format:', pets);
    }
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
        let listHTML = $('<ul class="pet-keepers-list"></ul>');

        petKeepers.forEach(function (petKeeper) {
            let listItem = $('<li></li>');
            let button;

            if (OwnerPet.type == "cat"){
                button = $('<button></button>').text(`${petKeeper.firstname} ${petKeeper.lastname} ${petKeeper.catprice + "$"}`);
            }else{
                button = $('<button></button>').text(`${petKeeper.firstname} ${petKeeper.lastname} ${petKeeper.dogprice + "$"}`);
            }

            // Add event listener to the button
            button.on('click', function() {
                sessionStorage.setItem('KeeperToBook', JSON.stringify(petKeeper));
                window.location.href = 'MakeBooking.html';
            });

            listItem.append(button);
            listHTML.append(listItem);
        });

        petKeepersList.append(listHTML);
    } else {
        petKeepersList.append('<p>No pet keepers found.</p>');
    }
}