package com.example.movieproject.ui.adapter.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.uimodel.upcoming.UpComingUiModel
import com.example.movieproject.databinding.ItemAgentLayoutBinding
import com.example.movieproject.utils.loadImage

class UpComingsAdapter(private val agentsAdapterList : ArrayList<UpComingUiModel>, private val itemClick: (UpComingUiModel) -> Unit):RecyclerView.Adapter<UpComingsAdapter.AgentsViewHolder>() {

    class AgentsViewHolder(val binding: ItemAgentLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsViewHolder {
        val binding = ItemAgentLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AgentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentsViewHolder, position: Int) {
        val imagePath="https://image.tmdb.org/t/p/w500"
        holder.binding.apply {
            agentNameTextView.text = agentsAdapterList[position].title
            agentImageView.loadImage(imagePath+ agentsAdapterList[position].posterPath)

            agentCardView.setOnClickListener {
                itemClick.invoke(agentsAdapterList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return agentsAdapterList.size
    }
}