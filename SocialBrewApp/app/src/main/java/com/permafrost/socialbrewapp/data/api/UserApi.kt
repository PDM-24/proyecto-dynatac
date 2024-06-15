package com.permafrost.socialbrewapp.data.api

import com.google.gson.annotations.SerializedName
import com.permafrost.socialbrewapp.util.Constants

data class UserApi(
    @SerializedName(value = Constants.USER_NAME)
    val username: String = "",
    @SerializedName(value = Constants.USER_EMAIL)
    val email: String = "",
    @SerializedName(value = Constants.USER_TOKEN)
    val tokens: List<String> = listOf(),
    @SerializedName(value = Constants.USER_ROLE)
    val roles :List<String> = listOf()
    )
