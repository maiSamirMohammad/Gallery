package com.example.retrofit.data

import com.example.retrofit.data.model.Album

object Repository {
     fun getAlbums(): List<Album> {
        val album1 = Album(userId = 1, id = 1, title = "quidem molestiae enim")
        val album2 = Album(userId = 1, id = 1, title = "quidem molestiae enim")
        val album3 = Album(userId = 1, id = 1, title = "quidem molestiae enim")
        return listOf(album1, album2, album3)
    }
/*    suspend fun getData(dataType:String): Response<out List<Any>>{
        val response= when(dataType){
            Constants.TODO ->{
                MyRetrofit.apiService.getTodos()}
            Constants.ALBUM ->{
                MyRetrofit.apiService.getAlbums()}
            else -> {
                MyRetrofit.apiService.getPosts()}
        }
        return response
    }*/
}