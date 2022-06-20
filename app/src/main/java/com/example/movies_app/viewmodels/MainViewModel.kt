package com.example.movies_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.repository.MainIntent
import com.example.movies_app.repository.MainRepository
import com.example.movies_app.repository.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val movieInent = Channel<MainIntent>(Channel.UNLIMITED)
    val searchInent = Channel<MainIntent>(Channel.UNLIMITED)
    var wordim:String =""

    val state = MutableStateFlow<MainState>(MainState.Idle)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            movieInent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                    is MainIntent.FetchSearchUser -> fetchSearchUser(wordim)
                }
            }
        }
    }

   fun fetchSearchUser(word:String) {
        viewModelScope.launch {
            state.value = MainState.Loading
            state.value = try {
                wordim = word
                MainState.Search(repository.getSearchedMovies(word))
            } catch (e:Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            state.value = MainState.Loading
            state.value = try {
                MainState.Users(repository.getUsers())
            } catch (e:Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }

}