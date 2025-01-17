package com.permafrost.socialbrewapp.data.api

import com.google.gson.annotations.SerializedName
import com.permafrost.socialbrewapp.util.Constants

data class ProductsApiWithStringUser(
    @SerializedName(value = Constants.PRODUCT_ID)
    val id: String = "",
    @SerializedName(value = Constants.PRODUCT_NAME)
    val name: String = "",
    @SerializedName(value = Constants.PRODUCT_PRICE)
    val price: Double = 0.0,
    @SerializedName(value = Constants.PRODUCT_CATEGORY)
    val category: String = "",
    @SerializedName(value = Constants.PRODUCT_IMAGE)
    val image: String = "",
    @SerializedName(value = Constants.PRODUCT_USER)
    val user: String = "",
    @SerializedName(value = Constants.PRODUCT_COMMENTS)
    val comments: MutableList<CommentarySchemaWithStringUser> = arrayListOf(),
    @SerializedName(value = Constants.PRODUCT_POINTS)
    val points: Float = 0.0f
)

data class ProductsApiWithObjectUser(
    @SerializedName(value = Constants.PRODUCT_ID)
    val id: String = "",
    @SerializedName(value = Constants.PRODUCT_NAME)
    val name: String = "",
    @SerializedName(value = Constants.PRODUCT_PRICE)
    val price: Double = 0.0,
    @SerializedName(value = Constants.PRODUCT_CATEGORY)
    val category: String = "",
    @SerializedName(value = Constants.PRODUCT_IMAGE)
    val image: String = "",
    @SerializedName(value = Constants.PRODUCT_USER)
    val user: User = User(),
    @SerializedName(value = Constants.PRODUCT_COMMENTS)
    val comments: MutableList<CommentarySchemaWithObjectUser> = arrayListOf(),
    @SerializedName(value = Constants.PRODUCT_POINTS)
    val points: Float = 0.0f
)




data class User(
    @SerializedName(value = Constants.USER_ID)
    val id: String = "",
    @SerializedName(value = Constants.USER_NAME)
    val username: String = "",
    @SerializedName(value = Constants.USER_EMAIL)
    val email: String = ""
)

data class CommentarySchemaWithStringUser(
    @SerializedName(value = Constants.COMMENT_ID)
    val id: String = "",
    @SerializedName(value = Constants.COMMENT_TEXT)
    val text: String = "",
    @SerializedName(value = Constants.COMMENT_USER)
    val user: String = "",
    @SerializedName(value = Constants.COMMENT_POINTS)
    val points: Float = 0.0f
)

data class CommentarySchemaWithObjectUser(
    @SerializedName(value = Constants.COMMENT_ID)
    val id: String = "",
    @SerializedName(value = Constants.COMMENT_TEXT)
    val text: String = "",
    @SerializedName(value = Constants.COMMENT_USER)
    val user: User = User(),
    @SerializedName(value = Constants.COMMENT_POINTS)
    val points: Float = 0.0f
)