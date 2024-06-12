const Mongoose = require('mongoose');
const debug = require('debug')('app:database');

const dbhost = process.env.DBHOST || 'localhost';
const dbport = process.env.DBPORT || '27017';
const dbname = process.env.DBNAME || 'SocialBrew';

const dburi = process.env.DBURI || `mongodb://${dbhost}:${dbport}/${dbname}`;

/*
   Connect to database method
*/

const connect = async () => {
   try{
      await Mongoose.connect(dburi);
      debug("Connected to database");

   } catch (error){
      console.error(error);
      debug("Cannot access database. Exiting...")
      debug("Recuerda poner el DBURI en archivo .env")
      process.exit(1);
   }
}



/*
   Disconnect from database method
*/
const disconnect = async () => {
   try {
      await Mongoose.disconnect();
      debug("Disconnected from database");
   } catch (error) {
      process.exit(1);
   }
}

module.exports = {
   connect,
   disconnect
}