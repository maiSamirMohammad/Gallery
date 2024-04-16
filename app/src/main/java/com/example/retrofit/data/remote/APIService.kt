package com.example.retrofit.data.remote

import com.example.retrofit.data.Constants.ALBUM_END_POINT
import com.example.retrofit.data.Constants.PHOTO_END_POINT
import com.example.retrofit.data.Constants.USER_END_POINT
import com.example.retrofit.data.model.AlbumResponse
import com.example.retrofit.data.model.PhotoResponse
import com.example.retrofit.data.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(USER_END_POINT)
    suspend fun getUsers(): Response<UsersResponse>
    @GET(ALBUM_END_POINT)
    suspend fun getAlbums(
        @Query("userId") userId: Int
    ): Response<AlbumResponse>

    @GET(PHOTO_END_POINT)
    suspend fun getPhotos(
        @Query("albumId") albumId:Int
    ): Response<PhotoResponse>

}