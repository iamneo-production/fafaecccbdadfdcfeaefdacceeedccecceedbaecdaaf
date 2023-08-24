// Retrieve registered user data from local storage
var storedUserData = localStorage.getItem('registeredUser');

if (storedUserData) {
    var userData = JSON.parse(storedUserData);
    var usernameElement = document.getElementById('username');
    var role = document.getElementById("role");

    if (usernameElement && role) {
        usernameElement.textContent = userData.username;
        role.textContent = userData.role;
    }
}
