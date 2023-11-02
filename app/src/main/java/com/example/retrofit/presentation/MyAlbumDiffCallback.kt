package com.example.retrofit.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofit.data.model.Album

class MyAlbumDiffCallback(
    private val  oldList: List<Album>,
    private val  newList: List<Album>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id== newList[newItemPosition]. id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val  oldAlbum = oldList[oldItemPosition]
        val  newAlbum = newList[newItemPosition]
        return when{
            oldAlbum.id != newAlbum.id -> false
            oldAlbum.userId != newAlbum.userId -> false
            oldAlbum.title != newAlbum.title -> false
            else -> true

        }
    }
}