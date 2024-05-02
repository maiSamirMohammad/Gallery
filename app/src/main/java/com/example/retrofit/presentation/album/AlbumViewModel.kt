package com.example.retrofit.presentation.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.entities.Photo
import com.example.retrofit.domain.entities.PhotoResponse
import com.example.retrofit.domain.repository.IRepository
import com.example.retrofit.domain.usecase.GetPhotosUseCase
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
class AlbumViewModel  @Inject constructor(val getPhotosUseCase: GetPhotosUseCase) :ViewModel(){
    private var _photos= MutableStateFlow<NetworkResponseState<PhotoResponse?>>(NetworkResponseState.OnLoading())
    val photos= _photos.asStateFlow()
    fun getPhotos(albumId:Int)
    {
        viewModelScope.launch(Dispatchers.IO){
            getPhotosUseCase(albumId).catch { throwable->
                _photos.value= NetworkResponseState.OnError(throwable)
            }.collectLatest{ photos->
                _photos.value= photos
            }
        }
    }

    fun insertPhoto(photo: Photo){
        viewModelScope.launch(Dispatchers.IO){
            getPhotosUseCase.repository.insertPhoto(photo)
        }
    }
}