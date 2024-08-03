package com.example.retrofit.domain.utils

import com.example.retrofit.domain.entities.UsersResponse

sealed class NetworkResponseState<T> {
    class OnLoading<T>: NetworkResponseState<T>()
    class OnSuccess<T>(val data: T): NetworkResponseState<T>()
    class OnError<T>(val error:Throwable): NetworkResponseState<T>()
    class OnNetworkErrorResponse<T>(val statusCode:Int, val data:String): NetworkResponseState<T>()
}