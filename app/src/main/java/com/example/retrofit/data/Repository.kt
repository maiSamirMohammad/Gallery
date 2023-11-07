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
        return flow { emit(apiService.getAlbums()) } 
     }
    override suspend fun getPhotos(): Flow<List<Photo>> {
        return flow { emit(apiService.getPhotos()) }
        }
}