package com.example.movies_app.repository

sealed class MainIntent {
    object FetchUser : MainIntent()

}

sealed class NewIntent {
    object FetchNew : NewIntent()

}


sealed class OmdIntent {

    object FetchOmd : OmdIntent()
}