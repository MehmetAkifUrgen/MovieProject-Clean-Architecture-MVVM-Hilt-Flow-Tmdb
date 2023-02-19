package com.example.movieproject.ui.fragment.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.databinding.FragmentMovieBinding
import com.example.movieproject.databinding.FragmentSearchBinding
import com.example.movieproject.ui.adapter.populars.PopularsAdapter
import com.example.movieproject.ui.adapter.searchMovie.SearchMovieAdapter
import com.example.movieproject.ui.adapter.upcoming.UpComingsAdapter
import com.example.movieproject.ui.fragment.populars.MovieFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {


    private var searchAdapter: SearchMovieAdapter? = null
    private val viewModel : SearchViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.castRequest(s.toString())
                initAgentAdapterListObserve()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun initAdapter(){
        viewModel.searchAdapterList.value?.let { adapterList->
            searchAdapter = SearchMovieAdapter(adapterList, itemClick = {
                it.id?.let { uuid->
                    findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(uuid))
                }
            })
            binding.searchRecyclerView.adapter = searchAdapter
        }
    }

    private fun initAgentAdapterListObserve(){
        viewModel.searchAdapterList.observe(viewLifecycleOwner){
            initAdapter()
        }
    }


}