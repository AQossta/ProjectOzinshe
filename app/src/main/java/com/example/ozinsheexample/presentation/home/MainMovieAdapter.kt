package com.example.ozinsheexample.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinsheexample.databinding.ItemMainMoviesBinding
import com.example.ozinsheexample.presentation.home.mainresponse.MainMoviesResponseItem

class MainMovieAdapter: RecyclerView.Adapter<MainMovieAdapter.MainMovieHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<MainMoviesResponseItem>() {
        override fun areItemsTheSame(
            oldItem: MainMoviesResponseItem,
            newItem: MainMoviesResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MainMoviesResponseItem,
            newItem: MainMoviesResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    fun sumbitList(listMoviesMain:List<MainMoviesResponseItem>){
        differ.submitList(listMoviesMain)
    }

    private var listenerClickAtItem: RcViewItemClickMainMoviesCallback? = null

    fun setOnMovieClickListener(listener: RcViewItemClickMainMoviesCallback){
        this.listenerClickAtItem = listener
    }

    inner class MainMovieHolder(private var binding: ItemMainMoviesBinding) :RecyclerView.ViewHolder(binding.root){
        fun bindItem(mainMovieItem: MainMoviesResponseItem){
            Glide.with(itemView.context)
                .load(mainMovieItem.link)
                .into(binding.imgMainMovie)

            binding.tvTextTitle.text = mainMovieItem.movie.name
            binding.tvTextDescription.text = mainMovieItem.movie.description
            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(mainMovieItem.movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMovieHolder {
        return MainMovieHolder(
            ItemMainMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MainMovieHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}