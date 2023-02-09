package com.example.movieproject.ui.adapter.populars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.uimodel.populars.Popular
import com.example.movieproject.data.uimodel.populars.PopularDetails
import com.example.movieproject.databinding.ItemAgentLayoutBinding
import com.example.movieproject.utils.loadImage

class PopularsAdapter(private val agentsAdapterList : ArrayList<Popular>, private val itemClick: (Popular) -> Unit):RecyclerView.Adapter<PopularsAdapter.AgentsViewHolder>() {

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