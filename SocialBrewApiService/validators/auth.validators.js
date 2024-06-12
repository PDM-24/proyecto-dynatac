const { body } = require('express-validator');

const validators = {};
const passwordRegexp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,32}$/;


validators.registerValidator = [
   body('username')
      .notEmpty().withMessage('Username is required')
      .isLength({ min: 5, max: 32 }).withMessage('Username must be between 5 to 32 characters'),
   
   body('email')
      .notEmpty().withMessage('Email is required')
      .isEmail().withMessage('Email is invalid'),

   body('password')
      .notEmpty().withMessage('Password is required')
      .matches(passwordRegexp).withMessage('Password must contain at least one uppercase letter, one lowercase letter, one number and must be at least 8 characters long')
];

module.exports = validators;