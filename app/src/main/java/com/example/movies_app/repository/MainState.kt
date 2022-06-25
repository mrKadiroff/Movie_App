package com.example.movies_app.repository

import com.example.movies_app.network.allmovies.AllResult


sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: AllResult) : MainState()
    data class Error(val error: String?) : MainState()
}