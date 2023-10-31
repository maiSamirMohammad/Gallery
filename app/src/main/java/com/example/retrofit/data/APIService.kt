package com.example.retrofit.data

import com.example.retrofit.data.Constants.ALBUM_END_POINT
import com.example.retrofit.data.Constants.POST_END_POINT
import com.example.retrofit.data.Constants.TODO_END_POINT
import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.Post
import com.example.retrofit.data.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET(TODO_END_POINT)
    suspend fun getTodos(): Response<List<Todo>>
    @GET(ALBUM_END_POINT)
    suspend fun getAlbums(): Response<List<Album>>
    @GET(POST_END_POINT)
    suspend fun getPosts(): Response<List<Post>>
}