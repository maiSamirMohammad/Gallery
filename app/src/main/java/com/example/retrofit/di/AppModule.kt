package com.example.retrofit.di

import android.app.Application
import com.example.retrofit.data.remote.APIService
import com.example.retrofit.data.Constants
import com.example.retrofit.data.local.AppDataBase
import com.example.retrofit.data.local.ILocalDataSource
import com.example.retrofit.data.local.LocalDataSource
import com.example.retrofit.data.local.PhotoDao
import com.example.retrofit.data.remote.IRemoteDataSource
import com.example.retrofit.data.remote.RemoteDataSource
import com.example.retrofit.data.repository.Repository
import com.example.retrofit.domain.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAPIService(): APIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
    @Provides
    @Singleton
    fun providePhotoDao(appContext:Application): PhotoDao {
        val db: AppDataBase =AppDataBase.getInstance(appContext)
        return db.photoDao()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(): IRemoteDataSource {
        return RemoteDataSource(provideAPIService())
    }
    @Provides
    @Singleton
    fun provideLocalDataSource(appContext:Application): ILocalDataSource {
        return LocalDataSource(providePhotoDao(appContext))
    }
}