package com.example.retrofit.domain.repository

import com.example.retrofit.domain.entities.AlbumResponse
import com.example.retrofit.domain.entities.Photo
import com.example.retrofit.domain.entities.PhotoResponse
import com.example.retrofit.domain.entities.UsersResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRepository {
    suspend fun getUsers(): Response<UsersResponse>
    suspend fun getAlbums(userId: Int): Response<AlbumResponse>
    suspend fun getPhotos(albumId:Int): Response<PhotoResponse>
    fun getLocalPhotos(): Flow<List<Photo>>
    suspend fun insertPhoto(photo: Photo): Long
    suspend fun deletePhoto(photo: Photo)
}