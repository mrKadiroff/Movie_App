package com.example.movies_app.network.allmovies

import java.io.Serializable

data class Link(
    val suggested_link_text: String,
    val type: String,
    val url: String
):Serializable