const Mongoose = require('mongoose');
const Schema = Mongoose.Schema;

const crypto = require('crypto');
const { type } = require('os');
const debug = require('debug')('app:models:user');

const UserSchema = new Schema({
   username: {
      type: String,
      required: true,
      trim: true
   },
   email: {
      type: String,
      required: true,
      unique: true,
      trim: true,
      lowercase: true
   },
   hashedPassword: {
      type: String,
      required: true
   },
   salt: {
      type: String,
      required: true
   },
   tokens: {
      type: [String],
      default: []
   },
   roles: {
      type: [String],
      default: [],
   }
}, { timestamps: true });


UserSchema.methods = {
   encryptPassword: function (password) {
      if (!password) return '';

      try {
         const _password = crypto.pbkdf2Sync(
            password,
            this.salt,
            1000,
            64,
            'sha512'
         ).toString('hex');

         return _password;

      } catch (error) {
         debug({ error });
         return '';
      }

   },
   makeSalt: function () {
      return crypto.randomBytes(16).toString('hex');
   },
   comparePassword: function (password) {
      return this.encryptPassword(password) === this.hashedPassword;
   }
}

UserSchema
   .virtual('password')
   .set(
      function (password = crypto.randomBytes(16).toString('hex')) {
         this.salt = this.makeSalt();
         this.hashedPassword = this.encryptPassword(password);
      }
   );

module.exports = Mongoose.model('User', UserSchema);