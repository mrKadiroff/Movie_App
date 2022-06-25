package com.example.movies_app.network.newresult

import java.io.Serializable

data class NewResult(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
):Serializable