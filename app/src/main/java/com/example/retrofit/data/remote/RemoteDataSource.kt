package com.example.retrofit.data.remote

import com.example.retrofit.domain.entities.AlbumResponse
import com.example.retrofit.domain.entities.PhotoResponse
import com.example.retrofit.domain.entities.UsersResponse
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