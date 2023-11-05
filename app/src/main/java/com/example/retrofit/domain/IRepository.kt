package com.example.retrofit.domain

import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.Photo

interface IRepository {
    fun getAlbums(): List<Album>
    fun getPhotos(): List<Photo>
}