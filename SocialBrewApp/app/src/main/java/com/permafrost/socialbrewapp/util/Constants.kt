package com.permafrost.socialbrewapp.util

object Constants {
    //Api Services
    const val BASE_URL="http://localhost:3000"//Localhost puede variar con nuestra ip asignada en esa ruta
    const val API_PATH="/api"
    const val BAR_PATH="/bar"

    //Login
    const val LOGIN="/auth"
    const val REGISTER="/register"
    const val USER_LOGIN="/login"

    //Products
    const val PRODUCTS_PATH="/products"
    const val PRODUCT_ID="_id"
    const val PRODUCT_NAME="name"
    const val PRODUCT_PRICE="price"
    const val PRODUCT_CATEGORY="category"
    const val PRODUCT_IMAGE="image"
    const val PRODUCT_USER="user"
    const val PRODUCT_COMMENTS="comments"
    const val PRODUCT_POINTS="points"

    //User
    const val USER_ID ="_id"
    const val USER_NAME ="username"
    const val USER_EMAIL="email"
    const val USER_HPSW="hashedPassword"
    const val USER_SALT="salt"
    const val USER_TOKEN="tokens"
    const val USER_ROLE="roles"

    //Comments
    const val COMMENTS_PATH="/comment"
    const val COMMENT_ID="_id"
    const val COMMENT_TEXT="text"
    const val COMMENT_USER="user"
    const val COMMENT_POINTS="points"
}