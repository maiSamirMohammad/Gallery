package com.example.retrofit.domain

import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.Photo
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getAlbums(): Flow<List<Album>>
    suspend fun getPhotos(): Flow<List<Photo>>
}