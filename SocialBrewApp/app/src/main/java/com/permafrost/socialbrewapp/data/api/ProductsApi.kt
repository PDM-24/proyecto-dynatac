package com.permafrost.socialbrewapp.data.api

import com.google.gson.annotations.SerializedName
import com.permafrost.socialbrewapp.util.Constants
import java.io.Serial

data class ProductsApi(
    @SerializedName(value = Constants.PRODUCT_ID)
    val id:String ="",
    @SerializedName(value = Constants.PRODUCT_NAME)
    val name:String ="",
    @SerializedName(value = Constants.PRODUCT_PRICE)
    val price:Double = 0.0,
    @SerializedName(value = Constants.PRODUCT_CATEGORY)
    val category:String ="",
    @SerializedName(value = Constants.PRODUCT_IMAGE)
    val image:String ="",
    @SerializedName(value = Constants.PRODUCT_USER)
    val user:String ="",
    @SerializedName(value = Constants.PRODUCT_COMMENTS)
    val comments:List<ComentarySchema> = listOf(),
    @SerializedName(value = Constants.PRODUCT_POINTS)
    val points:Float = 0.0f




)
