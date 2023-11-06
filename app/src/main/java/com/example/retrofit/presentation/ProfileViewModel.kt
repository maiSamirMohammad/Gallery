package com.example.retrofit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.AlbumAPIState
import com.example.retrofit.data.MyRetrofit
import com.example.retrofit.data.Repository
import com.example.retrofit.data.model.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel(private val repository:Repository):ViewModel(){
   /* private var _albums=MutableLiveData<List<Album>>()
    val albums= _albums*/
    private var _albums= MutableStateFlow<AlbumAPIState>(AlbumAPIState.Loading)
    val albums= _albums.asStateFlow()
     fun getAlbums()
     {
         viewModelScope.launch(Dispatchers.IO){

             repository.getAlbums().catch { throwable->
                 _albums.value=AlbumAPIState.Failure(throwable)
             }.collectLatest{ albums->
                 _albums.value=AlbumAPIState.Success(albums)

             }

         }
     }

}