package com.example.retrofit

import com.example.retrofit.Constants.ALBUM_END_POINT
import com.example.retrofit.Constants.POST_END_POINT
import com.example.retrofit.Constants.TODO_END_POINT
import com.example.retrofit.model.Album
import com.example.retrofit.model.Post
import com.example.retrofit.model.Todo
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