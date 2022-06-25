package com.example.movies_app.network

import com.example.movies_app.network.allmovies.AllResult
import com.example.movies_app.network.newresult.NewResult
import com.example.movies_app.network.omd.OmdResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("all.json")
    suspend fun getAllMovies(
        @Query("api-key") apikey:String = "beKOJAq1sjYHYp2raykgNMvjzHt4npjr"
     ): AllResult



    @GET("search.json")
    suspend fun getNewMovies(
        @Query("api-key") apikey:String = "beKOJAq1sjYHYp2raykgNMvjzHt4npjr",
        @Query("opening-date") opening_date:String = "2021-01-01:2022-01-01"
    ): NewResult








}


interface ApiSearchService {


    @GET("?type=movie")
    suspend fun getAllSearch(
        @Query("t") t:String,
        @Query("apikey") apikey:String = "43de418d"
    ): OmdResult


}