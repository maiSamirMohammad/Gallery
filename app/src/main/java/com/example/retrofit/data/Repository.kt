package com.example.retrofit.data

import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.Photo

object Repository {
     fun getAlbums(): List<Album> {
        val album1 = Album(userId = 1, id = 1, title = "quidem molestiae enim")
        val album2 = Album(userId = 1, id = 1, title = "quidem molestiae enim")
        val album3 = Album(userId = 1, id = 1, title = "quidem molestiae enim")
        return listOf(album1, album2, album3)
    }
    fun getPhotos(): List<Photo> {
        val photo1 = Photo(
            albumId= 1,
            id= 1,
            title="accusamus beatae ad facilis cum similique qui sunt",
            url="https://via.placeholder.com/600/92c952",
            thumbnailUrl="https://via.placeholder.com/150/92c952"
        )
        val photo2 = Photo(
            albumId= 1,
            id= 1,
            title="accusamus beatae ad facilis cum similique qui sunt",
            url="https://via.placeholder.com/600/92c952",
            thumbnailUrl="https://via.placeholder.com/150/92c952"
        )
        val photo3 = Photo(
            albumId= 1,
            id= 1,
            title="accusamus beatae ad facilis cum similique qui sunt",
            url="https://via.placeholder.com/600/92c952",
            thumbnailUrl="https://via.placeholder.com/150/92c952"
        )

        return listOf(photo1,photo2,photo3)
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