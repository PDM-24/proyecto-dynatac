const Product = require('../models/Product.model');
const Comentary = require('../models/Comentary.model');
const debug = require('debug')('app:Product.controller');

const controller = {};

// Create a new product
controller.save = async (req, res, next) => {
   try {

      const { name, price, category, image } = req.body;
      const { identifier } = req.params;
      const { user } = req;
      


      let product = await Product.findById(identifier);

      if (!product) {
         product = new Product();
         product['user'] = user._id;
      } else {
         if (product['user'].toString() !== user._id.toString()) {
            return res.status(403).json({ error: "Forbidden, Not your product" });
         }
      }

      product['name'] = name;
      product['price'] = price;
      product['category'] = category;
      product['image'] = image;
      


      const productSaved = await product.save();
      if (!productSaved) {
         return res.status(400).json({ error: "Error saving product" });
      }

      res.status(201).json(productSaved);

   } catch (error) {
      console.log(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

// Find all products
controller.findAll = async (req, res, next) => {
   try {
      // debug('Finding all products')
      var products = await Product.find(/* Parametros de busqueda. Hidden: False */)
         .populate('user', 'username email')
         // .populate('comments.user', 'username email -_id');
      if (!products) {
         return res.status(404).json({ error: "Products not found" });
      }

      for (let i = 0; i < products.length; i++) {
         products[i].comments = [];
      }
      
      debug(products)
      
      res.status(200).json(products);
   } catch (error) {
      console.log(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

// Find a product by id
controller.findOneById = async (req, res, next) => {
   const { identifier } = req.params;
   try {
      const product = await Product.findOne({ _id: identifier })
         .populate('user', 'username email')
         .populate('comments.user', 'username email -_id');
      if (!product) {
         return res.status(404).json({ error: "Product not found" });
      }
      return res.status(200).json(product);
   } catch (error) {
      console.log(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

// Delete a product by id
controller.deleteByID = async (req, res, next) => {
   try {
      const { identifier } = req.params;
      const { user } = req;

      const product = await Product.findOneAndDelete({ _id: identifier, user: user._id });

      if (!product) {
         return res.status(404).json({ error: "Product not found" });
      }

      return res.status(200).json({ message: "Product deleted" });

   } catch (error) {
      console.log(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

//Add a comment to a product
controller.addComment = async (req, res, next) => {
   try {
      const { identifier } = req.params;
      const { user } = req;
      const { text } = req.body;

      const product = await Product.findOne({ _id: identifier });
      if (!product) {
         return res.status(404).json({ error: "Product not found" });
      }
      
      const commentary = {
         text: text,
         user: user._id,
         // points: points
      }

      product.comments.push(commentary);

      const productSaved = await product.save();

      if (!productSaved) {
         return res.status(400).json({ error: "Error saving product" });
      }

      return res.status(201).json(productSaved);
      
   } catch (error) {
      console.log(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

module.exports = controller;