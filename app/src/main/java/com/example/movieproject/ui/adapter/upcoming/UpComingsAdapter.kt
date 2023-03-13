package com.example.movieproject.ui.adapter.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.uimodel.upcoming.UpComingUiModel
import com.example.movieproject.databinding.ItemMovieLayoutBinding
import com.example.movieproject.utils.loadImage

class UpComingsAdapter(private val upcomingAdapterList : ArrayList<UpComingUiModel>, private val itemClick: (UpComingUiModel) -> Unit):RecyclerView.Adapter<UpComingsAdapter.UpComingViewHolder>() {

    class UpComingViewHolder(val binding: ItemMovieLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val binding = ItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UpComingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        val imagePath="https://image.tmdb.org/t/p/w500"
        holder.binding.apply {
           // agentNameTextView.text = upcomingAdapterList[position].title
            agentImageView.loadImage(imagePath+ upcomingAdapterList[position].posterPath)

            agentCardView.setOnClickListener {
                itemClick.invoke(upcomingAdapterList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return upcomingAdapterList.size
    }
}