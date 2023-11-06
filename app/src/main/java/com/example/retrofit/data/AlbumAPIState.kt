package com.example.retrofit.data

import com.example.retrofit.data.model.Album

sealed class AlbumAPIState{
    class Success(var data : Album):AlbumAPIState()
    class Failure(val message: Throwable):AlbumAPIState()
    object Loading :AlbumAPIState()
}
