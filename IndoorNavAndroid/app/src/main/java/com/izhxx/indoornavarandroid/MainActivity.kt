package com.izhxx.indoornavarandroid

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.izhxx.indoornavarandroid.databinding.MainActivityBinding
import com.izhxx.indoornavarandroid.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment
        val navController = navHostFragment.navController

        //Change bottom navigation visibility if destination search fragment or point selection
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_search ||
                destination.id == R.id.navigation_points_selection) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
        navView.setupWithNavController(navController)

        observer(binding)
    }

    //Observer cardState in sharedVM to change bottom nav visibility
    private fun observer(binding: MainActivityBinding) {
        sharedViewModel.cardState.observe(this) { cardState ->
            if (cardState) binding.bottomNavigation.visibility = View.GONE
            else binding.bottomNavigation.visibility = View.VISIBLE
        }
    }

}