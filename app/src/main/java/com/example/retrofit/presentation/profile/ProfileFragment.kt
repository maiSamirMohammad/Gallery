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
import com.example.retrofit.domain.utils.NetworkResponseState
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
        profileViewModel.getRandomUser()

        lifecycleScope.launch {
            profileViewModel.user.collectLatest {apiResult ->
                when(apiResult)  {
                    is NetworkResponseState.OnLoading ->{
                        binding.progressBar.visibility= View.VISIBLE
                    }
                    is NetworkResponseState.OnSuccess ->{
                        binding.progressBar.visibility= View.GONE
                        binding.name.text=apiResult.data!![1].name
                        //lifecycleScope.launch {
                            profileViewModel.albums.collectLatest {albumResult ->
                                when(albumResult)  {
                                    is NetworkResponseState.OnLoading ->{
                                        binding.progressBar.visibility= View.VISIBLE
                                    }
                                    is NetworkResponseState.OnSuccess ->{
                                        binding.progressBar.visibility= View.GONE
                                        profileAdapter.setData(albumResult.data)
                                    }
                                    is NetworkResponseState.OnError ->{
                                        binding.progressBar.visibility= View.GONE
                                        Log.e("TAG", "error==========:${albumResult.error.message} ")
                                        Snackbar.make(activity?.window?.decorView!!.rootView,
                                            "Try another time as ${albumResult.error.message}",
                                            Snackbar.LENGTH_LONG)
                                            .setBackgroundTint(resources.getColor(android.R.color.holo_orange_light))
                                            .show()
                                    }

                                    is NetworkResponseState.OnNetworkErrorResponse -> {
                                        binding.progressBar.visibility= View.GONE
                                        Log.e("TAG", "error :${albumResult.statusCode} ")
                                        Snackbar.make(activity?.window?.decorView!!.rootView,
                                            "Error ${albumResult.statusCode}",
                                            Snackbar.LENGTH_LONG)
                                            .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark))
                                            .show()
                                    }
                                }
                            }
                        //}
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