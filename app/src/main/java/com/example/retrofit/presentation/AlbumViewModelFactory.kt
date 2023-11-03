package com.example.retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.data.Repository

class AlbumViewModelFactory (private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AlbumViewModel::class.java)){
            AlbumViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}