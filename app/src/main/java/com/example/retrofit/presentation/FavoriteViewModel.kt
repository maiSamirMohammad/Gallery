package com.example.retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.utils.PhotoLocalState
import com.example.retrofit.data.model.Photo
import com.example.retrofit.domain.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel  @Inject constructor(private val repository: IRepository): ViewModel(){
    private var _photos= MutableStateFlow<PhotoLocalState>(PhotoLocalState.Loading)
    val photos= _photos.asStateFlow()
    fun getLocalPhotos()
    {
        viewModelScope.launch(Dispatchers.IO){
            repository.getLocalPhotos().catch { throwable->
                _photos.value= PhotoLocalState.Failure(throwable)
            }.collectLatest{ photos->
                _photos.value= PhotoLocalState.Success(photos)
            }
        }
    }

    fun deletePhoto(photo: Photo){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePhoto(photo)
        }
    }
}