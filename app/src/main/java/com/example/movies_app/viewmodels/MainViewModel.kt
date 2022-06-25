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
    val omdInent = Channel<OmdIntent>(Channel.UNLIMITED)
    val newInent = Channel<NewIntent>(Channel.UNLIMITED)
    var wordim:String =""

    val state = MutableStateFlow<MainState>(MainState.Idle)
    val omdstate = MutableStateFlow<OmdState>(OmdState.Idle)
    val newstate = MutableStateFlow<NewState>(NewState.Idle)

    init {
        handleIntent()
    }

    init {
        omdHandle()
    }

    init {
        newHandle()
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







    private fun omdHandle() {
        viewModelScope.launch {
           omdInent.consumeAsFlow().collect {
               when (it) {
                   is OmdIntent.FetchOmd -> fetchOmd()
               }
           }
        }
    }

     fun fetchOmd() {
        viewModelScope.launch {
            omdstate.value = OmdState.Loading
            omdstate.value = try {
                OmdState.Search(repository.getOmdMovies(wordim))
            } catch (e:Exception) {
                OmdState.Error(e.localizedMessage)
            }
        }
    }


    private fun newHandle() {
        viewModelScope.launch {
            newInent.consumeAsFlow().collect {
                when (it) {
                    is NewIntent.FetchNew -> fetchNew()
                }
            }
        }
    }

    private fun fetchNew() {
        viewModelScope.launch {
            newstate.value = NewState.Loading
            newstate.value = try {
                NewState.Users(repository.getNewResult())
            } catch (e:Exception) {
                NewState.Error(e.localizedMessage)
            }
        }
    }

}