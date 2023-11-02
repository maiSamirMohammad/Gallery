package com.example.retrofit.presentation

import androidx.lifecycle.ViewModel
import com.example.retrofit.data.MyRetrofit
import com.example.retrofit.data.Repository
import com.example.retrofit.data.model.Album
import retrofit2.Response

class ProfileViewModel(private val repository:Repository):ViewModel(){
     fun getAlbums(): List<Album> {
        return repository.getAlbums()
    }
}