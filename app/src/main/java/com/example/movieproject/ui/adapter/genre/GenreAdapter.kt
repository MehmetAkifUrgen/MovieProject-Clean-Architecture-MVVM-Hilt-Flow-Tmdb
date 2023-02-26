package com.example.movieproject.ui.adapter.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.uimodel.genre.GenreUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.databinding.GenreItemBinding
import com.example.movieproject.databinding.ItemMovieLayoutBinding
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

class GenreAdapter(private val genreAdapterList : ArrayList<GenreUiModel>, private val itemClick: (GenreUiModel) -> Unit):RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    class GenreViewHolder(val binding: GenreItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {

        holder.binding.apply {
            genreName.text = genreAdapterList[position].name


            genreCard.setOnClickListener {
                itemClick.invoke(genreAdapterList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return genreAdapterList.size
    }
}