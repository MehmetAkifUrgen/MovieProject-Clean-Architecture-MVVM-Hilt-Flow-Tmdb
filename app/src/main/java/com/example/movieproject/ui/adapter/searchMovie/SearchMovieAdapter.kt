package com.example.movieproject.ui.adapter.searchMovie

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

class SearchMovieAdapter(private val searchMovieList: ArrayList<PopularUiModel>,private val itemClick: (PopularUiModel) -> Unit):RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    class SearchMovieViewHolder(val binding: ItemMovieLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val binding = ItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {

        holder.binding.apply {
            agentNameTextView.text = searchMovieList[position].title
            agentImageView.loadImage(imagePath+ searchMovieList[position].posterPath)

            agentCardView.setOnClickListener {
                itemClick.invoke(searchMovieList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return searchMovieList.size
    }
}