const { body, param } = require('express-validator')
const validators = {}

validators.createProductValidator = [

   param('identifier')
   .optional()
   .isMongoId().withMessage('id is not valid'),

   body('name')
   .notEmpty().withMessage('name is required'),

   body('price')
      .notEmpty().withMessage('price is required')
      .isDecimal().withMessage('price must be a number')
      .custom(value => {
         if (value < 0) {
            throw new Error('price must be greater than 0')
         }
         return true
      }),

   body('category')
      .notEmpty().withMessage('category is required')
      .isString().withMessage('category must be a string')
      .isIn(['Alimento', 'Bebida']).withMessage('category must be either Alimento or Bebida'),
   
   body('image')
      .notEmpty().withMessage('Image is required')
      .isURL().withMessage('image must be a URL'),

   //Comentarios

   //id_bar?
];

validators.idInParamsValidator = [
   param('identifier')
      .notEmpty().withMessage('id is required')
      .isMongoId().withMessage('id is not valid')
];


module.exports = validators