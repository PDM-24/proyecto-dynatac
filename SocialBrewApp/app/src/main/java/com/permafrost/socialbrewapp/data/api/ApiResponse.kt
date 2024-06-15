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


