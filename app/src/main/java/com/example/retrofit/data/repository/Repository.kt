package com.example.retrofit.data.repository

import com.example.retrofit.data.local.ILocalDataSource
import com.example.retrofit.domain.entities.AlbumResponse
import com.example.retrofit.domain.entities.Photo
import com.example.retrofit.domain.entities.PhotoResponse
import com.example.retrofit.domain.entities.UsersResponse
import com.example.retrofit.data.remote.IRemoteDataSource
import com.example.retrofit.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
     private val remoteDataSource: IRemoteDataSource,
     private val localDataSource: ILocalDataSource
) : IRepository {
    //remote
    override suspend fun getUsers(): Response<UsersResponse> {
        return remoteDataSource.getUsers()
    }
     override suspend fun getAlbums(userId: Int): Response<AlbumResponse>{
        return remoteDataSource.getAlbums(userId)
     }
    override suspend fun getPhotos(albumId:Int): Response<PhotoResponse> {
        return remoteDataSource.getPhotos(albumId)
        }
    //local
    override fun getLocalPhotos(): Flow<List<Photo>> {
        return localDataSource.getLocalPhotos()
    }
    override suspend fun insertPhoto(photo: Photo): Long {
        return localDataSource.insertPhoto(photo)
    }
    override suspend fun deletePhoto(photo: Photo) {
        localDataSource.deletePhoto(photo)
    }

}