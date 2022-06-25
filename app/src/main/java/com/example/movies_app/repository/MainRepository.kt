package com.example.movies_app.repository

import com.example.movies_app.network.ApiSearchService
import com.example.movies_app.network.ApiService

class MainRepository(private val apiService: ApiService,private val apiSearchService: ApiSearchService) {
    suspend fun getUsers() = apiService.getAllMovies()



    suspend fun getNewResult() = apiService.getNewMovies()


    suspend fun getOmdMovies(word:String) = apiSearchService.getAllSearch(word)


}