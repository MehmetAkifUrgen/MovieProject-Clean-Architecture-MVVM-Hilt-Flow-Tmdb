package com.example.movieproject.ui.fragment.watchList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.databinding.FragmentProfileBinding
import com.example.movieproject.databinding.FragmentWatchlistBinding
import com.example.movieproject.ui.adapter.searchMovie.SearchMovieAdapter
import com.example.movieproject.ui.adapter.watchList.WatchListAdapter
import com.example.movieproject.ui.fragment.populars.MovieFragmentDirections
import com.example.movieproject.ui.fragment.search.SearchViewModel
import com.example.movieproject.utils.loadImage
import com.google.android.flexbox.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WatchListFragment : BaseFragment<FragmentWatchlistBinding>() {
    private val viewModel: WatchListViewModel by viewModels()
    private var watchListAdapter: WatchListAdapter? = null


    override fun getLayoutId(): Int = R.layout.fragment_watchlist


    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.getWatchList()
        observeWatchList()
        initAdapter()


    }
    private fun initAdapter() {
        viewModel.watchlistAdapterList.value?.let { adapterList ->
            Log.d("bbs",adapterList.toString())
            watchListAdapter = WatchListAdapter(adapterList, itemClick = {
                Log.e("hata",it.posterPath.toString())
                it.id?.let { uuid ->
                    findNavController().navigate(
                        WatchListFragmentDirections.actionMovieFragmentToMovieDetailFragment(
                            uuid
                        )
                    )
                }
            },{

            })
            val layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.SPACE_BETWEEN
                alignItems = AlignItems.CENTER
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
            }
            binding.watchListRecyclerView.layoutManager = layoutManager
            binding.watchListRecyclerView.adapter = watchListAdapter
        }
    }

    private fun observeWatchList(){
        viewModel.watchlistAdapterList.observe(viewLifecycleOwner){
            initAdapter()
        }
    }



}