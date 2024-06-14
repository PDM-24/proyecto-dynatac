const Mongoose = require('mongoose');
const Schema = Mongoose.Schema;
const Comentary = require('./Comentary.model');

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
   },
   image: {
      type: String,
      required: true
   },
   user : {
      type: Schema.Types.ObjectId,
      ref: 'User',
      required: true
   },
   comments : {
      type: [Comentary.schema],
      default: []
   },
   points : {
      type: Number,
      default: 0
   },
   }, { timestamps: true });


module.exports = Mongoose.model('Product', ProductSchema);