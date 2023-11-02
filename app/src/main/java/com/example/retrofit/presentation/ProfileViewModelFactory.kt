package com.example.retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.data.Repository

class ProfileViewModelFactory(private val repository:Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            ProfileViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}