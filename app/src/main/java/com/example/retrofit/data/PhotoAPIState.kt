package com.example.retrofit.data

import com.example.retrofit.data.model.Photo


sealed class PhotoAPIState{
    class Success(var data : List<Photo>):PhotoAPIState()
    class Failure(val throwable: Throwable):PhotoAPIState()
    object Loading :PhotoAPIState()
}
