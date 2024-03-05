package com.example.retrofit.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.retrofit.data.PhotoAPIState
import com.example.retrofit.data.model.Photo
import com.example.retrofit.databinding.FragmentAlbumBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumFragment : Fragment(),OnSavePhotoClickListener {

    private var _binding: FragmentAlbumBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    val args:AlbumFragmentArgs by navArgs()
    private val albumViewModel:AlbumViewModel by viewModels()
    private lateinit var  albumAdapter:AlbumAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.title.text=args.album
        //setup recycler view
        albumAdapter  = AlbumAdapter(this)
        binding.recyclerviewPhotos.adapter=albumAdapter
        albumViewModel.getPhotos()
        lifecycleScope.launch {
            albumViewModel.photos.collectLatest {apiResult ->
                when(apiResult)  {
                    is PhotoAPIState.Loading ->{
                        binding.progressBar.visibility= View.VISIBLE
                    }
                    is PhotoAPIState.Success ->{
                        binding.progressBar.visibility= View.GONE
                        albumAdapter.setData(apiResult.data)
                    }
                    is PhotoAPIState.Failure ->{
                        binding.progressBar.visibility= View.GONE
                        Log.e("TAG", "error==========:${apiResult.throwable.message} ")
                        Snackbar.make(activity?.window?.decorView!!.rootView,
                            "Try another time as ${apiResult.throwable.message}",
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

    override fun onSaveClick(photo: Photo) {
        albumViewModel.insertPhoto(photo)
    }
}