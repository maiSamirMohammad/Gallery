package com.example.retrofit.domain

import com.example.retrofit.data.model.AlbumResponse
import com.example.retrofit.data.model.Photo
import com.example.retrofit.data.model.PhotoResponse
import com.example.retrofit.data.model.UsersResponse
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