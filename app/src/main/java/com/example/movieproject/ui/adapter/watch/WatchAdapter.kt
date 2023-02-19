package com.example.movieproject.ui.adapter.watch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.watch.WatchUiModel
import com.example.movieproject.databinding.ItemCastLayoutBinding
import com.example.movieproject.databinding.ItemWatchLayoutBinding
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

class WatchAdapter(private val watchList: ArrayList<WatchUiModel>):RecyclerView.Adapter<WatchAdapter.WatchViewHolder>() {

    class WatchViewHolder(val binding: ItemWatchLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchViewHolder {
        val binding = ItemWatchLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchViewHolder, position: Int) {

        holder.binding.apply {
            providerName.text = watchList[position].provider_name
            providerImage.loadImage(imagePath+ watchList[position].logo_path)


        }
    }

    override fun getItemCount(): Int {
        return watchList.size
    }
}