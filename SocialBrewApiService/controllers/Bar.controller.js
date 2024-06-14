const User = require('../models/User.model');
const ROLES = require('../data/roles.constants.json');
const debug = require('debug')('app:Bar.controller');

const controller = {};

//Find All Bars
controller.findBar = async (req, res, next) => {
   try {
      const bar = await User.find({ roles: ROLES.BAR });

      if (!bar) {
         return res.status(404).json({ error: "Bar not found" });
      }

      return res.status(200).json(bar);

   } catch (error) {
      console.error(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

//Create a new Bar
controller.newBar = async (req, res, next) => {
   try {
      const { username, email, password } = req.body;

      const existingUser = await User.findOne({ email });

      if (existingUser) {
         return res.status(409).json({ message: 'Email already exists' });
      }

      const newUser = new User({ 
         username: username, 
         email: email, 
         password: password, 
         roles: [ROLES.BAR]
      });
      await newUser.save();

      res.status(201).json({ message: 'User registered successfully' });

   } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'Internal server error' });
   }
}


//Products from Bar
controller.findProducts = async (req, res, next) => {
   try {
      const { identifier } = req.params;

      const products = await Product.find({ user: identifier });

      if (!products) {
         return res.status(404).json({ error: "Products not found" });
      }

      return res.status(200).json(products);
   
   } catch (error) {
      console.error(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

module.exports = controller;
