package com.baha.kmfapp.di

import android.content.Context
import com.baha.kmfapp.Constants
import com.baha.kmfapp.api_clients.ILoginService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { getOkHttpClient(get()) }
    single { getRetrofit(get()) }


    single {
        val retrofit: Retrofit = get()
        retrofit.create(ILoginService::class.java) as ILoginService

    }

}

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .build()

}

fun getOkHttpClient(context: Context): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor {
            val requestBuilder = it.request().newBuilder()
                .addHeader("platform", "ANDROID") // Todo need to make const val

//                .addHeader("X-Requested-With", "XMLHttpRequest")
//                .addHeader("Authorization", prefsAuth.getAccessToken() ?: "")
            it.proceed(requestBuilder.build())
        }
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .build()
}


