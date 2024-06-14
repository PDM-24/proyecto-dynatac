const express = require('express');
const router = express.Router();

const productRouter = require('./product.router');
const authRouter = require('./auth.router');
const barRouter = require('./bar.router');




//    /api/...
router.use('/auth', authRouter);
router.use('/products', productRouter);
router.use('/bar', barRouter);


module.exports = router; 