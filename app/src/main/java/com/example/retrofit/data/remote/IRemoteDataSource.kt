package com.example.retrofit.data.remote

import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.AlbumResponse
import com.example.retrofit.data.model.Photo
import com.example.retrofit.data.model.PhotoResponse
import com.example.retrofit.data.model.User
import com.example.retrofit.data.model.UsersResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRemoteDataSource {
    suspend fun getUsers(): Response<UsersResponse>
    suspend fun getAlbums(userId: Int): Response<AlbumResponse>
    suspend fun getPhotos(albumId:Int): Response<PhotoResponse>
}