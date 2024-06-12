const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const database = require('./config/database.config');


const app = express();

//Database Connection
database.connect();

//Logger -> Request
app.use(logger('dev'));

//Body Parser
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

//Static Router
app.use(express.static(path.join(__dirname, 'public')));


module.exports = app;
