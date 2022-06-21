package com.example.movies_app.repository

import com.example.movies_app.network.allmovies.AllResult
import com.example.movies_app.network.searchMovies.SearchResult

sealed class SearchState {
    object Idle : SearchState()
    object Loading : SearchState()
    data class Search(val search:SearchResult) : SearchState()
    data class Error(val error: String?) : SearchState()
}