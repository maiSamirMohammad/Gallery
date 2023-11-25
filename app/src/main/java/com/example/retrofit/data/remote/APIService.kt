package com.example.retrofit.data.remote

import com.example.retrofit.data.Constants.ALBUM_END_POINT
import com.example.retrofit.data.Constants.PHOTO_END_POINT
import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.Photo
import retrofit2.http.GET

interface APIService {
    @GET(ALBUM_END_POINT)
    suspend fun getAlbums(): List<Album>
    @GET(PHOTO_END_POINT)
    suspend fun getPhotos(): List<Photo>

}