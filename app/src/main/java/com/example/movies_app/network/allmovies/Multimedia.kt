package com.example.movies_app.network.allmovies

import java.io.Serializable

data class Multimedia(
    val height: Int,
    val src: String,
    val type: String,
    val width: Int
):Serializable