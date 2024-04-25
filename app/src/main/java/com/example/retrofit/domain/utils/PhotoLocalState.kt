package com.example.retrofit.domain.utils

import com.example.retrofit.domain.entities.Photo

sealed class PhotoLocalState{
    class Success(var data : List<Photo>): PhotoLocalState()
    class Failure(val throwable: Throwable): PhotoLocalState()
    object Loading : PhotoLocalState()
}
