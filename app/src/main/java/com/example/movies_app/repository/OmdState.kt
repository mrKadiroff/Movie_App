package com.example.movies_app.repository

import com.example.movies_app.network.allmovies.AllResult
import com.example.movies_app.network.omd.OmdResult


sealed class OmdState {
    object Idle : OmdState()
    object Loading : OmdState()
    data class Search(val search:OmdResult) : OmdState()
    data class Error(val error: String?) : OmdState()
}