const express = require('express');
const router = express.Router();

const validateFields = require('../validators/index.middleware');
const { createProductValidator, idInParamsValidator } = require('../validators/products.validators');

const productController = require('../controllers/Product.controller');


router.get('/', productController.findAll);

router.post('/', createProductValidator, validateFields, productController.create);

router.get('/:identifier', idInParamsValidator, validateFields, productController.findOneById);

module.exports = router;