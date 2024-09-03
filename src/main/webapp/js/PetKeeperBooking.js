var LoggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));
var bookingid;

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
    const container = document.getElementById('edit_bookings_from_keeper');

    if (Array.isArray(bookings)) {
        bookings.forEach(booking => {
            bookingid = booking.booking_id;
            if (booking.status === "requested") {
                const bookingDiv = document.createElement('div');
                bookingDiv.className = 'booking';

                bookingDiv.innerHTML = `
                    <p>Owner ID: ${booking.owner_id}</p>
                    <p>Pet ID: ${booking.pet_id}</p>
                    <p>From Date: ${booking.fromdate}</p>
                    <p>To Date: ${booking.todate}</p>
                    <p>Status: ${booking.status}</p>
                    <p>Price: ${booking.price + "$"}</p>
                    
                    <button id="acceptButton">Accept</button>
                    <button id="rejectButton">Reject</button>
                `;

                container.appendChild(bookingDiv);
            }
        });
    } else {
        console.error('Invalid data format:', bookings);
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const container = document.getElementById('edit_bookings_from_keeper');

    container.addEventListener('click', function (event) {
        if (event.target && event.target.id === 'acceptButton') {
            handleAccept("accepted");
        } else if (event.target && event.target.id === 'rejectButton') {
            handleAccept("rejected");
        }
    });
});

function handleAccept(status) {
    $.ajax({
        url: 'GetBookings?',
        type: 'POST',
        dataType: 'json',
        data: {
            BookingId: bookingid,
            Status: status
        },
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.error('Error fetching pets:', error);
        }
    });
}
