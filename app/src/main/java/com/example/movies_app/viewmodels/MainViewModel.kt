package com.example.movies_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.repository.*
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
    val searchInent = Channel<SearchIntent>(Channel.UNLIMITED)
    var wordim:String =""

    val state = MutableStateFlow<MainState>(MainState.Idle)
    val searchstate = MutableStateFlow<SearchState>(SearchState.Idle)

    init {
        handleIntent()
    }


    init {
        searchHandle()
    }




    private fun handleIntent() {
        viewModelScope.launch {
            movieInent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                }
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

    private fun searchHandle() {
        viewModelScope.launch {
            searchInent.consumeAsFlow().collect {
                when (it) {
                    is SearchIntent.FetchSearchUser -> fetchSearch(wordim)
                }
            }
        }
    }


    fun fetchSearch(word:String) {
        viewModelScope.launch {
            searchstate.value = SearchState.Loading
            searchstate.value = try {
                SearchState.Search(repository.getSearchedMovies(wordim))
            } catch (e:Exception) {
                SearchState.Error(e.localizedMessage)
            }
        }
    }

}