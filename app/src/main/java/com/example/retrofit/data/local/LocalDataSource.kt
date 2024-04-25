package com.example.retrofit.data.local

import com.example.retrofit.domain.entities.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource@Inject constructor(
    private val photoDao: PhotoDao
) :ILocalDataSource {
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