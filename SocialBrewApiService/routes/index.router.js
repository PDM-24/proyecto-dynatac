const express = require('express');
const router = express.Router();

// const productRouter = require('./product.router'); // No se es q se usa
const authRouter = require('./auth.router');




//    /api/...
router.use('/auth', authRouter);
router.use('/products', require('./product.router'));


module.exports = router; 