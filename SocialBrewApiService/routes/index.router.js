const express = require('express');
const router = express.Router();

//    /api/products/...
router.use('/products', require('./product.router'));

module.exports = router;