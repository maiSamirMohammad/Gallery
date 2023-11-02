package com.example.retrofit.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofit.data.model.Album
import com.example.retrofit.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val album1=Album(userId=1, id=1, title="quidem molestiae enim")
        val album2=Album(userId=1, id=1, title="quidem molestiae enim")
        val album3=Album(userId=1, id=1, title="quidem molestiae enim")
        val dummyData = listOf(album1, album2,album3)
        val profileAdapter  = ProfileAdapter()
        binding.recyclerviewAlbums.adapter=profileAdapter
        profileAdapter.setData(dummyData)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}