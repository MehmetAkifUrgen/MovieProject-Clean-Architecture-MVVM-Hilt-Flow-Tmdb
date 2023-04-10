package com.example.movieproject.ui.fragment.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.NumberPicker
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
import kotlin.collections.ArrayList

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    lateinit var drawerLayout: DrawerLayout



    private var searchAdapter: SearchMovieAdapter? = null
    private val discoverAdapter = DiscoverPagingAdapter()
    private var genreAdapter: GenreAdapter? = null
    private val viewModel: SearchViewModel by viewModels()
    var genreId:String=""

    override fun getLayoutId(): Int = R.layout.fragment_search
    override fun prepareView(savedInstanceState: Bundle?) {
        searchFunc()
        viewModel.genreRequest()
        initGenre()
        yearPicker()
        drawerLayout()
        discoverClick()
        dropDown()

    }

    private fun dropDown() {
        val items = listOf("Öğe 1", "Öğe 2", "Öğe 3")
        ArrayAdapter.createFromResource(
            requireContext(),
           initGenre(),
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.genreSpinner.adapter = adapter
        }
        binding.genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Seçilen öğeyle yapılacak işlemler
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Hiçbir şey seçilmediğinde yapılacak işlemler
            }
        }
    }


    private fun yearPicker() {
        binding.apply {
            startYear.minValue = 1900
            startYear.maxValue = 2100
            startYear.setOnValueChangedListener { numberPicker, i, i2 ->
                binding.startYear.value = numberPicker.value
            }

            endYear.minValue = 1901
            endYear.maxValue = 2100
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
                    discoverUpdate(uuid)
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            })
            binding.genreRecyclerView.adapter = genreAdapter
        }
    }

    private fun discoverUpdate(uuid: String) {
        binding.startYear.isEnabled = true
        binding.endYear.isEnabled=true
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
            return@observe
        }
    }


}