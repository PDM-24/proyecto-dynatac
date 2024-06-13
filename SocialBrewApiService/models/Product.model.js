const Mongoose = require('mongoose');
const Schema = Mongoose.Schema;

const ProductSchema = new Schema({
   name: {
      type: String,
      required: true
   },
   price: {
      type: Number,
      required: true
   },
   category: {
      type: String,
      required: true,
      // enum: ['Alimento', 'Bebida'],
   },
   image: {
      type: String,
      required: true
   },
   user : {
      type: Schema.Types.ObjectId,
      ref: 'User',
      required: true
   }

   //Comentarios


   }, { timestamps: true });


module.exports = Mongoose.model('Product', ProductSchema);