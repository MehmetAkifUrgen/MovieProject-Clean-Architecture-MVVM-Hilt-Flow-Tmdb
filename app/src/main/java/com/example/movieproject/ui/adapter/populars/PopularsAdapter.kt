package com.example.movieproject.ui.adapter.populars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.databinding.ItemMovieLayoutBinding
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

class PopularsAdapter(private val popularAdapterList : ArrayList<PopularUiModel>, private val itemClick: (PopularUiModel) -> Unit):RecyclerView.Adapter<PopularsAdapter.PopularViewHolder>() {

    class PopularViewHolder(val binding: ItemMovieLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = ItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {

        holder.binding.apply {
           //agentNameTextView.text = popularAdapterList[position].title
            agentImageView.loadImage(imagePath+ popularAdapterList[position].posterPath)

            agentCardView.setOnClickListener {
                itemClick.invoke(popularAdapterList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return popularAdapterList.size
    }
}