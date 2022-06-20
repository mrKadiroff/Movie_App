package com.example.movies_app.network

import com.example.movies_app.network.allmovies.AllResult
import com.example.movies_app.network.searchMovies.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("all.json")
    suspend fun getAllMovies(
        @Query("api-key") apikey:String = "beKOJAq1sjYHYp2raykgNMvjzHt4npjr"
     ): AllResult


    @GET("search.json")
    suspend fun getAllSearchResults(
        @Query("query") query:String,
        @Query("api-key") apikey:String = "beKOJAq1sjYHYp2raykgNMvjzHt4npjr"
    ): SearchResult


}