const Mongoose = require('mongoose');
const Schema = Mongoose.Schema;

const ComentarySchema = new Schema({
   text: {
      type: String,
      required: true
   },
   user : {
      type: Schema.Types.ObjectId,
      ref: 'User',
      required: true
   },
   points: {
      type: Number,
      required: true
   },
}, { timestamps: true });

module.exports = Mongoose.model('Comentary', ComentarySchema);