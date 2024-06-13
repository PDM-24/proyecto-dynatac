package com.permafrost.socialbrewapp.data.api

import com.permafrost.socialbrewapp.util.Constants
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //Get all products
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/")
    suspend fun getAllProducts(): List<ProductsApi>

    //Get products by id
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun getProductsById(@Path("identifier") identifier: String): ProductsApi


    //Delete products by id
    @Headers(value = ["Content-Type: application/json"])
    @DELETE(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun deleteProductsById(@Path("identifier") identifier: String): ProductsApi

    //Login
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.LOGIN + Constants.USER_LOGIN)
    suspend fun login(): UserApi

    //Register
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.LOGIN + Constants.USER_REGISTER)
    suspend fun register(): UserApi


}