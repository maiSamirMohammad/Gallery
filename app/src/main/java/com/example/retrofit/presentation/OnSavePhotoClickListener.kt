package com.example.retrofit.presentation

import com.example.retrofit.data.model.Photo

interface OnSavePhotoClickListener {
    fun onSaveClick(photo: Photo)
}