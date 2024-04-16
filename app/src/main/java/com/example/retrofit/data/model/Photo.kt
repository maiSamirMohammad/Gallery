package com.example.retrofit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
class PhotoResponse : ArrayList<Photo>()
@Entity
data class Photo(
    val albumId: Int,
    @PrimaryKey val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
): Serializable