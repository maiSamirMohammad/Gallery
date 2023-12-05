package com.example.retrofit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    val albumId: Int,
    @PrimaryKey val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)