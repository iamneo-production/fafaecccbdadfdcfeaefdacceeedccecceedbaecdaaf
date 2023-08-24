const { JSDOM } = require('jsdom');
const { validateRegistration } = require('../registrationValidation'); // Update the path accordingly

describe('Registration Form Validation', () => {
    let dom;

    beforeEach(() => {
        dom = new JSDOM(`
        <div class="container">
        <h1>Registration</h1>
        <form id="registrationForm" onsubmit="return validateRegistration();" method="post">
            <input type="text" id="username" name="username" required>

            <input type="password" id="password" name="password" required>
            <span id="passwordValidation" class="validation-message"></span>

            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <span id="passwordValidation1" class="validation-message"></span>


            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="" disabled selected>Select role</option>
                <option value="admin">Admin</option>
                <option value="organizer">Organizer</option>
            </select>

            <input type="submit" value="Register">
        </form>
    </div>
        `);
        global.document = dom.window.document;
        global.window = dom.window;
        global.alert = jasmine.createSpy(); // Mock the alert function
        global.location = {
            assign: jasmine.createSpy() // Mock the assign function for navigation
        };

        // Mock localStorage
        global.localStorage = {
            getItem: jasmine.createSpy(),
            setItem: jasmine.createSpy(),
            removeItem: jasmine.createSpy(),
            clear: jasmine.createSpy()
        };

    });

    afterEach(() => {
        delete global.document;
        delete global.window;
        delete global.alert;
        delete global.location;
        delete global.localStorage;
    });

    it('Week4_Day1_Invalid_Registration_Weak_Password', () => {
      // Mock form inputs with weak password
      document.getElementById('username').value = 'weakUser';
      document.getElementById('password').value = 'password'; // Doesn't meet complexity requirements
      document.getElementById('confirmPassword').value = 'password';
      document.getElementById('role').value = 'admin';

      // Call the validation function
      const result = validateRegistration();

      // Assertions
      expect(result).toBe(false);
      expect(document.getElementById('passwordValidation').textContent).toBe('Password must be at least 8 characters long and contain both letters and numbers');
  });

  it('Week4_Day1_Invalid_Registration_Password_does_not_match', () => {
    // Mock form inputs with weak password
    document.getElementById('username').value = 'weakUser';
    document.getElementById('password').value = 'password1'; // Doesn't meet complexity requirements
    document.getElementById('confirmPassword').value = 'password2';
    document.getElementById('role').value = 'admin';

    // Call the validation function
    const result = validateRegistration();

    // Assertions
    expect(result).toBe(false);
    expect(document.getElementById('passwordValidation').textContent).toBe('Passwords do not match');
  });



  it('Week4_Day1_should_register_for_valid_registration_data', () => {
    // Mock form inputs with valid registration data
    document.getElementById('username').value = 'validUser';
    document.getElementById('password').value = 'Password1';
    document.getElementById('confirmPassword').value = 'Password1';
    document.getElementById('role').value = 'admin';

    // Mock window.location.assign function
    const originalAssign = global.location.assign;
    global.location.assign = jasmine.createSpy();

    // Call the validation function
    const result = validateRegistration();

    // Assertions
    // expect(result).toBe(false); // Since you're preventing form submission
    expect(global.alert).toHaveBeenCalledWith('Registration successful!'); // Check if alert was called with the correct message
    // expect(global.location.assign).toHaveBeenCalledWith('login.html'); // Check if assign was called with the correct URL

    // Check if the current URL matches the expected URL
    // expect(location.href).toBe('login.html');

    // // Restore the original function after the test
    global.location.assign = originalAssign;
});

it('Week4_Day2_should_navigate_to_login_page_with_valid_registration_data', () => {
  // Mock form inputs with valid registration data
  document.getElementById('username').value = 'validUser';
  document.getElementById('password').value = 'Password1';
  document.getElementById('confirmPassword').value = 'Password1';
  document.getElementById('role').value = 'admin';

  // Mock window.location.assign function
  const originalAssign = global.location.assign;
  global.location.assign = jasmine.createSpy();

  // Call the validation function
  const result = validateRegistration();

  // Assertions
  // expect(result).toBe(false); // Since you're preventing form submission
  // expect(global.alert).toHaveBeenCalledWith('Registration successful!'); // Check if alert was called with the correct message
  // expect(global.location.assign).toHaveBeenCalledWith('login.html'); // Check if assign was called with the correct URL

  // Check if the current URL matches the expected URL
  expect(location.href).toBe('login.html');

  // Restore the original function after the test
  global.location.assign = originalAssign;
});

});

const { validateLogin } = require('../loginValidation'); // Update the path accordingly

describe('Login Form Validation', () => {
  let dom;

  beforeEach(() => {
      dom = new JSDOM(`
      <div class="container">
      <h1>Login</h1>
      <form id="loginForm" onsubmit="return validateLogin();" method="post">
          <label for="username">Username:</label>
          <input type="text" id="username" name="username" required>

          <label for="password">Password:</label>
          <input type="password" id="password" name="password" required>

          <input type="submit" value="Login">
      </form>
  </div>
      `);
      global.document = dom.window.document;
      global.window = dom.window;
      global.alert = jasmine.createSpy(); // Mock the alert function
      global.location = {
          assign: jasmine.createSpy() // Mock the assign function for navigation
      };

      // Mock localStorage
      global.localStorage = {
          getItem: jasmine.createSpy(),
          setItem: jasmine.createSpy(),
          removeItem: jasmine.createSpy(),
          clear: jasmine.createSpy()
      };
  });

  afterEach(() => {
      delete global.document;
      delete global.window;
      delete global.alert;
      delete global.location;
      delete global.localStorage;
  });

  it('Week4_Day1_Valid_Login', () => {
      // Mock form inputs
      document.getElementById('username').value = 'validUser';
      document.getElementById('password').value = 'Password1';

      // Mock localStorage with registered user data
      global.localStorage.getItem.and.returnValue(JSON.stringify({
          username: 'validUser',
          password: 'Password1',
          role: 'admin'
      }));

      // Mock alert function
      global.alert.and.stub();

      // Call the validation function
      validateLogin();

      // Assertions
      expect(global.alert).toHaveBeenCalledWith('Login successful! Role: admin');
      // expect(global.location.assign).toHaveBeenCalledWith('login.html');
  });
  it('Week4_Day2_Valid_Login_navigate_to_home_page', () => {
    // Mock form inputs
    document.getElementById('username').value = 'validUser';
    document.getElementById('password').value = 'Password1';

    // Mock localStorage with registered user data
    global.localStorage.getItem.and.returnValue(JSON.stringify({
        username: 'validUser',
        password: 'Password1',
        role: 'admin'
    }));

    // Mock alert function
    global.alert.and.stub();

    // Call the validation function
    validateLogin();

    // Assertions
    // expect(global.alert).toHaveBeenCalledWith('Login successful! Role: admin');
    expect(location.href).toBe('home.html');
});

  it('Week4_Day2_Invalid_User_Login', () => {
        // Mock form inputs for an invalid user
        document.getElementById('username').value = 'invalidUser';
        document.getElementById('password').value = 'InvalidPassword';

        // Mock localStorage with registered user data (not matching the input)
        global.localStorage.getItem.and.returnValue(JSON.stringify({
            username: 'validUser',
            password: 'Password1',
            role: 'admin'
        }));

        // Mock alert function
        global.alert.and.stub();

        // Call the validation function
        validateLogin();

        // Assertions
        expect(global.alert).toHaveBeenCalledWith('Invalid username or password.');
        // expect(global.location.assign).not.toHaveBeenCalled(); // No navigation for invalid login
    });

});
