package com.baha.kmfapp.api_clients

import com.baha.kmfapp.UserResponse
import com.google.gson.JsonObject
import retrofit2.http.*

interface ILoginService {

    @GET("v2/user/{username}")
    suspend fun getUser(@Path(value = "username", encoded = true) username: String?): UserResponse

    @FormUrlEncoded
    @POST("v2/user")
    fun registerUser(@Body login: UserResponse?): JsonObject
}