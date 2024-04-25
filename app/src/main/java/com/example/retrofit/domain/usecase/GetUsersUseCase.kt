package com.example.retrofit.domain.usecase

import com.example.retrofit.domain.entities.UsersResponse
import com.example.retrofit.domain.repository.IRepository
import com.example.retrofit.domain.utils.NetworkResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: IRepository) {

     operator fun invoke() = flow {
        val response = coroutineScope{
            val response = async(Dispatchers.IO){ repository.getUsers() }
            emit(NetworkResponseState.OnLoading<UsersResponse?>())
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