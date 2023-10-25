package com.example.retrofit

import com.example.retrofit.model.Album
import com.example.retrofit.model.Post
import com.example.retrofit.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>
    @GET("/albums")
    suspend fun getAlbums(): Response<List<Album>>
    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
}