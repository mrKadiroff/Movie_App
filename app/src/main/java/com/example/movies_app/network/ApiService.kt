package com.example.movies_app.network

import com.example.movies_app.network.allmovies.AllResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("all.json")
     fun getAllMovies(
        @Query("api-key") apikey:String
     ): Call<AllResult>
}