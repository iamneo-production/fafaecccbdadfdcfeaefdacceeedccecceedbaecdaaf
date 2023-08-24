function validateRegistration() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    var role = document.getElementById("role").value;

    // Password validation rules
    var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

    var passwordValidation = document.getElementById("passwordValidation");

    if (password !== confirmPassword) {
        passwordValidation.textContent = "Passwords do not match";
        return false;
    }

    if (!password.match(passwordRegex)) {
        passwordValidation.textContent = "Password must be at least 8 characters long and contain both letters and numbers";
        return false;
    }

    // Clear password validation error
    passwordValidation.textContent = "";

    if (role !== "admin" && role !== "organizer") {
        alert("Invalid role selected.");
        return false;
    }

    // Store registration data in local storage
    var userData = {
        username: username,
        password: password,
        role: role
    };
    
    localStorage.setItem('registeredUser', JSON.stringify(userData));

    alert("Registration successful!");
    
    
    // Redirect to login page
    location.href = 'login.html';

    
    return false; // Prevent form submission
}

module.exports = {
    validateRegistration
  };