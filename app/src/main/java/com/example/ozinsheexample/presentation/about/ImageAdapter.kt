package com.example.ozinsheexample.presentation.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinsheexample.data.model.Screenshot
import com.example.ozinsheexample.databinding.ItemImageBinding
import com.example.ozinsheexample.databinding.ItemMainMoviesBinding
import com.example.ozinsheexample.presentation.home.MainMovieAdapter
import com.example.ozinsheexample.presentation.home.RcViewItemClickMainMoviesCallback
import com.example.ozinsheexample.presentation.home.mainresponse.MainMoviesResponseItem

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<Screenshot>() {
        override fun areItemsTheSame(
            oldItem: Screenshot,
            newItem: Screenshot
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Screenshot,
            newItem: Screenshot
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    fun sumbitList(list:List<Screenshot>){
        differ.submitList(list)
    }

    private var listenerClickAtItem: RcViewItemClickImageCallBack? = null

    fun setOnImageClickListener(listener: RcViewItemClickImageCallBack){
        this.listenerClickAtItem = listener
    }

    inner class ImageHolder(private var binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItem(image: Screenshot){
            Glide.with(itemView.context)
                .load(image.link)
                .into(binding.imageView)
            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(image.link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}