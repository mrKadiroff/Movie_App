package com.example.movies_app.repository

sealed class MainIntent {
    object FetchUser : MainIntent()

}


sealed class SearchIntent {

    object FetchSearchUser : SearchIntent()
}