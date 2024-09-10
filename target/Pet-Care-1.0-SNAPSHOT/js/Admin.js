let context = document.getElementById("context");
let isKeeper;

document.getElementById('petKeepers').addEventListener('click', doPetKeepers);
document.getElementById('petOwners').addEventListener('click', doPetOwners);
document.getElementById('profit').addEventListener('click', doProfits);
document.getElementById('pets').addEventListener('click', doPets);
document.getElementById('users').addEventListener('click', doUsers);

function doPetKeepers() {
    getAllKeepers();
}

function doPetOwners() {
    getAllOwners();
}

function doProfits() {

}

function doPets() {

}

function doUsers() {

}

function getAllKeepers() {
    $.ajax({
        url: 'GetAllPetKeepers?',
        type: 'GET',
        dataType: 'json',
        data: { type: "everyone" },
        success: function (data) {
            isKeeper = true;
            displayUsers(data);
        },
        error: function (error) {
            console.error('Error fetching pet keepers:', error);
        }
    });
}

function getAllOwners() {
    // Make an AJAX request to fetch pet keepers
    $.ajax({
        url: 'GetAllPetOwners?', // Replace with the actual URL to your servlet
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            isKeeper = false;
            displayUsers(data);
        },
        error: function (error) {
            console.error('Error fetching pet keepers:', error);
        }
    });
}

function displayUsers(User) {
    let UserList = $('#context');
    UserList.empty();

    if (User && User.length > 0) {

        let tableHTML = `<table id="table">
                        <tr class="text">
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Edit</th>
                        </tr>`;

        User.forEach(function (user, index) {
            tableHTML += '<tr class="text">' +
                '<td> ' + user.firstname + '</td>' +
                '<td> ' + user.lastname + '</td>' +
                '<td><button class="delete-btn" data-index="' + index + '">Delete</button></td>' +
                '</tr>';
        });

        tableHTML += ('</table>');
        UserList.append(tableHTML);

        // Add event listener for delete buttons
        $('.delete-btn').on('click', function () {
            let userIndex = $(this).data('index');
            deleteUser(User[userIndex]);
        });
    } else {
        UserList.append('<p>No pet keepers found.</p>');
    }
}

function deleteUser(user) {
    if(isKeeper){
        $.ajax({
            url: 'DeleteKeeper',
            type: 'POST',
            data: { id: user.keeper_id },
            success: function (response) {
                // Refresh the user list after deletion
                getAllKeepers();
            },
            error: function (error) {
                console.error('Error deleting user:', error);
            }
        });
    }else{
        $.ajax({
            url: 'DeleteOwner',
            type: 'POST',
            data: { id: user.owner_id },
            success: function (response) {
                // Refresh the user list after deletion
                getAllOwners();
            },
            error: function (error) {
                console.error('Error deleting user:', error);
            }
        });
    }
}