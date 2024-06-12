package com.permafrost.socialbrewapp.data.api

import com.permafrost.socialbrewapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    //Get all products
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/")
    suspend fun getAllProducts():List<ProductsApi>
    //Get products by id

    //Delete products by id


}