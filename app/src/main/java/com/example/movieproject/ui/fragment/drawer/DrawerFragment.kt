package com.example.movieproject.ui.fragment.drawer

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
import com.google.android.flexbox.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrawerFragment : BaseFragment<FragmentSearchBinding>() {




    override fun getLayoutId(): Int = R.layout.drawer_layout

    override fun prepareView(savedInstanceState: Bundle?) {


    }

    private fun initAdapter(){

    }

    private fun initAgentAdapterListObserve(){

    }


}