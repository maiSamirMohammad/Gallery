package com.example.retrofit.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class AlbumResponse : ArrayList<Album>()
@Parcelize
data class Album(
    val id: Int,
    val title: String,
    val userId: Int
):Parcelable