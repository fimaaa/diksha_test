package com.example.basedagger.ui.adapter.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basedagger.data.model.Photos
import com.example.basedagger.databinding.ItemPhotosBinding
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class AdapterPhotos(
    private val actionPhotos: (Photos.Data) -> Unit
): RecyclerView.Adapter<AdapterPhotos.ViewHolder>() {
    private val listPhotos = mutableListOf<Photos.Data?>()

    fun setListPhotos(listPhotos: MutableList<Photos.Data>) {
        this.listPhotos.clear()
        this.listPhotos.addAll(listPhotos)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(
            photo: Photos.Data
        ) {
            binding.data = photo
            binding.root.onLongClick {
                actionPhotos.invoke(photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemPhotosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listPhotos[position]?.let { it ->
            holder.bindItem(it)
        }
    }

    override fun getItemCount(): Int = listPhotos.size
}