package com.example.retrofit.data.local

import com.example.retrofit.domain.entities.Photo
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    fun getLocalPhotos(): Flow<List<Photo>>
    suspend fun insertPhoto(photo: Photo): Long
    suspend fun deletePhoto(photo: Photo)
}