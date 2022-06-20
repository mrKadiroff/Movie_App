package com.example.movies_app.repository

import com.example.movies_app.network.ApiService

class MainRepository(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getAllMovies()

    suspend fun getSearchedMovies(word:String) = apiService.getAllSearchResults(word)
}