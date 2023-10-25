package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

const val TAG = "RetrofitMainActivity"
const val TODO = "TODOS"
const val ALBUM = "ALBUMS"
const val POST = "POSTS"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.todos.setOnClickListener{
            getData(TODO)
        }
        binding.albums.setOnClickListener {
            getData(ALBUM)
        }
        binding.posts.setOnClickListener {
            getData(POST)
        }
    }
    fun getData(dataType:String){
        lifecycleScope.launch {
            binding.progressBar.isVisible = true
            try {
                val response = when(dataType){
                    TODO->{MyRetrofit.apiService.getTodos()}
                    ALBUM->{MyRetrofit.apiService.getAlbums()}
                    else -> {MyRetrofit.apiService.getPosts()}
                }
                if(response.isSuccessful && response.body() != null) {
                    Log.e(TAG, "MY $dataType : ${response.body()}")
                } else {
                    Log.e(TAG, "Response not successful or data is null")
                }
                binding.progressBar.isVisible = false

            } catch(e:Exception) {
                Log.e(TAG, "ExceptionNNNN: ${e.message}")
                binding.progressBar.isVisible = false
            }

        }
    }
}