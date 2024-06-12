package com.permafrost.socialbrewapp.data.api

import com.google.gson.annotations.SerializedName
import com.permafrost.socialbrewapp.util.Constants
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
    //@SerializedName(value = Constants.PRODUCT_COMMENTS)
    //val comments:List<String> = listOf(),
    //@SerializedName(value = Constants.ID_BAR)
    //val id_bar:Int =""




)
