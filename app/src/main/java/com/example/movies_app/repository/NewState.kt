package com.example.movies_app.repository

import com.example.movies_app.network.allmovies.AllResult
import com.example.movies_app.network.newresult.NewResult


sealed class NewState {
    object Idle : NewState()
    object Loading : NewState()
    data class Users(val user: NewResult) : NewState()
    data class Error(val error: String?) : NewState()
}