package com.example.movieproject.ui.adapter.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.data.response.casting.Cast
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.cast.CrewDetailUiModel
import com.example.movieproject.databinding.ItemCastLayoutBinding
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

class CrewAdapter(private val crewList: ArrayList<CrewDetailUiModel>):RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    class CrewViewHolder(val binding: ItemCastLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val binding = ItemCastLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {

        holder.binding.apply {
            caracterText.text = crewList[position].job
            castImage.loadImage(imagePath+ crewList[position].profile_path)
            realName.text=crewList[position].name

        }
    }

    override fun getItemCount(): Int {
        return crewList.size
    }
}