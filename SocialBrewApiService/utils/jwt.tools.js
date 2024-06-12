const { SignJWT, jwtVerify } = require('jose')

const secret = new TextEncoder().encode(process.env.TOKEN_SECRET || 'mysecret')
const exp = process.env.TOKEN_EXP || '15d'

const tools = {}

tools.createToken = async (id) => {
   return await new SignJWT()
      .setProtectedHeader({ alg: 'HS256' })
      .setSubject(id)
      .setExpirationTime(exp)
      .setIssuedAt()
      .sign(secret)
}

tools.verifyToken = async (token) => {
   try {
      return (await jwtVerify(token, secret)).payload

   } catch (error) {
      return false
   }
}

module.exports = tools