package com.example.movieproject.ui.fragment.populars

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularDetailsUiModel
import com.example.movieproject.databinding.FragmentAgentDetailBinding
import com.example.movieproject.ui.adapter.cast.CastAdapter
import com.example.movieproject.ui.fragment.cast.CastViewModel
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AgentDetailFragment : BaseFragment<FragmentAgentDetailBinding>() {

    private val viewModel: AgentDetailViewModel by viewModels()
    private val castViewModel: CastViewModel by viewModels()
    private var castAdapter: CastAdapter? = null
    private val args: AgentDetailFragmentArgs by navArgs()


    override fun getLayoutId(): Int = R.layout.fragment_agent_detail



    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.agentDetailRequest(args.id)
        castViewModel.castRequest(args.id)
        Log.d("idd",args.id)
        initAgentDetailItemObserve()
        initBackButtonClickListener()
    }

    private fun initBackButtonClickListener() {
        binding.agentDetailLayout.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun initUi(agentDetailItem: PopularDetailsUiModel) {
        binding.agentDetailLayout.apply {

            agentDetailItem.let { item ->
                topImage.loadImage(imagePath + item.backdrop_path)
                moviePoster.loadImage(imagePath + item.poster_path)
                collapsingToolBar.transitionName = item.original_title
                movieDescription.text = item.overview
                movieDuration.text = item.id
                movieName.text = item.original_title
                movieYear.text = item.release_date
                movieRating.rating = item.vote_average!!.toFloat() / 2

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
        viewModel.agentDetailItem.observe(viewLifecycleOwner) {
            initUi(it)
        }
        castViewModel.castAdapterList.observe(viewLifecycleOwner){
            initCastAdapter(it)
        }
    }
    private fun initCastAdapter(castItem: ArrayList<CastDetailUiModel>){

        castItem.let {
            castAdapter = CastAdapter(it)
            binding.agentDetailLayout.castRecyclerView.adapter = castAdapter

        }
    }



}