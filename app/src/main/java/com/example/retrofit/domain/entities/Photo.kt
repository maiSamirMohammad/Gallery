package com.example.retrofit.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

class PhotoResponse : ArrayList<Photo>()
@Entity
data class Photo(
    val albumId: Int,
    @PrimaryKey val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)