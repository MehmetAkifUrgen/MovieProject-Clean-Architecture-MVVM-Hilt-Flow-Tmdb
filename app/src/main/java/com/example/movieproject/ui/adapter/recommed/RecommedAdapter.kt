package com.example.movieproject.ui.adapter.recommed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.response.popular.Movie
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.databinding.ItemCastLayoutBinding
import com.example.movieproject.databinding.ItemMovieLayoutBinding
import com.example.movieproject.databinding.ItemRecommedBinding
import com.example.movieproject.utils.Constants
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

class RecommedPagingAdapter : PagingDataAdapter<Movie, RecommedPagingAdapter.MyViewHolder>(DIFF_UTIL) {


    var onCLick: ((String) -> Unit)? = null

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id== newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }


    fun onMovieClick(listener: (String) -> Unit) {
        onCLick = listener
    }

    inner class MyViewHolder(val viewDataBinding: ItemRecommedBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)

        if (data != null) {
            holder.viewDataBinding.moviePoster.loadImage(imagePath+data.poster_path)
            holder.viewDataBinding.movieName.text=data.title
        }

        holder.viewDataBinding.root.setOnClickListener {
            onCLick?.let {
                if (data != null) {
                    it(data.id.toString())
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecommedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}