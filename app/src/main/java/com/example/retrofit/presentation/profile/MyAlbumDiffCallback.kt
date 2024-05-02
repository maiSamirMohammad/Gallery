package com.example.retrofit.presentation.profile

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofit.domain.entities.Album
import com.example.retrofit.domain.entities.AlbumResponse

class MyAlbumDiffCallback(
    private val  oldList: AlbumResponse?,
    private val  newList: AlbumResponse?
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList!!.size
    }

    override fun getNewListSize(): Int {
        return newList!!.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList!![oldItemPosition].id== newList!![newItemPosition]. id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val  oldAlbum = oldList!![oldItemPosition]
        val  newAlbum = newList!![newItemPosition]
        return when{
            oldAlbum.id != newAlbum.id -> false
            oldAlbum.userId != newAlbum.userId -> false
            oldAlbum.title != newAlbum.title -> false
            else -> true

        }
    }
}