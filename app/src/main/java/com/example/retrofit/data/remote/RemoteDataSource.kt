package com.example.retrofit.data.remote

import com.example.retrofit.data.local.PhotoDao
import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.AlbumResponse
import com.example.retrofit.data.model.Photo
import com.example.retrofit.data.model.PhotoResponse
import com.example.retrofit.data.model.User
import com.example.retrofit.data.model.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: APIService
)  : IRemoteDataSource{
    override suspend fun getUsers(): Response<UsersResponse> {
        return apiService.getUsers()
    }

    override suspend fun getAlbums(userId: Int): Response<AlbumResponse> {
        return  apiService.getAlbums(userId)
    }
    override suspend fun getPhotos(albumId:Int): Response<PhotoResponse> {
        return apiService.getPhotos(albumId)
    }
}