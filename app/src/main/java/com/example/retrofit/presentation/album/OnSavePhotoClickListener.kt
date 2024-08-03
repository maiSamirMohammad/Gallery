package com.example.retrofit.presentation.album

import com.example.retrofit.domain.entities.Photo

interface OnSavePhotoClickListener {
    fun onSaveClick(photo: Photo)
}