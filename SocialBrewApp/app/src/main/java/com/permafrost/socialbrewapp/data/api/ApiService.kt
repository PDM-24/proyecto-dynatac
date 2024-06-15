package com.permafrost.socialbrewapp.data.api

import com.permafrost.socialbrewapp.util.Constants
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //Bar
    //FindProductsFromABar
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.BAR_PATH + "/" + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun findProductsFromABar(@Path("identifier") identifier: String): List<ProductsApi>

    //FindAllBars
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.BAR_PATH + "/")
    suspend fun findAllBars(): List<UserApi>

    //CreateNewBar
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.BAR_PATH + Constants.REGISTER)
    suspend fun createNewBar(): UserApi

    /***************************************************************************************************/
    //Auth

    //Login
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.LOGIN + Constants.USER_LOGIN)
    suspend fun login(): UserApi

    //Register
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.LOGIN + Constants.REGISTER)
    suspend fun register(): UserApi


    /***************************************************************************************************/
    //Products

    //FindAllProducts
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/")
    suspend fun findAllProducts(): List<ProductsApi>


    //CreateNewProduct
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun createNewProduct(@Path("identifier") identifier: String): List<ProductsApi>

    //FindProductById
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun findProductById(@Path("identifier") identifier: String): ProductsApi

    //UpdateProduct
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun updateProduct(@Path("identifier") identifier: String): ProductsApi

    //Delete Product
    @Headers(value = ["Content-Type: application/json"])
    @DELETE(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun deleteProduct(@Path("identifier") identifier: String): ProductsApi

    //Add Comment to a Product
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}" + Constants.COMMENTS_PATH)
    suspend fun addCommentToProduct(@Path("identifier") identifier: String): ProductsApi

}