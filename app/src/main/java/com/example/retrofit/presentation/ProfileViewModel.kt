package com.example.retrofit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.data.MyRetrofit
import com.example.retrofit.data.Repository
import com.example.retrofit.data.model.Album
import retrofit2.Response

class ProfileViewModel(private val repository:Repository):ViewModel(){
    private var _albums=MutableLiveData<List<Album>>()
    val albums= _albums
     fun getAlbums():LiveData<List<Album>> {
          _albums.value=repository.getAlbums()
         return albums
    }
}