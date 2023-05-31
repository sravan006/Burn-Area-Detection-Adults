package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {
    @POST("upload")
    fun uploadImage(@Body request: ImageRequest): Call<ApiResponse>

    @GET("endpoint")
    fun getFloat(): Call<Float>

    @POST("form")
    fun postInt(@Body request: Intreq): Call<ApiResponse>

    @GET("fluid")
    fun getString() : Call<String>

}
data class Intreq(val int1:String)
data class ImageRequest(val image: String)
data class ApiResponse(val success: Boolean, val message: String)



