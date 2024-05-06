package com.msu.morrison.chapter20

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.msu.morrison.chapter20.api.GalleryItem
import com.msu.morrison.chapter20.databinding.ListItemGalleryBinding

class PhotoListAdapter : PagingDataAdapter<GalleryItem, PhotoListAdapter.PhotoViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GalleryItem>() {
            override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class PhotoViewHolder(private val binding: ListItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(galleryItem: GalleryItem) {
            binding.itemImageView.load(galleryItem.url)
        }
    }
}
