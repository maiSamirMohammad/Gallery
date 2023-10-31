package com.example.retrofit.data

import com.example.retrofit.data.Constants
import com.example.retrofit.data.MyRetrofit
import retrofit2.Response

object Repository {
    suspend fun getData(dataType:String): Response<out List<Any>>{
        val response= when(dataType){
            Constants.TODO ->{
                MyRetrofit.apiService.getTodos()}
            Constants.ALBUM ->{
                MyRetrofit.apiService.getAlbums()}
            else -> {
                MyRetrofit.apiService.getPosts()}
        }
        return response
    }
}