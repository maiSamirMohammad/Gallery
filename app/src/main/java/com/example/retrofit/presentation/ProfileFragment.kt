package com.example.retrofit.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.data.Repository
import com.example.retrofit.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var  profileViewModelFactory:ProfileViewModelFactory
    private lateinit var  profileViewModel:ProfileViewModel
    private lateinit var  profileAdapter:ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
       //Getting viewModel ready
        profileViewModelFactory=ProfileViewModelFactory(Repository)
        profileViewModel =
            ViewModelProvider(this,profileViewModelFactory).get(ProfileViewModel::class.java)
        //setup recycler view
        profileAdapter  = ProfileAdapter()
        binding.recyclerviewAlbums.adapter=profileAdapter
        profileViewModel.getAlbums().observe(viewLifecycleOwner){albums ->
            if (albums!=null)
                profileAdapter.setData(albums)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}