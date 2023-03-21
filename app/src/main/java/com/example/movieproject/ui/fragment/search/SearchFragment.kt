package com.example.movieproject.ui.fragment.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.data.uimodel.genre.GenreUiModel
import com.example.movieproject.data.uimodel.populars.PopularUiModel
import com.example.movieproject.databinding.FragmentSearchBinding
import com.example.movieproject.ui.adapter.discover.DiscoverAdapter
import com.example.movieproject.ui.adapter.discover.DiscoverPagingAdapter
import com.example.movieproject.ui.adapter.genre.GenreAdapter
import com.example.movieproject.ui.adapter.searchMovie.SearchMovieAdapter
import com.example.movieproject.ui.fragment.populars.MovieFragmentDirections
import com.google.android.flexbox.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    lateinit var drawerLayout: DrawerLayout

    private var searchAdapter: SearchMovieAdapter? = null
    private val discoverAdapter = DiscoverPagingAdapter()
    private var genreAdapter: GenreAdapter? = null
    private val viewModel: SearchViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_search


    override fun prepareView(savedInstanceState: Bundle?) {
        searchFunc()
        viewModel.genreRequest()
        initGenre()

        drawerLayout()
        discoverClick()


    }

    private fun searchFunc() {
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.castRequest(s.toString())
                initSearchAdapterListObserve()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun drawerLayout() {
        drawerLayout = binding.myDrawerLayout
        binding.myDrawerButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);

            } else {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        }
    }

    private fun initAdapter() {
        viewModel.searchAdapterList.value?.let { adapterList ->
            searchAdapter = SearchMovieAdapter(adapterList, itemClick = {
                it.id?.let { uuid ->
                    findNavController().navigate(
                        MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
                            uuid
                        )
                    )
                }
            })
            val layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.SPACE_BETWEEN
                alignItems = AlignItems.CENTER
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
            }
            binding.searchRecyclerView.layoutManager = layoutManager
            binding.searchRecyclerView.adapter = searchAdapter
            binding.searchRecyclerView.visibility = View.VISIBLE
        }
    }



    private fun initGenreAdapter(genreUiModels: ArrayList<GenreUiModel>) {
        genreUiModels.let { adapterList ->
            genreAdapter = GenreAdapter(adapterList, itemClick = {
                it.id?.let { uuid ->

                    viewModel.setQuery(uuid)
                    setRecyclerView()
                    observeDiscover()

                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            })
            binding.genreRecyclerView.adapter = genreAdapter
        }
    }

    private fun observeDiscover() {
        viewModel.list.observe(viewLifecycleOwner) {
            discoverAdapter.submitData(lifecycle, it)
        }
    }

    private fun discoverClick() {
        discoverAdapter.onMovieClick {
            findNavController().navigate(
                MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(it)
            )
        }
    }

    private fun setRecyclerView() {
        binding.searchRecyclerView.apply {
            adapter = discoverAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun initSearchAdapterListObserve() {
        viewModel.searchAdapterList.observe(viewLifecycleOwner) {
            initAdapter()
        }
    }

    private fun initGenre() {
        viewModel.genreAdapterList.observe(viewLifecycleOwner) {
            initGenreAdapter(it)
        }
    }


}