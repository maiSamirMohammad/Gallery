package com.example.retrofit.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.retrofit.data.Repository
import com.example.retrofit.databinding.FragmentAlbumBinding


class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    val args:AlbumFragmentArgs by navArgs()
    private lateinit var  albumViewModelFactory:AlbumViewModelFactory
    private lateinit var  albumViewModel:AlbumViewModel
    private lateinit var  albumAdapter:AlbumAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.title.text=args.album
        //Getting viewModel ready
        albumViewModelFactory=AlbumViewModelFactory(Repository())
        albumViewModel =
            ViewModelProvider(this,albumViewModelFactory).get(AlbumViewModel::class.java)
        //setup recycler view
        albumAdapter  = AlbumAdapter()
        binding.recyclerviewPhotos.adapter=albumAdapter
        albumViewModel.getPhotos().observe(viewLifecycleOwner){photos ->
            if (photos!=null)
                albumAdapter.setData(photos)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}