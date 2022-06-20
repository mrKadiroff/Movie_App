package com.example.movies_app.network.searchMovies

data class SearchResult(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)