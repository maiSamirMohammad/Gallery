package com.example.retrofit.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.example.retrofit.R
import com.example.retrofit.data.model.Photo
import com.example.retrofit.databinding.ItemPhotoBinding

class AlbumAdapter (val listener:OnPhotoClickListener): RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
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
        val currentPhoto=oldPhotoList[position]
        val thumbnailUrl: String = currentPhoto.thumbnailUrl
        val url: String = currentPhoto.url
        thumbnailUrl.let {
            binding.imageView.load(it) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
        binding.imageView.setOnClickListener {
            //TODO show image on dialog with save button

            val dialog= MaterialDialog(binding.root.context)
                .noAutoDismiss()
                .customView(R.layout.dialog_photo)

            url.let {
                dialog.findViewById<ImageView>(R.id.display_photo).load(it) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }
            dialog.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog.dismiss() }
            dialog.findViewById<Button>(R.id.btn_save).setOnClickListener {
                dialog.findViewById<ProgressBar>(R.id.progress_bar).visibility= View.VISIBLE
                try {
                    listener.onPhotoClick(currentPhoto)
                }catch (e:Exception){
                    Toast.makeText(binding.root.context,"${e.message}",Toast.LENGTH_LONG).show()
                }finally {
                    dialog.findViewById<ProgressBar>(R.id.progress_bar).visibility= View.VISIBLE
                    dialog.dismiss()
                }
                 }
            dialog.show()

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
