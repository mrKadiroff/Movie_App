package com.example.movies_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/"
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiService() :ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}