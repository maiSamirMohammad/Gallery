package com.example.retrofit.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.retrofit.R
import com.example.retrofit.data.model.Photo
import com.example.retrofit.databinding.ItemFavoriteBinding

class FavoriteAdapter(val listener:OnDeletePhotoClickListener): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>()  {
    lateinit var binding: ItemFavoriteBinding
    private var oldPhotoList= emptyList<Photo>()

    // Replace the contents of a view (invoked by the layout manager)
    class ViewHolder(var binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val inflater= LayoutInflater.from(parent.context)
        binding= ItemFavoriteBinding.inflate(inflater, parent, false)
        return FavoriteAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentPhoto=oldPhotoList[position]
        val thumbnailUrl: String = currentPhoto.thumbnailUrl
        val url: String = currentPhoto.url
        thumbnailUrl.let {
            binding.imageView.load(it) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
        binding.btnDelete.setOnClickListener {
            try {
                listener.onDeleteClick(currentPhoto)
            }catch (e:Exception){
                Toast.makeText(binding.root.context,"${e.message}", Toast.LENGTH_LONG).show()
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