package com.example.retrofit.domain.usecase

import com.example.retrofit.domain.entities.AlbumResponse
import com.example.retrofit.domain.repository.IRepository
import com.example.retrofit.domain.utils.NetworkResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(private val repository: IRepository)  {

     operator fun invoke(userId:Int) = flow {
        val response = coroutineScope{
            val response = async(Dispatchers.IO){ repository.getAlbums(userId) }
            emit(NetworkResponseState.OnLoading<AlbumResponse?>())
            response.await()
        }

        if (response.isSuccessful) {
            emit(NetworkResponseState.OnSuccess(response.body()))
        } else {
            emit(
                NetworkResponseState.OnNetworkErrorResponse(
                    statusCode = response.code(),
                    data = response.errorBody().toString()
                )
            )
        }
    }.catch { emit(NetworkResponseState.OnError(it)) }
}