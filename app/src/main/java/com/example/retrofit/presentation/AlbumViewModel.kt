package com.example.retrofit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.data.Repository
import com.example.retrofit.data.model.Photo

class AlbumViewModel (private val repository: Repository): ViewModel(){
    private var _photos= MutableLiveData<List<Photo>>()
    val photos= _photos
    fun getPhotos(): LiveData<List<Photo>> {
        _photos.value=repository.getPhotos()
        return photos
    }
}