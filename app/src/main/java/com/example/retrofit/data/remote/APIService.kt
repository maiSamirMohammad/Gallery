package com.example.retrofit.data.remote

import com.example.retrofit.data.utils.Constants.ALBUM_END_POINT
import com.example.retrofit.data.utils.Constants.PHOTO_END_POINT
import com.example.retrofit.data.utils.Constants.USER_END_POINT
import com.example.retrofit.domain.entities.AlbumResponse
import com.example.retrofit.domain.entities.PhotoResponse
import com.example.retrofit.domain.entities.UsersResponse
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