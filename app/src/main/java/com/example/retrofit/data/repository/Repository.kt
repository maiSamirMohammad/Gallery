package com.example.retrofit.data.repository

import com.example.retrofit.data.local.PhotoDao
import com.example.retrofit.data.model.Album
import com.example.retrofit.data.model.Photo
import com.example.retrofit.data.remote.APIService
import com.example.retrofit.domain.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
/* TODO add service api in constructor*/
     private val apiService: APIService,
     private val photoDao: PhotoDao
) : IRepository {
    
    // TODO call retrofit service
     override suspend fun getAlbums(): Flow<List<Album>> {
        return flow { emit(apiService.getAlbums()) } 
     }
    override suspend fun getPhotos(): Flow<List<Photo>> {
        return flow { emit(apiService.getPhotos()) }
        }
    //local
    override fun getLocalPhotos(): Flow<List<Photo>> {
        return photoDao.getAllPhotos()
    }
    override suspend fun insertPhoto(photo: Photo): Long {
        return photoDao.insertPhoto(photo)
    }
    override suspend fun deletePhoto(photo: Photo) {
        photoDao.deletePhoto(photo)
    }

}