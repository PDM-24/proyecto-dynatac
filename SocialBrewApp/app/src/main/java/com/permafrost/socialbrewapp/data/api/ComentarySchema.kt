package com.permafrost.socialbrewapp.data.api

import com.google.gson.annotations.SerializedName
import com.permafrost.socialbrewapp.util.Constants

data class ComentarySchema(
    @SerializedName(value = Constants.COMMENT_ID)
    val id: String = "",
    @SerializedName(value =Constants.COMMENT_TEXT)
    val text: String = "",
    @SerializedName(value = Constants.COMMENT_USER)
    val user: String = "",
    @SerializedName(value = Constants.COMMENT_POINTS)
    val points: Float = 0.0f

)
