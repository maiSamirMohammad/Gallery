package com.example.retrofit.domain.entities

class AlbumResponse : ArrayList<Album>()
data class Album(
    val id: Int,
    val title: String,
    val userId: Int
)