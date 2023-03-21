package com.example.movieproject.ui.fragment.populars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.databinding.FragmentMovieBinding
import com.example.movieproject.ui.adapter.populars.PopularPagingAdapter
import com.example.movieproject.ui.adapter.upcoming.UpComingPagingAdapter
import com.example.movieproject.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>() {


    private val popularAdapter = PopularPagingAdapter()
    private val upComingsAdapter = UpComingPagingAdapter()
    private val viewModel: MovieViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_movie



    override fun prepareView(savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.progressBar.visibility=View.VISIBLE
            delay(1000)
            binding.progressBar.visibility=View.GONE
        }
        setRecyclerView()

        PopularItemClick()
        UpComingItemClick()
        PagingObserve()

    }

    private fun PagingObserve() {
        viewModel.popularlist.observe(viewLifecycleOwner) {
            popularAdapter.submitData(lifecycle, it)
            binding.progressBar.visibility=View.GONE
        }
        viewModel.upCominglist.observe(viewLifecycleOwner){
            upComingsAdapter.submitData(lifecycle,it)
        }

    }

    private fun PopularItemClick() {
        popularAdapter.onMovieClick {
            findNavController().navigate(
                MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
                    it
                )
            )
        }
    }
    private fun UpComingItemClick() {
        upComingsAdapter.onMovieClick {
            findNavController().navigate(
                MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
                    it
                )
            )
        }
    }

    private fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter = popularAdapter
            layoutManager = GridLayoutManager(requireContext(), 1,GridLayoutManager.HORIZONTAL,false)
        }
        binding.upcomingRecycler.apply {
            adapter=upComingsAdapter
            layoutManager = GridLayoutManager(requireContext(), 1,GridLayoutManager.HORIZONTAL,false)
        }

    }




}