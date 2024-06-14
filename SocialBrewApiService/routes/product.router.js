const express = require('express');
const router = express.Router();

const ROLES = require('../data/roles.constants.json');

const validateFields = require('../validators/index.middleware');
const { createProductValidator, idInParamsValidator, addCommentValidator } = require('../validators/products.validators');

const { authentication, authorization } = require('../middlewares/auth.middleware')

const productController = require('../controllers/Product.controller');


//Find all products
router.get('/', productController.findAll);

//Create a new product
router.post(['/', '/:identifier'],
   authentication,
   authorization(ROLES.BAR),
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
   authentication,
   authorization(ROLES.BAR),
   idInParamsValidator,
   validateFields,
   productController.deleteByID
);




//Add a comment to a product
router.post('/:identifier/comment',
   authentication,
   authorization(ROLES.USUARIO),
   idInParamsValidator,
   addCommentValidator,
   validateFields,
   productController.addComment

)

module.exports = router;