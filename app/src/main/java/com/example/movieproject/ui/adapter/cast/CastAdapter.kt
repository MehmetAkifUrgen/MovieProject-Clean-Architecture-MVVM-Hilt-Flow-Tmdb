package com.example.movieproject.ui.adapter.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.databinding.ItemCastLayoutBinding
import com.example.movieproject.utils.Constants
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

class CastAdapter(private val castList: ArrayList<CastDetailUiModel>):RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(val binding: ItemCastLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ItemCastLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {

        holder.binding.apply {
            caracterText.text = castList[position].character
            castImage.loadImage(imagePath + castList[position].profile_path)
            realName.text=castList[position].original_name

        }
    }

    override fun getItemCount(): Int {
        return castList.size
    }
}