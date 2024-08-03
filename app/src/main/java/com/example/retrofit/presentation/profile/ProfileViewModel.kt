package com.example.retrofit.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.entities.AlbumResponse
import com.example.retrofit.domain.entities.User
import com.example.retrofit.domain.entities.UsersResponse
import com.example.retrofit.domain.usecase.GetAlbumsUseCase
import com.example.retrofit.domain.usecase.GetUsersUseCase
import com.example.retrofit.domain.utils.NetworkResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getAlbumsUseCase: GetAlbumsUseCase
):ViewModel(){

    private var _user= MutableStateFlow<NetworkResponseState<UsersResponse?>>(NetworkResponseState.OnLoading())
    val user= _user.asStateFlow()

    private var _albums= MutableStateFlow<NetworkResponseState<AlbumResponse?>>(NetworkResponseState.OnLoading())
    val albums= _albums.asStateFlow()

    fun getRandomUser(){
        viewModelScope.launch(Dispatchers.IO){
            getUsersUseCase().catch { throwable->
                _user.value= NetworkResponseState.OnError(throwable)
            }.collectLatest{ response->
                if (response is NetworkResponseState.OnSuccess){
                    val randomUser= response.data?.random()
                    getAlbums(randomUser!!.id)
                    val userResponse= UsersResponse()
                    userResponse.add(randomUser)
                    _user.value= NetworkResponseState.OnSuccess(userResponse)
                }else{
                    _user.value= response
                }

                }
            }
        }

    fun getAlbums(userId:Int){
        viewModelScope.launch(Dispatchers.IO){
            getAlbumsUseCase(userId).catch { throwable->
                _albums.value= NetworkResponseState.OnError(throwable)
            }.collectLatest{ response->

                _albums.value= response


            }
        }
    }
    }


