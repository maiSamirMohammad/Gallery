package com.example.retrofit.data

import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.Photo
import com.example.retrofit.domain.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(
/* TODO add service api in constructor*/
     private val apiService: APIService=MyRetrofit.apiService

) : IRepository {


    // TODO call retrofit service
     override suspend fun getAlbums(): Flow<List<Album>> {
        return flow {
            emit(
                apiService.getAlbums()
            )
        }
    }
    override suspend fun getPhotos(): Flow<List<Photo>> {
        return flow {
            emit(
                apiService.getPhotos()
            )
        }

        }
/*        val photo1 = Photo(
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

        return listOf(photo1,photo2,photo3)*/
    //}
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