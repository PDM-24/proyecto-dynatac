const Product = require('../models/Product.model');

const controller = {};

controller.create = async (req, res, next) => {
   const { name, price, category, image } = req.body;
   try {

      const product = new Product({
         name,
         price,
         category,
         image
      });

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

controller.findAll = async (req, res, next) => {
   try {
      // debug('Finding all products')
      const products = await Product.find(/* Parametros de busqueda. Hidden: False */);
      if (!products) {
         return res.status(404).json({ error: "Products not found" });
      }

      res.status(200).json(products);
   } catch (error) {
      console.log(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

controller.findOneById = async (req, res, next) => {
   const { identifier } = req.params;
   try {
      const product = await Product.findById(identifier);
      if (!product) {
         return res.status(404).json({ error: "Product not found" });
      }
      return res.status(200).json(product);
   } catch (error) {
      console.log(error);
      return res.status(500).json({ error: "Internal Server Error" });
   }
}

module.exports = controller;