package com.testing.myapplication.apiServices

import com.testing.myapplication.apiResponseModal.GitResponseModal
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://api.github.com/"

private val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(300, TimeUnit.SECONDS)
    .readTimeout(300, TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()

private val retrofit = Retrofit.Builder().client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface getTrendingResporitories {
    @GET("/search/repositories?q=language=+sort:stars")
    fun getGitdata(): Call<GitResponseModal>
}

object getTrendingResporitoriesApi {
    val retrofitService: getTrendingResporitories by lazy {
        retrofit.create(getTrendingResporitories::class.java)
    }
}