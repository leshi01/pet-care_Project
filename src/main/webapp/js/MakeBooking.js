var KeeperToBook = JSON.parse(sessionStorage.getItem('KeeperToBook'));
var OwnerWhoBooks = JSON.parse(sessionStorage.getItem('loggedInUser'));
var Pet = JSON.parse(sessionStorage.getItem('PetOfLoggedInOwner'));

document.getElementById("formHeader").innerHTML = "Book " + KeeperToBook.firstname + " " + KeeperToBook.lastname;


function MakeBooking() {

    const myForm = document.getElementById('bookingForm');
    const formData = new FormData(myForm);

    const data = {};

    data.owner_id = OwnerWhoBooks.owner_id;
    data.pet_id = Pet.pet_id;
    data.keeper_id = KeeperToBook.keeper_id;
    if (Pet.type === "cat"){
        data.price = KeeperToBook.catprice;
    }else{
        data.price = KeeperToBook.dogprice;
    }

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

    xhr.open('POST', 'Booking?');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(data));
}