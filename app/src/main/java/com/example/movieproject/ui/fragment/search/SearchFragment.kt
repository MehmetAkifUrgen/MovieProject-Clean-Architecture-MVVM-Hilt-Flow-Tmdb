package com.example.movieproject.ui.fragment.search


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.data.uimodel.genre.GenreUiModel
import com.example.movieproject.databinding.FragmentSearchBinding
import com.example.movieproject.ui.adapter.discover.DiscoverPagingAdapter
import com.example.movieproject.ui.adapter.genre.GenreAdapter
import com.example.movieproject.ui.adapter.searchMovie.SearchMovieAdapter
import com.example.movieproject.ui.fragment.populars.MovieFragmentDirections
import com.google.android.flexbox.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private var searchAdapter: SearchMovieAdapter? = null
    private val discoverAdapter = DiscoverPagingAdapter()
    private val viewModel: SearchViewModel by viewModels()
    var genreId: String = ""

    override fun getLayoutId(): Int = R.layout.fragment_search
    override fun prepareView(savedInstanceState: Bundle?) {
        searchFunc()
        viewModel.genreRequest()
        initGenre()
        yearPicker()
        discoverClick()

    }

    private fun dropDown(data: ArrayList<GenreUiModel>) {

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data.map {
                it.name
            })
        binding.genreSpinner.adapter = adapter
        binding.genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                val selectedGenre: String? = data[i].id
                if (selectedGenre != null) {
                    discoverUpdate(selectedGenre)
                }
                // Seçilen şehirle ilgili işlemleri burada yapabilirsiniz
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }


    private fun yearPicker() {
        binding.apply {

            startYear.minValue = 1900
            startYear.maxValue = 2023
            startYear.value = 2010
            startYear.setOnValueChangedListener { numberPicker, i, i2 ->
                binding.startYear.value = numberPicker.value
                discoverUpdate(genreId)
            }

            endYear.minValue = 1901
            endYear.maxValue = 2025
            endYear.value = 2023
            endYear.setOnValueChangedListener { numberPicker, i, i2 ->
                binding.endYear.value = numberPicker.value
                discoverUpdate(genreId)
            }
        }
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


    private fun discoverUpdate(uuid: String) {
        binding.startYear.isEnabled = true
        binding.endYear.isEnabled = true
        viewModel.setStartDate(binding.startYear.value.toString())
        viewModel.setEndDate(binding.endYear.value.toString())
        genreId = uuid
        viewModel.setQuery(uuid)
        setRecyclerView()
        observeDiscover()
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
            dropDown(it)
        }
    }


}