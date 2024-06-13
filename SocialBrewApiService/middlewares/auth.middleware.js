const debug = require('debug')('app:auth.middleware')
const { verifyToken } = require('../utils/jwt.tools')
const User = require('../models/User.model')

const ROLES = require('../data/roles.constants.json')

const middleware = {}
const PREFIX = 'Bearer'


middleware.authentication = async (req, res, next) => {
   try {
      debug('Authentication middleware')

      const { authorization } = req.headers

      if (!authorization) {
         return res.status(401).json({ message: 'Unauthorized' })
      }

      const [prefix, token] = authorization.split(' ')

      if (prefix !== PREFIX) {
         return res.status(401).json({ message: 'Unauthorized' })
      }
      if (!token) {
         return res.status(401).json({ message: 'Unauthorized' })
      }

      const payload = await verifyToken(token)

      if (!payload) {
         return res.status(401).json({ message: 'Unauthorized' })
      }

      const userID = payload['sub']

      const user = await User.findById(userID)

      if (!user) {
         return res.status(401).json({ message: 'Unauthorized' })
      }

      const isTokenValid = user.tokens.includes(token)
      if (!isTokenValid) {
         return res.status(401).json({ message: 'Unauthorized' })
      }

      req.user = user
      req.token = token


      next()
   } catch (error) {
      console.log(error)
      return res.status(500).json({ message: 'Internal server error' })
   }
}

middleware.authorization = (roleRequired = ROLES.ADMIN) => {
   return (req, res, next) => {
      try {
         const { roles } = req.user
         debug("Roles", roles)

         if (!roles.includes(roleRequired)) {
            return res.status(403).json({ message: 'Forbidden' })
         }
         // if (roles.includes(ROLES.ADMIN)) {
         //    return res.status(403).json({ message: 'Forbidden' })
         // }

         next()
      } catch (error) {
         return res.status(500).json({ message: 'Internal server error' })
      }


      
   }
}


module.exports = middleware