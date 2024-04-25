package com.example.retrofit.presentation.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var  profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        //setup recycler view
        profileAdapter  = ProfileAdapter()
        binding.recyclerviewAlbums.adapter=profileAdapter
        profileViewModel.getAlbums()
        lifecycleScope.launch {
            profileViewModel.albums.collectLatest {apiResult ->
                when(apiResult)  {
                    is AlbumAPIState.Loading ->{
                        binding.progressBar.visibility= View.VISIBLE
                    }
                    is AlbumAPIState.Success ->{
                        binding.progressBar.visibility= View.GONE
                        profileAdapter.setData(apiResult.data)
                    }
                    is AlbumAPIState.Failure ->{
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
        binding.btnFavorite.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment_to_favoriteFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}