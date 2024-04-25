package com.example.retrofit.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.domain.entities.Album
import com.example.retrofit.databinding.ItemAlbumBinding
import com.example.retrofit.presentation.ProfileFragmentDirections

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    lateinit var binding: ItemAlbumBinding
    private var oldAlbumList= emptyList<Album>()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(var binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val inflater= LayoutInflater.from(parent.context)
        binding=ItemAlbumBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentAlbum=oldAlbumList[position]
        viewHolder.binding.title.text=currentAlbum.title
        viewHolder.binding.title.setOnClickListener {
            val action =
                ProfileFragmentDirections.actionProfileFragmentToAlbumFragment(currentAlbum.title)
            androidx.navigation.Navigation.findNavController(viewHolder.binding.root).navigate(action)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = oldAlbumList.size

    fun setData(newAlbumList:List<Album>){
        val diffUtil= MyAlbumDiffCallback(oldAlbumList,newAlbumList)
        val diffResults=DiffUtil.calculateDiff(diffUtil)
        oldAlbumList=newAlbumList
        diffResults.dispatchUpdatesTo(this)
    }

}
