const Product = require('../models/Product.model');

const controller = {};

// Create a new product
controller.save = async (req, res, next) => {
   const { name, price, category, image } = req.body;
   const { identifier } = req.params;

   try {

      // const product = new Product({
      //    name,
      //    price,
      //    category,
      //    image
      // });

      let product = await Product.findById(identifier);

      if (!product) {
         product = new Product();
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

// Find a product by id
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

// Delete a product by id
controller.deleteByID = async (req, res, next) => {
   try {
      const { identifier } = req.params;

      const product = await Product.findByIdAndDelete(identifier);
      
      if (!product) {
         return res.status(404).json({ error: "Product not found" });
      }

      return res.status(200).json({ message: "Product deleted" });

   } catch (error) {
      return res.status(500).json({ error: "Internal Server Error" });
   }
}


module.exports = controller;