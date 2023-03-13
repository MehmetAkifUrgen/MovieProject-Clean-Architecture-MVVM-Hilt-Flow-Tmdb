package com.example.movieproject.ui.adapter.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.databinding.ItemCastLayoutBinding
import com.example.movieproject.databinding.ItemMovieLayoutBinding
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

class DiscoverAdapter(private val discoverList: ArrayList<PopularUiModel>, private val itemClick: (PopularUiModel) -> Unit):RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder>() {

    class DiscoverViewHolder(val binding: ItemMovieLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding = ItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DiscoverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {

        holder.binding.apply {
           // agentNameTextView.text = discoverList[position].title
            agentImageView.loadImage(imagePath+ discoverList[position].posterPath)

            agentCardView.setOnClickListener {
                itemClick.invoke(discoverList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return discoverList.size
    }
}