package com.example.retrofit.data.model

import java.io.Serializable

class AlbumResponse : ArrayList<Album>()
data class Album(
    val id: Int,
    val title: String,
    val userId: Int
): Serializable