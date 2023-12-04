package com.example.retrofit.di

import com.example.retrofit.data.remote.APIService
import com.example.retrofit.data.Constants
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

 /*   @Provides
    @Singleton
    fun provideRepository(apiService: APIService): IRepository{
        return Repository(apiService)
    }*/

}