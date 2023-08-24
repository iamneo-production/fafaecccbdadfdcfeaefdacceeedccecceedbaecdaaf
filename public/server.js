const express = require('express');
const app = express();
const path = require('path');
const port = 8081;

// Serve the Registration HTML file
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '', 'registeration.html'));
});

// Serve the Login HTML file
app.get('/login.html', (req, res) => {
    res.sendFile(path.join(__dirname, '', 'login.html'));
});
app.get('/home.html', (req, res) => {
  res.sendFile(path.join(__dirname, '', 'home.html'));
});

// Serve the CSS file with the correct MIME type
app.get('/style.css', (req, res) => {
    res.setHeader('Content-Type', 'text/css');
    res.sendFile(path.join(__dirname, '', 'style.css'));
});

// Serve the JavaScript files with the correct MIME type
app.get('/registrationValidation.js', (req, res) => {
    res.setHeader('Content-Type', 'application/javascript');
    res.sendFile(path.join(__dirname, '', 'registrationValidation.js'));
});

app.get('/loginValidation.js', (req, res) => {
    res.setHeader('Content-Type', 'application/javascript');
    res.sendFile(path.join(__dirname, '', 'loginValidation.js'));
});

app.get('/home.js', (req, res) => {
  res.setHeader('Content-Type', 'application/javascript');
  res.sendFile(path.join(__dirname, '', 'home.js'));
});

// Serve static files from the 'public' directory
app.use(express.static(path.join(__dirname, 'public')));

// Start the server
app.listen(port, () => {
    console.log(`Listening on port ${port}!`);
});
