const express = require('express');
const router = express.Router();

const validateFields = require('../validators/index.middleware');
const { createProductValidator, idInParamsValidator } = require('../validators/products.validators');

const productController = require('../controllers/Product.controller');


//Find all products
router.get('/', productController.findAll);

//Create a new product
router.post(['/', '/:identifier'], 
   createProductValidator,
   validateFields, 
   productController.save
);

//Find a product by id
router.get('/:identifier', 
   idInParamsValidator, 
   validateFields, 
   productController.findOneById
);

//Delete a product by id
router.delete('/:identifier',
   idInParamsValidator,
   validateFields,
   productController.deleteByID
);

module.exports = router;