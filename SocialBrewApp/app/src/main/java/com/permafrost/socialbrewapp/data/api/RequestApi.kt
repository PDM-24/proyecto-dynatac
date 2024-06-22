package com.permafrost.socialbrewapp.data.api

import androidx.compose.ui.semantics.Role
import com.google.gson.annotations.SerializedName
import com.permafrost.socialbrewapp.util.Constants

data class RegisterRequest(
    @SerializedName(value= Constants.USER_NAME)
    val username: String,
    @SerializedName(value= Constants.USER_EMAIL)
    val email: String,
    @SerializedName(value= Constants.USER_HPSW)
    val password: String,
)

data class LoginRequest(
    @SerializedName(value= Constants.USER_EMAIL)
    val email: String,
    @SerializedName(value= Constants.USER_HPSW)
    val password: String
)

data class NewBarRequest(
    @SerializedName(value= Constants.USER_NAME)
    val username: String="",
    @SerializedName(value= Constants.USER_EMAIL)
    val email: String="",
    @SerializedName(value= Constants.USER_HPSW)
    val password: String="",
)

data class NewProductRequest(
    @SerializedName(value= Constants.PRODUCT_NAME)
    val name: String,
    @SerializedName(value= Constants.PRODUCT_PRICE)
    val price: Double,
    @SerializedName(value= Constants.PRODUCT_CATEGORY)
    val category: String,
    @SerializedName(value= Constants.PRODUCT_IMAGE)
    val image: String
)

data class newCommentRequest(
    @SerializedName(value= Constants.COMMENT_TEXT)
    val text: String,
    @SerializedName(value= Constants.COMMENT_POINTS)
    val points: Double
)



