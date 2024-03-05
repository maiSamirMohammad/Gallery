package com.example.retrofit.data

import com.example.retrofit.data.model.Photo

sealed class PhotoLocalState{
    class Success(var data : List<Photo>):PhotoLocalState()
    class Failure(val throwable: Throwable):PhotoLocalState()
    object Loading :PhotoLocalState()
}
