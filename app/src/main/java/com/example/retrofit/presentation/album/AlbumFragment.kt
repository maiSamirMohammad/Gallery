package com.example.retrofit.presentation.album

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.retrofit.domain.entities.Photo
import com.example.retrofit.databinding.FragmentAlbumBinding
import com.example.retrofit.domain.utils.NetworkResponseState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumFragment : Fragment(), OnSavePhotoClickListener {

    private var _binding: FragmentAlbumBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    val args: AlbumFragmentArgs by navArgs()
    private val albumViewModel: AlbumViewModel by viewModels()
    private lateinit var  albumAdapter: AlbumAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.title.text=args.album.title
        //setup recycler view
        albumAdapter  = AlbumAdapter(this)
        binding.recyclerviewPhotos.adapter=albumAdapter
        albumViewModel.getPhotos(args.album.id)
        lifecycleScope.launch {
            albumViewModel.photos.collectLatest {apiResult ->
                when(apiResult)  {
                    is NetworkResponseState.OnLoading ->{
                        binding.progressBar.visibility= View.VISIBLE
                    }
                    is NetworkResponseState.OnSuccess ->{
                        binding.progressBar.visibility= View.GONE
                        albumAdapter.setData(apiResult.data)
                    }
                    is NetworkResponseState.OnError ->{
                        binding.progressBar.visibility= View.GONE
                        Log.e("TAG", "error==========:${apiResult.error.message} ")
                        Snackbar.make(activity?.window?.decorView!!.rootView,
                            "Try another time as ${apiResult.error.message}",
                            Snackbar.LENGTH_LONG)
                            .setBackgroundTint(resources.getColor(android.R.color.holo_orange_light))
                            .show()
                    }

                    is NetworkResponseState.OnNetworkErrorResponse -> {
                        binding.progressBar.visibility= View.GONE
                        Log.e("TAG", "error :${apiResult.statusCode} ")
                        Snackbar.make(activity?.window?.decorView!!.rootView,
                            "Error ${apiResult.statusCode}",
                            Snackbar.LENGTH_LONG)
                            .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark))
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