package com.example.movieproject.ui.fragment.populars

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.data.uimodel.populars.PopularDetails
import com.example.movieproject.databinding.FragmentAgentDetailBinding
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentDetailFragment : BaseFragment<FragmentAgentDetailBinding>() {

    private val viewModel : AgentDetailViewModel by viewModels()
    private val args : AgentDetailFragmentArgs by navArgs()
   // private lateinit var abilitiesAdapter : AbilitiesAdapter

    override fun getLayoutId(): Int = R.layout.fragment_agent_detail

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.agentDetailRequest(args.id)
        initAgentDetailItemObserve()
        //initBackButtonClickListener()
    }

  /* private fun initBackButtonClickListener(){
        binding.agentDetailLayout.backButtonImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }*/

    private fun initUi(agentDetailItem: PopularDetails) {
        binding.agentDetailLayout.apply {

            agentDetailItem.let { item ->
                topImage.loadImage(imagePath+ item.backdrop_path)
                moviePoster.loadImage(imagePath+ item.poster_path)
                movieDescription.text = item.overview
                movieDuration.text = item.id
                movieName.text = item.original_title
                movieYear.text = item.release_date
                movieRating.rating = item.vote_average!!.toFloat()/2

               // initAbilitiesAdapter(item)
            }
        }
    }

 /* private fun initAbilitiesAdapter(agentDetailItem: AgentDetailItem){
        agentDetailItem.abilities?.let {
            abilitiesAdapter = AbilitiesAdapter(it)
            binding.agentDetailLayout.abilitiesRecyclerView.adapter = abilitiesAdapter
        }
    }*/
    private fun initAgentDetailItemObserve() {
        viewModel.agentDetailItem.observe(viewLifecycleOwner){
            initUi(it)
        }
    }
}