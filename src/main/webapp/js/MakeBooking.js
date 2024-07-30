var KeeperToBook = JSON.parse(sessionStorage.getItem('KeeperToBook'));
document.getElementById("formHeader").innerHTML = "Book " + KeeperToBook.firstname + " " + KeeperToBook.lastname;
