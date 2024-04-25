package com.example.retrofit.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.entities.AlbumResponse
import com.example.retrofit.domain.entities.UsersResponse
import com.example.retrofit.domain.usecase.GetAlbumsUseCase
import com.example.retrofit.domain.usecase.GetUsersUseCase
import com.example.retrofit.domain.utils.NetworkResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getAlbumsUseCase: GetAlbumsUseCase
):ViewModel(){

    private var _users= MutableStateFlow(NetworkResponseState.OnLoading<UsersResponse>())
    val users= _users.asStateFlow()

    private var _albums= MutableStateFlow(NetworkResponseState.OnLoading<AlbumResponse>())
    val albums= _albums.asStateFlow()

     fun getAlbums()
     {
         viewModelScope.launch(Dispatchers.IO){
             repository.getAlbums().catch { throwable->
                 _albums.value= AlbumAPIState.Failure(throwable)
             }.collectLatest{ albums->
                 _albums.value= AlbumAPIState.Success(albums)
             }
         }
     }
}