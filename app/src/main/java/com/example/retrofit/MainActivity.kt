package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.retrofit.Constants.ALBUM
import com.example.retrofit.Constants.Exception_MESSAGE
import com.example.retrofit.Constants.POST
import com.example.retrofit.Constants.TODO
import com.example.retrofit.Constants.TAG
import com.example.retrofit.Constants.UNSUCCESSFUL_MESSAGE
import com.example.retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.launch



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
                val response = Repository.getData(dataType)
                if(response.isSuccessful && response.body() != null) {
                    Log.e(TAG, "MY $dataType : ${response.body()}")
                } else {
                    Log.e(TAG, UNSUCCESSFUL_MESSAGE)
                }
                binding.progressBar.isVisible = false

            } catch(e:Exception) {
                Log.e(TAG, "$Exception_MESSAGE: ${e.message}")
                binding.progressBar.isVisible = false
            }

        }
    }
}