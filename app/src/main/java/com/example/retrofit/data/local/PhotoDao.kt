package com.example.retrofit.data.local

import androidx.room.*
import com.example.retrofit.data.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM Photo")
    fun getAllPhotos(): Flow<List<Photo>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: Photo): Long

    @Delete
    suspend fun deletePhoto(photo: Photo)

}