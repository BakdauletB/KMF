package com.baha.kmfapp.api_clients

import com.baha.kmfapp.BASE_URL_OWN
import com.baha.kmfapp.OwnApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object KMFApi {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(BASE_URL_OWN)
        .build()

    val retrofitService: OwnApiService by lazy {
        retrofit.create(OwnApiService::class.java)
    }
}