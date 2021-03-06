package com.example.movies_app.network.allmovies

import java.io.Serializable

data class AllResult(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
):Serializable