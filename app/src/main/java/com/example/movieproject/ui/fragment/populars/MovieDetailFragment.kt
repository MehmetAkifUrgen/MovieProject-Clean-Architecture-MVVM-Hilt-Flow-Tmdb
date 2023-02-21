package com.example.movieproject.ui.fragment.populars

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.data.uimodel.cast.CastDetailUiModel
import com.example.movieproject.data.uimodel.cast.CrewDetailUiModel
import com.example.movieproject.data.uimodel.populars.PopularDetailsUiModel
import com.example.movieproject.data.uimodel.watch.WatchUiModel
import com.example.movieproject.databinding.FragmentMovieDetailBinding
import com.example.movieproject.ui.adapter.cast.CastAdapter
import com.example.movieproject.ui.adapter.cast.CrewAdapter
import com.example.movieproject.ui.adapter.watch.WatchAdapter
import com.example.movieproject.ui.fragment.cast.CastViewModel
import com.example.movieproject.ui.fragment.watch.WatchViewModel
import com.example.movieproject.utils.Constants.imagePath
import com.example.movieproject.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private val watchViewModel: WatchViewModel by viewModels()
    private val castViewModel: CastViewModel by viewModels()
    private var castAdapter: CastAdapter? = null
    private var crewAdapter: CrewAdapter? = null
    private var providerAdapter: WatchAdapter? = null
    private val args: MovieDetailFragmentArgs by navArgs()


    override fun getLayoutId(): Int = R.layout.fragment_movie_detail


    override fun prepareView(savedInstanceState: Bundle?) {
        watchViewModel.watchRequest(args.id)
        viewModel.agentDetailRequest(args.id)
        castViewModel.castRequest(args.id)

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
                movieDescription.text = item.overview
                movieDuration.text = item.id
                movieName.text = item.original_title
                movieYear.text = item.release_date
                movieRating.rating = item.vote_average!!.toFloat() / 2

                var isShow = true
                var scrollRange = -1
                appBar.addOnOffsetChangedListener { barLayout, verticalOffset ->
                    if (scrollRange == -1) {
                        scrollRange = barLayout?.totalScrollRange!!
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolBar.title = item.original_title
                        collapsingToolBar.setCollapsedTitleTextColor(Color.WHITE)
                        isShow = true
                    } else if (isShow) {
                        collapsingToolBar.title =
                            " " //careful there should a space between double quote otherwise it wont work
                        isShow = false
                    }
                }

                // initAbilitiesAdapter(item)
            }
        }

    }

    private fun crewUi(crewDetailUiModel: CrewDetailUiModel) {
        binding.agentDetailLayout.directorName.text = crewDetailUiModel.name
    }

    private fun initAgentDetailItemObserve() {

       try {
           viewModel.agentDetailItem.observe(viewLifecycleOwner) {
               initUi(it)
           }
           castViewModel.castAdapterList.observe(viewLifecycleOwner) {
               initCastAdapter(it)
           }
           castViewModel.director.observe(viewLifecycleOwner) {
               crewUi(it)
           }
           castViewModel.crewAdapterList.observe(viewLifecycleOwner) {
               initCrewdapter(it)
           }
           watchViewModel.pro.observe(viewLifecycleOwner) {
               Log.d("klass", it.toString())
               initProviderAdapter(it)
           }
       }
       catch (e:Exception){
           Toast.makeText(context,"$e",Toast.LENGTH_SHORT)
       }

    }

    private fun initCastAdapter(castItem: ArrayList<CastDetailUiModel>) {

        castItem.let {
            castAdapter = CastAdapter(it)
            binding.agentDetailLayout.castRecyclerView.adapter = castAdapter

        }
    }

    private fun initCrewdapter(crewItem: ArrayList<CrewDetailUiModel>) {

        crewItem.let {
            crewAdapter = CrewAdapter(it)
            binding.agentDetailLayout.crewRecyclerView.adapter = crewAdapter

        }
    }

    private fun initProviderAdapter(providerItem: ArrayList<WatchUiModel>) {

        providerItem.let {
            providerAdapter = WatchAdapter(it)
            binding.agentDetailLayout.providerRecylerView.adapter = providerAdapter

        }
    }


}