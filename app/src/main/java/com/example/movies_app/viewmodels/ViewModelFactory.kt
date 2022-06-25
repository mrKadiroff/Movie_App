package com.example.movies_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies_app.network.ApiSearchService
import com.example.movies_app.network.ApiService
import com.example.movies_app.repository.MainRepository

class ViewModelFactory(private val apiService: ApiService,private val apiSearchService: ApiSearchService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiService,apiSearchService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}