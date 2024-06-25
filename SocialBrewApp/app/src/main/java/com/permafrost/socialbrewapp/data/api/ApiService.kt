package com.permafrost.socialbrewapp.data.api

import com.permafrost.socialbrewapp.util.Constants
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import com.permafrost.socialbrewapp.data.api.RegisterRequest
import retrofit2.Response

interface ApiService {

    //Bar

    //FindProductsFromABar
    //Identifier es el id del bar que es el usuario que tiene el rol de bar
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.BAR_PATH + "/{identifier}" + Constants.PRODUCTS_PATH)
    suspend fun findProductsFromABar(@Path("identifier") identifier: String): List<ProductsApiWithStringUser>


    //FindAllBars
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.BAR_PATH + "/")
    suspend fun findAllBars(): List<UserApi>




    //CreateNewBar
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.BAR_PATH + Constants.REGISTER)
    suspend fun createNewBar(@Body newBarRequest: NewBarRequest): Response<NewBarResponse>

    /***************************************************************************************************/
    //Auth

    //Login
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.LOGIN + Constants.USER_LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    //Register
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.LOGIN + Constants.REGISTER)
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>


    /***************************************************************************************************/
    //Products

    //FindAllProducts
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/")
    suspend fun findAllProducts(): List<ProductsApiWithStringUser>


    //CreateNewProduct
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun createNewProduct(
        @Path("identifier") identifier: String,
        @Body newProductRequest: NewProductRequest
    ): ProductsApiWithStringUser

    //FindProductById
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun findProductById(@Path("identifier") identifier: String): ProductsApiWithObjectUser

    //UpdateProduct
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun updateProduct(@Path("identifier") identifier: String): ProductsApiWithStringUser


    //Delete Product
    @Headers(value = ["Content-Type: application/json"])
    @DELETE(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}")
    suspend fun deleteProduct(@Path("identifier") identifier: String): Response<String>

    //Add Comment to a Product
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.PRODUCTS_PATH + "/{identifier}" + Constants.COMMENTS_PATH)
    suspend fun addCommentToProduct(
        @Path("identifier") identifier: String,
        @Body newCommentRequest: NewCommentRequest
    ): ProductsApiWithStringUser

}