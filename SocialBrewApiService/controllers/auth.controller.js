const User = require('../models/User.model');
const ROLES = require('../data/roles.constants.json');
const { createToken, verifyToken} = require('../utils/jwt.tools');
const debug = require('debug')('app:auth.controller');

const controller = {};

//Register a new user
controller.register = async (req, res, next) => {
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
         roles: [ROLES.USUARIO]
      });
      await newUser.save();

      res.status(201).json({ message: 'User registered successfully' });

   } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'Internal server error' });
   }
}

//Login a user
controller.login = async (req, res, next) => {
   try {
      const { email, password } = req.body;
   
      const user = await User.findOne({ email });
   
      if (!user) {
         return res.status(404).json({ message: 'User not found' });
      }
   
      // Asegurarse de que comparePassword es tratado como una operación asíncrona
      const isPasswordMatch = await user.comparePassword(password);
      if (!isPasswordMatch) {
         return res.status(401).json({ message: 'Incorrect password' });
      }
      const token = await createToken(user._id);
   
      let _tokens = [...user.tokens];
   
      const _verifyPromises = _tokens.map(async token => {
         const status = await verifyToken(token);
         return status ? token : null;
      });
   
      _tokens = (await Promise.all(_verifyPromises))
         .filter(token => token)
         .slice(0, 4);
   
      _tokens = [token, ..._tokens];
      user.tokens = _tokens;
   
      await user.save();
   
      return res.status(200).json({ token });
   } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'Internal server error' });
   }
}


module.exports = controller;