package com.example.movieproject.ui.adapter.watchList

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.R
import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.databinding.ItemCastLayoutBinding
import com.example.movieproject.databinding.ItemMovieLayoutBinding
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

class WatchListAdapter(
    private val watchList: ArrayList<PopularUiModel>,
    private val itemClick: (PopularUiModel) -> Unit,
    private val watchClick: () -> Unit
) : RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder>() {

    class WatchListViewHolder(val binding: ItemMovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val binding =
            ItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {

        holder.binding.apply {
            watched.z = 2f
            watched.setOnClickListener {

                watchClick.invoke()
            }


            // agentNameTextView.text = searchMovieList[position].title
            agentImageView.loadImage(imagePath + watchList[position].posterPath.toString())

            agentCardView.setOnClickListener {
                itemClick.invoke(watchList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return watchList.size
    }
}