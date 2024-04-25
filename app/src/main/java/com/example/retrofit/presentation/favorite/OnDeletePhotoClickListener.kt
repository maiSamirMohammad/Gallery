package com.example.retrofit.presentation.favorite

import com.example.retrofit.domain.entities.Photo

interface OnDeletePhotoClickListener {
    fun onDeleteClick(photo: Photo)
}