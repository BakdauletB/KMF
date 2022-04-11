package com.baha.kmfapp.api_clients

import com.baha.kmfapp.UserResponse
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


const val BASE_URL_OWN = "https://petstore.swagger.io/"

interface OwnApiService {
    @GET("v2/user/{username}")
    suspend fun getUser(@Path(value = "username", encoded = true) username: String?): UserResponse

    @POST("v2/user")
    suspend fun registerUser(@Body login: UserResponse?): JsonObject
}
