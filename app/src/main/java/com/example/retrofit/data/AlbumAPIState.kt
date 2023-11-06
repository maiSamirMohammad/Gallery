package com.example.retrofit.data

import com.example.retrofit.data.model.Album

sealed class AlbumAPIState{
    class Success(var data : List<Album>):AlbumAPIState()
    class Failure(val throwable: Throwable):AlbumAPIState()
    object Loading :AlbumAPIState()
}
