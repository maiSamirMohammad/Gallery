package com.example.retrofit.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.retrofit.R
import com.example.retrofit.data.model.Photo
import com.example.retrofit.databinding.ItemPhotoBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    lateinit var binding: ItemPhotoBinding
    private var oldPhotoList= emptyList<Photo>()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(var binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val inflater= LayoutInflater.from(parent.context)
        binding= ItemPhotoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentAlbum=oldPhotoList[position]
        val url: String = currentAlbum.url
        url.let {
            binding.imageView.load(url) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = oldPhotoList.size
    fun setData(newPhotoList:List<Photo>){
        val diffUtil=MyPhotosDiffCallback(oldPhotoList,newPhotoList)
        val diffResults= DiffUtil.calculateDiff(diffUtil)
        oldPhotoList=newPhotoList
        diffResults.dispatchUpdatesTo(this)
    }
}
