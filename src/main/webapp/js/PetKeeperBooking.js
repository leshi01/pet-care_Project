var LoggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));

window.onload = function () {
    getBookings(LoggedInUser.keeper_id);

};

function getBookings(keeperid) {
    $.ajax({
        url: 'GetBookings?',
        type: 'GET',
        dataType: 'json',
        data: { keeperId: keeperid },
        success: function (data) {
            displayBookings(data);
        },
        error: function (error) {
            console.error('Error fetching pets:', error);
        }
    });
}


function displayBookings(bookings) {

    if (Array.isArray(bookings)) {
        bookings.forEach(booking => {
            console.log(booking);
        });
    } else {
        console.error('Invalid data format:', pets);
    }
}