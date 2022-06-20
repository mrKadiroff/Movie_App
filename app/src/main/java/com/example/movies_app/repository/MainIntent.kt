package com.example.movies_app.repository

sealed class MainIntent {
    object FetchUser : MainIntent()

    object FetchSearchUser : MainIntent()
}