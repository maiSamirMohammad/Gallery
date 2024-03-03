package com.example.retrofit.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.retrofit.R
import com.example.retrofit.data.PhotoLocalState
import com.example.retrofit.data.model.Photo
import com.example.retrofit.databinding.FragmentFavoriteBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment(),OnDeletePhotoClickListener  {
    private var _binding: FragmentFavoriteBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private val favoriteViewModel:FavoriteViewModel by viewModels()
    private lateinit var  favoriteAdapter: FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        //setup recycler view
        favoriteAdapter  = FavoriteAdapter(this)
        binding.recyclerviewFavorite.adapter=favoriteAdapter
        favoriteViewModel.getLocalPhotos()
        lifecycleScope.launch {
            favoriteViewModel.photos.collectLatest {localResult ->
                when(localResult)  {
                    is PhotoLocalState.Loading ->{
                        binding.progressBar.visibility= View.VISIBLE
                    }
                    is PhotoLocalState.Success ->{
                        binding.progressBar.visibility= View.GONE
                        favoriteAdapter.setData(localResult.data)
                    }
                    is PhotoLocalState.Failure ->{
                        binding.progressBar.visibility= View.GONE
                        Log.e("TAG", "error==========:${localResult.throwable.message} ")
                        Snackbar.make(activity?.window?.decorView!!.rootView,
                            "Try another time as ${localResult.throwable.message}",
                            Snackbar.LENGTH_LONG)
                            .setBackgroundTint(resources.getColor(android.R.color.holo_orange_light))
                            .show()
                    }
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteClick(photo: Photo) {
        favoriteViewModel.deletePhoto(photo)
    }

}