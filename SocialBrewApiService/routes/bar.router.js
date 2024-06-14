const express = require('express');
const router = express.Router();

const barController = require('../controllers/Bar.controller');


//Find Bar
router.get('/', barController.findBar);

//Create a new Bar
router.post('/register', barController.newBar);

//Products from Bar
router.get('/:identifier/products', 
   barController.findProducts
);

module.exports = router;