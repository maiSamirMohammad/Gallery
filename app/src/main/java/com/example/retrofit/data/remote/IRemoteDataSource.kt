package com.example.retrofit.data.remote

import com.example.retrofit.domain.entities.AlbumResponse
import com.example.retrofit.domain.entities.PhotoResponse
import com.example.retrofit.domain.entities.UsersResponse
import retrofit2.Response

interface IRemoteDataSource {
    suspend fun getUsers(): Response<UsersResponse>
    suspend fun getAlbums(userId: Int): Response<AlbumResponse>
    suspend fun getPhotos(albumId:Int): Response<PhotoResponse>
}