package com.permafrost.socialbrewapp.data.api

import com.google.gson.annotations.SerializedName
import com.permafrost.socialbrewapp.util.Constants

data class ApiResponseSuccessful(
    @SerializedName(value = Constants.RESPONSE_SUCCESSFUL)
    val result: String
)
data class ApiResponseError(
    @SerializedName(value = Constants.RESPONSE_ERROR)
    val message: String
)

data class LoginResponse(
    @SerializedName(value="token")
    val token: String,
    @SerializedName(value="Roles")
    val role: List<String>,
    @SerializedName(value="id")
    val id: String
)


data class RegisterResponse(
    @SerializedName("message")
    val message: String
)

data class NewBarResponse(
    @SerializedName("message")
    val message: String
)