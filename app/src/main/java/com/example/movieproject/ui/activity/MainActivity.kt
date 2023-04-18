package com.example.movieproject.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.movieproject.R
import com.example.movieproject.base.BaseActivity
import com.example.movieproject.databinding.ActivityMainBinding
import com.example.movieproject.utils.setGone
import com.example.movieproject.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun prepareView(savedInstanceState: Bundle?) {
        initNavController()
    }

    private fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navHostFragment.navController)
        onDestinationChanged(navHostFragment.navController)
    }

    private fun onDestinationChanged(navController: NavController){
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.movieFragment -> {
                    binding.bottomNavigationView.setVisible()
                }
                R.id.searchFragment -> {
                    binding.bottomNavigationView.setVisible()
                }
                R.id.profileFragment -> {
                    binding.bottomNavigationView.setVisible()
                }
                R.id.watchlistfragment -> {
                    binding.bottomNavigationView.setVisible()
                }

                else -> {
                    binding.bottomNavigationView.setGone()
                }
            }
        }
    }
}